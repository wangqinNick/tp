package seedu.duke.data.storage;

import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.alibaba.fastjson.JSON;

/**
 * Manages all inputs to files, and the conversion from Object in memory to String in file.
 * Throws exceptions to InputOutputManager and handles none.
 *
 * @author Sim Jun You
 */
public class Encoder {
    /**
     * Saves all tasks to file path/name specified.
     *
     * @param dataFileName
     *  The name of the file to save to
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
     */
    public static void saveModules(String dataFileName) throws IOException, ModuleManager.ModuleNotFoundException {
        File mySaveFile = new File(dataFileName);
        prepareSaveFile(mySaveFile);
        Module currentModule;
        String[] modsToBeSaved = ModuleManager.getModCodeList();
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
     */
    public static void saveNusModules(String dataFileName) throws IOException, ModuleManager.ModuleNotFoundException {
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
