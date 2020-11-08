package seedu.ravi.data.storage;

import com.alibaba.fastjson.JSON;
import seedu.ravi.data.Module;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.Task;
import seedu.ravi.data.TaskManager;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages all inputs to files, and the conversion from Object in memory to String in file.
 * Throws exceptions to InputOutputManager and handles none.
 *
 * @author Sim Jun You
 */
public class Encoder {
    /**
     * Saves timetable to file path/name specified.
     *
     * @param dataFileName
     *  The name of the file to save to
     * @throws IOException
     *  When there is an error preparing the save file
     */
    public static void saveTimetable(String dataFileName) throws IOException {
        File mySaveFile = new File(dataFileName);
        prepareSaveFile(mySaveFile);
        writeToFile(mySaveFile, JSON.toJSONString(TimeTableManager.getTimeTable()));
    }

    /**
     * Saves all tasks to file path/name specified.
     *
     * @param dataFileName
     *  The name of the file to save to
     * @throws IOException
     *  When there is an error preparing the save file
     * @throws TaskManager.TaskNotFoundException
     *  If index out of range (should never happen)
     */
    public static void saveTasks(String dataFileName) throws IOException, TaskManager.TaskNotFoundException {
        int taskCount = TaskManager.getTaskCount();
        File mySaveFile = new File(dataFileName);
        prepareSaveFile(mySaveFile);
        Task currentTask;
        for (int i = 0; i < taskCount; i++) {
            currentTask = TaskManager.getTask(i);
            writeToFile(mySaveFile, JSON.toJSONString(currentTask));
        }
    }

    /**
     * Saves all modules to file path/name specified.
     *
     * @param dataFileName
     *  The name of the file to save to
     * @throws IOException
     *  When there is an error preparing the save file
     * @throws ModuleNotFoundException
     *  If module not found (should never happen)
     */
    public static void saveModules(String dataFileName) throws IOException, ModuleNotFoundException {
        File mySaveFile = new File(dataFileName);
        prepareSaveFile(mySaveFile);

        Module currentModule;
        for (String eachModCode : ModuleManager.getModCodeList()) {
            currentModule = ModuleManager.getModule(eachModCode);
            writeToFile(mySaveFile, JSON.toJSONString(currentModule));
        }
    }

    /**
     * Saves all NUS modules to file path/name specified.
     *
     * @param dataFileName
     *  The name of the file to save to
     * @throws IOException
     *  When there is an error preparing the save file
     * @throws ModuleNotProvidedException
     *  If module not found (should never happen)
     */
    public static void saveNusModules(String dataFileName) throws IOException, ModuleNotProvidedException {
        File mySaveFile = new File(dataFileName);
        prepareSaveFile(mySaveFile);
        Module currentModule;
        for (String eachModCode : ModuleManager.getNusModCodeList()) {
            currentModule = ModuleManager.getNusModule(eachModCode);
            writeToFile(mySaveFile, JSON.toJSONString(currentModule));
        }
    }

    /**
     * Prepares the specified save file (and dir) by creating it or deleting then creating it (if the file exists).
     *
     * @param saveFile
     *  The File object to prepare the actual file for
     *
     * @throws IOException
     *  If createNewFile does not work as expected
     */
    private static void prepareSaveFile(File saveFile) throws IOException {
        if (saveFile.exists()) { // overwrite by deleting first
            saveFile.delete();
        }
        saveFile.createNewFile();
    }

    /**
     * Appends the specified string to the specified File object.
     *
     * @param saveFile
     *  The File object to write to
     * @param textToAdd
     *  The string to write
     * @throws IOException
     *  If FileWriter cannot write or close the file
     */
    private static void writeToFile(File saveFile, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(saveFile, true); // true to append data instead of overwrite
        fw.write(textToAdd + "\n");
        fw.close();
    }
}
