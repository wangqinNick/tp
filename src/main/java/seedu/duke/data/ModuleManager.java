package seedu.duke.data;

import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.ui.TextUi;
import seedu.duke.DukeLogger;

import java.util.HashMap;

public class ModuleManager {
    private static HashMap<String, Module> modulesMap = new HashMap<>();
    // modulesMap is the main module list. Maps module code to module object.
    private static HashMap<String, Module> nusModsMap = new HashMap<>();
    // nusModsMap is the module list containing the Module objects created from NUSMods' JSON file of modules.

    private static final DukeLogger logger = new DukeLogger(ModuleManager.class.getName());

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
        logger.getLogger().info("Retrieving module with code: " + moduleCode);
        for (Module module : modulesMap.values()) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        logger.getLogger().warning("Can't retrieve module because it doesn't exist!");
        throw new ModuleNotFoundException();
    }

    /**
     * Edits a module in the Module List by replacing the old module object with a new one.
     *
     * @param newModule
     *  The new module that replaces the old one.
     * @param oldModuleCode
     *  The module code of the module to be edited.
     * @throws ModuleNotProvidedException
     *  If there is no module with the new module code offered by NUS
     * @throws DuplicateModuleException
     *  If there are duplicate modules with the same module code as the new module code in the Module List
     */
    public static void edit(Module newModule, String oldModuleCode)
            throws ModuleNotProvidedException, DuplicateModuleException {
        logger.getLogger().info("Editing module at old module code: " + oldModuleCode);
        //modulesMap.get(module.getCode()).setTitle(moduleDescription);
        Module oldModule = modulesMap.get(oldModuleCode);
        if (!modulesMap.containsKey(oldModuleCode)) {
            logger.getLogger().warning("Old module code not found!");
            throw new ModuleNotProvidedException();
        }
        if (oldModule.isSameModule(newModule)) {
            logger.getLogger().warning("Can't edit to new mod code because it exists!");
            throw new DuplicateModuleException();
        }
        modulesMap.remove(oldModuleCode);
        modulesMap.put(newModule.getModuleCode(), newModule);
    }

    /**
     * Checks if the module to be graded is in the moduleMap and assigns the grade to the module.
     *
     * @param moduleCode
     * module to be graded
     * @param grade
     * the grade to be assigned
     * @throws ModuleNotFoundException
     * if there is no such module in the module list input by the user
     */
    public static void grade(Module moduleCode, String grade, int moduleCredit) throws ModuleNotFoundException {
        if (contains(moduleCode.getModuleCode())) {
            modulesMap.get(moduleCode.getModuleCode()).setModuleGrade(grade);
            modulesMap.get(moduleCode.getModuleCode()).setModuleCredit(moduleCredit);
        } else {
            throw new ModuleNotFoundException();
        }
    }

    /**
     * Checks for duplicates of the same module code in the Module List.
     * @param moduleCode
     *  The module code to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    public static boolean contains(String moduleCode) {
        for (String eachCode : modulesMap.keySet()) {
            if (eachCode.equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a module to the Module List.
     * @param newModule
     *  The module object to add to the module list
     */
    public static void add(Module newModule) throws DuplicateModuleException {
        logger.getLogger().info("Adding module with code: " + newModule.getModuleCode());
        if (contains(newModule.getModuleCode())) {
            logger.getLogger().warning("Can't add module because it already exists!");
            throw new DuplicateModuleException();
        }
        modulesMap.put(newModule.getModuleCode(), newModule);
    }

    /**
     * Removes a module from the Module List using the module code.
     * @param moduleCode
     *  The module code of the module to remove from the module list
     */
    public static boolean delete(String moduleCode) throws ModuleNotFoundException {
        logger.getLogger().info("Deleting module with code: " + moduleCode);
        if (!contains(moduleCode)) {
            logger.getLogger().warning("Can't delete module because it doesn't exist!");
            throw new ModuleNotFoundException();
        }
        modulesMap.remove(moduleCode);
        return false;
    }

    /**
     *  Finds a module with the specified module code in the NUSMods Module List.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found in the Module List
     */
    public static Module getNusModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : nusModsMap.values()) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    public static String[] getModCodeList() {
        String[] outputArray = modulesMap.keySet().toArray(new String[0]);
        return outputArray;
    }

    public static String[] getNusModCodeList() {
        String[] outputArray = nusModsMap.keySet().toArray(new String[0]);
        return outputArray;
    }

    /**
     * List modules in the module map.
     *
     * @returns
     *  The formatted module list from TextUi or null if list is empty
     */
    public static String list() {
        if (modulesMap.size() > 0) {
            return TextUi.getIndexModuleList(modulesMap);
        } else {
            return null;
        }
    }

    /**
     * Loads the file loaded module map into ModuleManager's own module map.
     *
     * @param loadedModulesMap the loaded module map from file
     */
    public static void loadMods(HashMap<String, Module> loadedModulesMap) {
        modulesMap = loadedModulesMap;
    }

    /**
     * Loads the file loaded module map into ModuleManager's own module map.
     *
     * @param loadedModulesMap the loaded module map from file
     */
    public static void loadNusMods(HashMap<String, Module> loadedModulesMap) {
        nusModsMap = loadedModulesMap;
    }

    /**
     * Clears all modules in modulesMap.
     */
    public static void clearModules() {
        modulesMap = new HashMap<>();
    }

    /**
     * Returns the modules in the system.
     *
     * @return modulesMap
     */
    public static HashMap<String, Module> getModulesMap() {
        return modulesMap;
    }

    public static class ModuleNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateModuleException extends DuplicateDataException {
    }
}
