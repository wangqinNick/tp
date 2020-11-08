package seedu.duke.data;

import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {
    private static ArrayList<Module> moduleList;
    private static HashMap<String, String> modulesMap;

    private static final String NO_KEYWORD = "";

    /**
     * Initialises the ModuleManager class.
     *
     * @param modulesMap
     *  The hash map containing NUS provided modules
     */
    public static void initialise(HashMap<String, String> modulesMap, ArrayList<Module> moduleList) {
        ModuleManager.modulesMap = modulesMap;
        ModuleManager.moduleList = moduleList;
    }

    /**
     *  Finds a module with the specified module code in the Module List.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found in the Module List
     */
    public static Module getModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : moduleList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * Edits a module in the Module List.
     *
     * @param toEdit
     *  The module to be edited
     * @param newModuleCode
     *  The new module code of the module
     * @throws ModuleNotProvidedException
     *  If there is no module with the new module code offered by NUS
     * @throws DuplicateModuleException
     *  If there are duplicate modules with the same module code as the new module code in the Module List
     */
    public static void edit(Module toEdit, String newModuleCode)
            throws ModuleNotProvidedException, DuplicateModuleException {
        if (!modulesMap.containsKey(newModuleCode)) {
            throw new ModuleNotProvidedException();
        }
        if (!toEdit.isSameModule(newModuleCode) && contains(newModuleCode)) {
            throw new DuplicateModuleException();
        }
        String newTitle = modulesMap.get(newModuleCode);
        toEdit.setModuleCode(newModuleCode);
        toEdit.setTitle(newTitle);
    }

    /**
     * Checks for duplicates of the same module code in the Module List.
     * @param moduleCode
     *  The module code to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    public static boolean contains(String moduleCode) {
        for (Module module : moduleList) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    public static void remove(String moduleCode) throws ModuleNotFoundException {
        if (!contains(moduleCode)){
            throw new ModuleNotFoundException();
        }
        moduleList.removeIf(module -> module.getModuleCode().equalsIgnoreCase(moduleCode));
    }

    /**
     * Returns the modules map
     *
     * @return modules map
     */
    public static HashMap<String, String> getModulesMap() {
        return modulesMap;
    }

    /**
     * Add a module to the Module List.
     *
     * @param toAdd
     *  The module to be added
     */
    public static void add(Module toAdd) throws DuplicateModuleException, ModuleNotProvidedException {
        //check duplicate
        if (contains(toAdd.getModuleCode())) {
            throw new DuplicateModuleException();
        } else if (modulesMap.size() > 0 && !modulesMap.containsKey(toAdd.getModuleCode())) {
            throw new ModuleNotProvidedException();
        } else {
            String moduleTitle = modulesMap.get(toAdd.getModuleCode());
            toAdd.setTitle(moduleTitle);
            moduleList.add(toAdd);
        }
    }

    /**
     * Filter for modules in the Module List with module code that matches <b>exactly</b> the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @return
     *  The list of filtered modules
     */
    public static ArrayList<Module> filterExact(String moduleKeyword) {
        // Returns all modules in the Module List if no keyword is provided.
        if (moduleKeyword.equals(NO_KEYWORD)) {
            return moduleList;
        }

        ArrayList<Module> filteredModuleList = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getModuleCode().toLowerCase().equals(moduleKeyword.toLowerCase())) {
                filteredModuleList.add(module);
            }
        }
        return filteredModuleList;
    }

    /* Filters for data (module / task / category) that *contains* given keywords (i.e. not exact match)
     *  in a case-insensitive manner. There may be multiple data that matches. */

    /**
     * Filter for modules in the Module List with module code that contains the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @return
     *  The list of filtered modules
     */
    public static ArrayList<Module> filter(String moduleKeyword) {
        ArrayList<Module> filteredModuleList = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getModuleCode().toLowerCase().contains(moduleKeyword.toLowerCase())) {
                filteredModuleList.add(module);
            }
        }
        return filteredModuleList;
    }

    public static ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public static ArrayList<Task> getAllTasks() {
        return filter(NO_KEYWORD, NO_KEYWORD);
    }

    /**
     * Filter for modules in the Module List with module code that contains the specified module keyword,
     * then for categories in the Category List of the filtered modules with name that contains the specified
     * category keyword, then for tasks in the Task List of the filtered categories with description that
     * contains the specified task keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @param taskKeyword
     * The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public static ArrayList<Task> filter(String moduleKeyword, String taskKeyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Module module : filter(moduleKeyword)) {
            filteredTaskList.addAll(module.getTasks().filter(taskKeyword));
        }
        return filteredTaskList;
    }

    /**
     * Sets the entire Module List to a new list.
     *
     * @param moduleList
     *  The new Module List to be set
     */
    public static void setModuleList(ArrayList<Module> moduleList) {
        ModuleManager.moduleList = moduleList;
    }

    public static class ModuleNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateModuleException extends DuplicateDataException {
    }
}
