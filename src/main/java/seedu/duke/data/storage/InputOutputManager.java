package seedu.duke.data.storage;

import com.alibaba.fastjson.JSONException;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
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
    static final String root = System.getProperty("user.dir");
    static final java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");
    static final java.nio.file.Path resourceDirPath = java.nio.file.Paths.get(root, "src/main/resources");

    static final String userModuleFileName = FileName.MOD_SAVE_FILE_NAME + FileName.FILE_EXT;
    static final String userTaskFileName = FileName.TASK_SAVE_FILE_NAME + FileName.FILE_EXT;
    static final String timetableFileName = FileName.TIMETABLE_SAVE_FILE_NAME + FileName.FILE_EXT;
    static final String nusModuleFileName = FileName.NUSMOD_SAVE_FILE_NAME + FileName.FILE_EXT;

    static final java.nio.file.Path userModuleFile =
            java.nio.file.Paths.get(String.valueOf(dirPath),userModuleFileName);
    static final java.nio.file.Path userTaskFile =
            java.nio.file.Paths.get(String.valueOf(dirPath), userTaskFileName);
    static final java.nio.file.Path timetableFile =
            java.nio.file.Paths.get(String.valueOf(dirPath), timetableFileName);
    static final java.nio.file.Path nusModuleFile =
            java.nio.file.Paths.get(String.valueOf(resourceDirPath), nusModuleFileName);
    static final String jarNusModuleFile = "/" + nusModuleFileName;

    private static final DukeLogger logger = new DukeLogger(InputOutputManager.class.getName());

    /**
     * Creates the save directory if it has not been created.
     * Loads the user's module and task saves into memory.
     */
    public static int start() {
        int status = 1; // 'Skipped load' status by default
        logger.getLogger().info("Starting InputOutputManager");
        java.io.File saveFolder = dirPath.toFile();
        if (!saveFolder.exists()) {
            logger.getLogger().info("Save folder does not exist, creating now");
            saveFolder.mkdir();
        } else {
            try {
                if (Files.exists(userModuleFile)) {
                    logger.getLogger().info("Loading user module saves from " + userModuleFileName);
                    ModuleManager.loadMods(Decoder.loadModules(userModuleFile.toString()));
                    status = 0; // 'Success' status
                } else {
                    logger.getLogger().info("Skipping user module save; file does not exist: " + userModuleFileName);
                }

                if (Files.exists(userTaskFile)) {
                    logger.getLogger().info("Loading user task saves from " + userTaskFileName);
                    TaskManager.loadTasks(Decoder.loadTasks(userTaskFile.toString()));
                    status = 0; // 'Success' status
                } else {
                    logger.getLogger().info("Skipping user task save; file does not exist: " + userTaskFileName);
                }

                if (Files.exists(timetableFile)) {
                    logger.getLogger().info("Loading timetable save from " + timetableFile);
                    TimeTableManager.loadTimeTable(Decoder.loadTimeTable(timetableFile.toString()));
                    status = 0; // 'Success' status
                } else {
                    logger.getLogger().info("Skipping timetable save; file does not exist: " + timetableFile);
                }
            } catch (JSONException e) {
                logger.getLogger().severe("JSON string can't be parsed! Is it corrupted?");
                renameCorruptedFiles();
                status = 2; // 'Corrupted' status
            }
        }
        loadNusModSave(); // will load from NUSMods API if file not found
        return status;
    }

    /**
     * Loads NUS Modules from the given file.
     */
    public static void loadNusModSave() {
        try {
            logger.getLogger().info("Loading NUS modules from JAR file resources folder " + nusModuleFileName);
            ModuleManager.loadNusMods(Decoder.loadNusModsFromJar());
            logger.getLogger().info("Successfully loaded from JAR!");
        } catch (IOException | NullPointerException e) {
            try {
                logger.getLogger().warning("Couldn't load NUSMods from JAR directory, trying normal directory...");
                ModuleManager.loadNusMods(Decoder.loadModules(nusModuleFile.toString()));
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
        logger.getLogger().info("Saving user modules and tasks into " + userModuleFileName
                + " and " + userTaskFileName);
        try {
            if (ModuleManager.getModCodeList().length != 0) {
                Encoder.saveModules(userModuleFile.toString());
            }
            if (TaskManager.getTaskCount() != 0) {
                Encoder.saveTasks(userTaskFile.toString());
            }
            Encoder.saveTimetable(timetableFile.toString()); // always save, even if there's no lessons
        } catch (ModuleManager.ModuleNotFoundException | TaskManager.TaskNotFoundException | IOException e) {
            logger.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
        }

    }

    /**
     * Updates the user's NUS Modules save file.
     */
    public static void saveNusMods() {
        logger.getLogger().info("Saving NUS modules into " + nusModuleFileName);
        try {
            Encoder.saveNusModules(nusModuleFile.toString());
        } catch (ModuleManager.ModuleNotFoundException | IOException e) {
            logger.getLogger().log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    /**
     * Renames the corrupted files (if any) by adding a ".bak" behind the file name.
     */
    public static void renameCorruptedFiles() {
        String backup = ".bak";

        String modBackup = FileName.MOD_SAVE_FILE_NAME + FileName.FILE_EXT + backup;
        String taskBackup = FileName.TASK_SAVE_FILE_NAME + FileName.FILE_EXT + backup;
        String timetableBackup = FileName.TIMETABLE_SAVE_FILE_NAME + FileName.FILE_EXT + backup;

        try {
            Files.move(userModuleFile, userModuleFile.resolveSibling(modBackup));
            Files.move(userTaskFile, userTaskFile.resolveSibling(taskBackup));
            Files.move(timetableFile, timetableFile.resolveSibling(timetableBackup));
            logger.getLogger().info("Corrupted files are renamed successfully.");
        } catch (IOException e) {
            logger.getLogger().severe("Could not rename corrupted files!");
            try {
                Files.deleteIfExists(userModuleFile);
                Files.deleteIfExists(userTaskFile);
                Files.deleteIfExists(timetableFile);
            } catch (IOException e2) {
                logger.getLogger().warning("Could not delete corrupted files!");
            }
        }
    }
}
