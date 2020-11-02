package seedu.duke.data.storage;

import com.alibaba.fastjson.JSONException;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.ModuleNotFoundException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.util.FileName;
import seedu.duke.data.TaskManager;
import seedu.duke.DukeLogger;

import java.io.IOException;
import java.nio.file.Files;

import java.util.logging.Level;


/**
 * Manages all inputs and outputs (to and from files).
 * Encoder and Decoder are only used by InputOutputManager.
 * InputOutputManager also handles exceptions thrown by Encoder and Decoder. No exceptions are thrown from here.
 *
 * @author Sim Jun You
 */
public class InputOutputManager {
    static final String ROOT = System.getProperty("user.dir");
    static final java.nio.file.Path DIR_PATH = java.nio.file.Paths.get(ROOT, "data");
    static final java.nio.file.Path RESOURCE_DIR_PATH = java.nio.file.Paths.get(ROOT, "src/main/resources");

    static final String USER_MOD_F_NAME = FileName.MOD_SAVE_FILE_NAME + FileName.FILE_EXT;
    static final String USER_TASK_F_NAME = FileName.TASK_SAVE_FILE_NAME + FileName.FILE_EXT;
    static final String TTABLE_F_NAME = FileName.TIMETABLE_SAVE_FILE_NAME + FileName.FILE_EXT;
    static final String NUS_MOD_F_NAME = FileName.NUSMOD_SAVE_FILE_NAME + FileName.FILE_EXT;

    static final String BACKUP = ".bak";

    static final String MOD_BK_NAME = FileName.MOD_SAVE_FILE_NAME + FileName.FILE_EXT + BACKUP;
    static final String TASK_BK_NAME = FileName.TASK_SAVE_FILE_NAME + FileName.FILE_EXT + BACKUP;
    static final String TTABLE_BK_NAME = FileName.TIMETABLE_SAVE_FILE_NAME + FileName.FILE_EXT + BACKUP;

    static final java.nio.file.Path USER_MODULE_FILE =
            java.nio.file.Paths.get(String.valueOf(DIR_PATH), USER_MOD_F_NAME);
    static final java.nio.file.Path USER_TASK_FILE =
            java.nio.file.Paths.get(String.valueOf(DIR_PATH), USER_TASK_F_NAME);
    static final java.nio.file.Path TIMETABLE_FILE =
            java.nio.file.Paths.get(String.valueOf(DIR_PATH), TTABLE_F_NAME);
    static final java.nio.file.Path NUS_MODULE_FILE =
            java.nio.file.Paths.get(String.valueOf(RESOURCE_DIR_PATH), NUS_MOD_F_NAME);
    static final String JAR_NUS_MODULE_FILE = "/" + NUS_MOD_F_NAME;

    private static final DukeLogger logger = new DukeLogger(InputOutputManager.class.getName());

    /**
     * Creates the save directory if it has not been created.
     * Loads the user's module and task saves into memory.
     *
     * @return
     *  the integer containing the status of loading of the modules, task and timetable.
     */
    public static int start() {
        int status = 0; // 'Skipped load' status for all 3 files by default
        logger.getLogger().info("Starting InputOutputManager");
        java.io.File saveFolder = DIR_PATH.toFile();
        if (!saveFolder.exists()) {
            logger.getLogger().info("Save folder does not exist, creating now");
            saveFolder.mkdir();
        } else {
            // For documentation of this part, look at TextUi's showWelcomeMessage
            status += tryLoadMods();
            status += tryLoadTasks() * 10;
            status += tryLoadTimetable() * 100;
        }
        loadNusModSave(); // will load from NUSMods API if file not found
        return status;
    }

    public static int tryLoadMods() {
        try {
            if (Files.exists(USER_MODULE_FILE)) {
                logger.getLogger().info("Loading user module saves from " + USER_MOD_F_NAME);
                ModuleManager.loadMods(Decoder.loadModules(USER_MODULE_FILE.toString()));
                return 1; // 'Success' status
            } else {
                logger.getLogger().info("Skipping user module save; file does not exist: " + USER_MOD_F_NAME);
                return 0; // 'Skipped' status
            }
        } catch (JSONException e) {
            logger.getLogger().severe("JSON string can't be parsed! Error: " + e.getMessage());
            handleCorruptedFile(USER_MODULE_FILE, MOD_BK_NAME);
            return 2; // 'Corrupted' status
        }
    }

    public static int tryLoadTasks() {
        try {
            if (Files.exists(USER_TASK_FILE)) {
                logger.getLogger().info("Loading user task saves from " + USER_TASK_F_NAME);
                TaskManager.loadTasks(Decoder.loadTasks(USER_TASK_FILE.toString()));
                return 1; // 'Success' status
            } else {
                logger.getLogger().info("Skipping user task save; file does not exist: " + USER_TASK_F_NAME);
                return 0; // 'Skipped' status
            }
        } catch (JSONException e) {
            logger.getLogger().severe("JSON string can't be parsed! Error: " + e.getMessage());
            handleCorruptedFile(USER_TASK_FILE, TASK_BK_NAME);
            return 2; // 'Corrupted' status
        }
    }

    public static int tryLoadTimetable() {
        try {
            if (Files.exists(TIMETABLE_FILE)) {
                logger.getLogger().info("Loading timetable save from " + TIMETABLE_FILE);
                TimeTableManager.loadTimeTable(Decoder.loadTimeTable(TIMETABLE_FILE.toString()));
                return 1; // 'Success' status
            } else {
                logger.getLogger().info("Skipping timetable save; file does not exist: " + TIMETABLE_FILE);
                return 0; // 'Skipped' status
            }
        } catch (JSONException e) {
            logger.getLogger().severe("JSON string can't be parsed! Error: " + e.getMessage());
            handleCorruptedFile(TIMETABLE_FILE, TTABLE_BK_NAME);
            return 2; // 'Corrupted' status
        }
    }

    /** Loads NUS Modules from the given file. */
    public static void loadNusModSave() {
        try {
            logger.getLogger().info("Loading NUS modules from JAR file resources folder " + NUS_MOD_F_NAME);
            ModuleManager.loadNusMods(Decoder.loadNusModsFromJar());
            logger.getLogger().info("Successfully loaded from JAR!");
        } catch (IOException | NullPointerException e) {
            try {
                logger.getLogger().warning("Couldn't load NUSMods from JAR directory, trying normal directory...");
                ModuleManager.loadNusMods(Decoder.loadModules(NUS_MODULE_FILE.toString()));
                logger.getLogger().info("Successfully loaded from normal directory!");
            } catch (Exception e2) {
                logger.getLogger().warning("Can't load from normal location! " + e2.getMessage());
                logger.getLogger().warning("Loading from NUSMods API...");
                ModuleManager.loadNusMods(Decoder.generateNusModsList());
                logger.getLogger().info("Successfully loaded from NUSMods API!");
            }
        }
    }

    /**
     * Updates the user's save files. Does not save NUS Modules.
     */
    public static void save() {
        logger.getLogger().info("Saving user modules and tasks into " + USER_MOD_F_NAME
                + " and " + USER_TASK_F_NAME);
        try {
            if (ModuleManager.getModCodeList().length != 0) {
                Encoder.saveModules(USER_MODULE_FILE.toString());
            }
            if (TaskManager.getTaskCount() != 0) {
                Encoder.saveTasks(USER_TASK_FILE.toString());
            }
            Encoder.saveTimetable(TIMETABLE_FILE.toString()); // always save, even if there's no lessons
        } catch (ModuleNotFoundException | TaskManager.TaskNotFoundException | IOException e) {
            logger.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }

    /**
     * Updates the user's NUS Modules save file.
     */
    public static void saveNusMods() {
        logger.getLogger().info("Saving NUS modules into " + NUS_MOD_F_NAME);
        try {
            Encoder.saveNusModules(NUS_MODULE_FILE.toString());
        } catch (ModuleNotProvidedException | IOException e) {
            logger.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    /**
     * Renames the corrupted files (if any) by adding a ".bak" behind the file name.
     */
    public static void handleAllCorruptedFiles() {
        String backup = ".bak";

        String modBackup = FileName.MOD_SAVE_FILE_NAME + FileName.FILE_EXT + backup;
        String taskBackup = FileName.TASK_SAVE_FILE_NAME + FileName.FILE_EXT + backup;
        String timetableBackup = FileName.TIMETABLE_SAVE_FILE_NAME + FileName.FILE_EXT + backup;

        handleCorruptedFile(USER_MODULE_FILE, modBackup);
        handleCorruptedFile(USER_TASK_FILE, taskBackup);
        handleCorruptedFile(TIMETABLE_FILE, timetableBackup);
    }

    public static void handleCorruptedFile(java.nio.file.Path currentFile, String newName) {
        try {
            Files.move(currentFile, currentFile.resolveSibling(newName));
            logger.getLogger().info("Renamed corrupted file to " + newName);
        } catch (IOException e) {
            logger.getLogger().severe("Could not rename corrupted file to " + newName);
        }
    }
}