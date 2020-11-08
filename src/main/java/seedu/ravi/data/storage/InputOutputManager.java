package seedu.ravi.data.storage;

import com.alibaba.fastjson.JSONException;
import seedu.ravi.RaviLogger;
import seedu.ravi.data.Module;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.util.FileName;
import seedu.ravi.data.TaskManager;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Files;

import java.util.HashMap;
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
            java.nio.file.Paths.get(String.valueOf(DIR_PATH), NUS_MOD_F_NAME);
    static final String JAR_NUS_MODULE_FILE = "/" + NUS_MOD_F_NAME;

    private static final RaviLogger logger = new RaviLogger(InputOutputManager.class.getName());

    /**
     * Creates the save directory if it has not been created.
     * Loads the user's module and task saves into memory.
     *
     * @return
     *  the integer containing the status of loading of the modules, task and timetable.
     * @throws NusModsNotLoadedException
     *  When no NUSMods data could be loaded. Signals the main class to crash.
     */
    public static int start() throws NusModsNotLoadedException {
        int status = 0; // 'Skipped load' status for all 3 files by default
        logger.getLogger().info("Starting InputOutputManager");
        java.io.File saveFolder = DIR_PATH.toFile();
        if (!saveFolder.exists()) {
            logger.getLogger().info("Save folder does not exist, creating now");
            saveFolder.mkdir();
            status += tryLoadNusMods() * 1000;
        } else {
            // For documentation of this part, look at TextUi's showWelcomeMessage
            status += tryLoadMods();
            status += tryLoadTasks() * 10;
            status += tryLoadTimetable() * 100;
            status += tryLoadNusMods() * 1000;
        }
        return status;
    }

    public static int tryLoadMods() {
        try {
            if (Files.exists(USER_MODULE_FILE)) {
                logger.getLogger().info("Loading user module saves from " + USER_MOD_F_NAME);
                ModuleManager.loadMods(Decoder.loadModules(USER_MODULE_FILE.toString()));
                logger.getLogger().info("Successfully loaded user module saves from " + USER_MOD_F_NAME);
                return 1; // 'Success' status
            } else {
                logger.getLogger().info("Skipping user module save; file does not exist: " + USER_MOD_F_NAME);
                return 0; // 'Skipped' status
            }
        } catch (JSONException e) {
            logger.getLogger().severe("JSON string can't be parsed! Error: " + e.getMessage());
            handleCorruptedFile(USER_MODULE_FILE, MOD_BK_NAME);
            return 2; // 'Corrupted' status
        } catch (IOException e) {
            // should not happen
            logger.getLogger().severe("IOException in tryLoadMods - check me now!!");
            return 0; // 'Skipped' status
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
        } catch (IOException e) {
            // should not happen
            logger.getLogger().severe("IOException in tryLoadTasks - check me now!!");
            return 0; // 'Skipped' status
        }
    }

    public static int tryLoadTimetable() {
        try {
            if (Files.exists(TIMETABLE_FILE)) {
                logger.getLogger().info("Loading timetable save from " + TTABLE_F_NAME);
                TimeTableManager.loadTimeTable(Decoder.loadTimeTable(TIMETABLE_FILE.toString()));
                return 1; // 'Success' status
            } else {
                logger.getLogger().info("Skipping timetable save; file does not exist: " + TTABLE_F_NAME);
                return 0; // 'Skipped' status
            }
        } catch (JSONException e) {
            logger.getLogger().severe("JSON string can't be parsed! Error: " + e.getMessage());
            handleCorruptedFile(TIMETABLE_FILE, TTABLE_BK_NAME);
            return 2; // 'Corrupted' status
        } catch (IOException e) {
            // should not happen
            logger.getLogger().severe("IOException in tryLoadTimetable - check me now!!");
            return 0; // 'Skipped' status
        }
    }

    /**
     * Loads NUSMods from the saved file in the data directory.
     * Failing that, it will try to load from the online NUSMods API.
     * Failing even that, it will fall back to the packaged NUSMods list in the jar file.
     * Finally, failing all these, it will throw the NusModsNotLoadedException as a signal to crash the program.
     *
     * @return
     *  The status code (refer to TextUi's showWelcomeMessage method)
     * @throws NusModsNotLoadedException
     *  When no NUSMods data can be loaded
    */
    public static int tryLoadNusMods() throws NusModsNotLoadedException {
        HashMap<String, Module> loadedNusModsMap;
        int status;
        try {
            logger.getLogger().info("Loading NUSMods data from data directory...");
            loadedNusModsMap = Decoder.loadModules(NUS_MODULE_FILE.toString());
            logger.getLogger().info("Successfully loaded NUSMods data from data directory!");
            status = 0;
        } catch (IOException | JSONException e1) {
            try {
                logger.getLogger().warning("Could not load from data directory!");
                logger.getLogger().info("Loading NUSMods from NUSMods API...");
                loadedNusModsMap = Decoder.generateNusModsList();
                logger.getLogger().info("Successfully loaded NUSMods data from NUSMods API!");
                status = 1;
            } catch (ConnectException | JSONException e2) {
                try {
                    logger.getLogger().warning("Could not load from NUSMods API! (No Internet connection?)");
                    logger.getLogger().info("Loading NUSMods data from JAR resource file...");
                    loadedNusModsMap = Decoder.loadNusModsFromJar();
                    logger.getLogger().info("Successfully loaded NUSMods data from JAR resource file!");
                    status = 2;
                } catch (IOException | JSONException | NullPointerException e3) {
                    logger.getLogger().severe("Could not load from JAR resource file! Crashing...");
                    throw new NusModsNotLoadedException();
                }
            }
        }
        ModuleManager.loadNusMods(loadedNusModsMap);
        return status;
    }

    /**
     * Updates the user's save files. Does not save NUS Modules.
     */
    public static void save() {
        try {
            if (ModuleManager.getModCodeList().length != 0) {
                Encoder.saveModules(USER_MODULE_FILE.toString());
                logger.getLogger().info("Saved module data to " + USER_MOD_F_NAME);
            } else {
                logger.getLogger().warning("No module data to save!");
            }
            if (TaskManager.getTaskCount() != 0) {
                Encoder.saveTasks(USER_TASK_FILE.toString());
                logger.getLogger().info("Saved task data to " + USER_TASK_F_NAME);
            } else {
                logger.getLogger().warning("No task data to save!");
            }
            if (TimeTableManager.isInitialised()) {
                Encoder.saveTimetable(TIMETABLE_FILE.toString());
                logger.getLogger().info("Saved timetable data to " + TTABLE_F_NAME);
            } else {
                logger.getLogger().warning("No timetable data to save!");
            }
        } catch (ModuleNotFoundException | TaskManager.TaskNotFoundException | IOException e) {
            logger.getLogger().severe("Something went wrong while trying to save files!");
            logger.getLogger().log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

    }

    /**
     * Updates the user's NUS Modules save file. Will not create file to save if there is nothing to save.
     */
    public static void saveNusMods() {
        try {
            logger.getLogger().info("Saving NUS modules into " + NUS_MOD_F_NAME);
            if (ModuleManager.getNusModCodeList().length != 0) {
                Encoder.saveNusModules(NUS_MODULE_FILE.toString());
            } else {
                logger.getLogger().warning("No NUSMods data to save!");
            }
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