package seedu.duke.data.storage;

import seedu.duke.data.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class InputOutputManager {
    private static HashMap<String, String> modulesMap;
    private static ArrayList<Task> tasksList;
    static String root = System.getProperty("user.dir");
    static java.nio.file.Path dirPath = java.nio.file.Paths.get(root, "data");

    /** Creates the output directory if it has not been created.  */
    public static void start() {
        load();
    }

    /** Updates the output files.  */
    public static void save() {
        // todo add code to Encoder
        Encoder.saveModules();
        Encoder.saveTasks();
    }

    /** Loads the files.  */
    public static void load() {
        modulesMap = Decoder.loadModules("moduleList.json");
        // todo add code to loadTasks
        tasksList = Decoder.loadTasks("taskList.json");
    }
}
