package seedu.duke.data;

import seedu.duke.directory.Module;
import seedu.duke.directory.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemSetting {
    private static ArrayList<Module> moduleList;
    private static HashMap<String, String> modulesMap;

    public static void initialise(){
        modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        //todo load module list
        moduleList = new ArrayList<>();
        //set data
        ModuleManager.initialise(modulesMap, moduleList);
    }
}
