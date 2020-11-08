package seedu.ravi.data;

import seedu.ravi.RaviLogger;
import seedu.ravi.exception.DuplicateModuleException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;
import seedu.ravi.ui.TextUi;

import java.util.HashMap;


public class ModuleManager {
    private static final RaviLogger logger = new RaviLogger(ModuleManager.class.getName());

    private static HashMap<String, Module> modulesMap = new HashMap<>();
    // modulesMap is the main module list. Maps module code to module object.
    private static HashMap<String, Module> nusModsMap = new HashMap<>();
    // nusModsMap is the module list containing the Module objects created from NUSMods' JSON file of modules.

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
     * @param newModuleCode
     *  The new module code of the new module that replaces the old one.
     * @param oldModuleCode
     *  The module code of the module to be edited.
     * @return
     *  The module object after editing.
     * @throws ModuleNotProvidedException
     *  If there is no module with the new module code offered by NUS
     * @throws DuplicateModuleException
     *  If there are duplicate modules with the same module code as the new module code in the Module List
     * @throws ModuleNotFoundException
     *  If the old module is not found
     */
    public static Module edit(String newModuleCode, String oldModuleCode)
            throws ModuleNotProvidedException, DuplicateModuleException, ModuleNotFoundException {
        logger.getLogger().info("Editing module at old module code: " + oldModuleCode);

        if (!doesContainMod(oldModuleCode)) {
            logger.getLogger().warning("Old module code not found!");
            throw new ModuleNotFoundException();
        }
        add(newModuleCode);
        Module oldModule = getModule(oldModuleCode);
        if (oldModule.getModuleCredit() != 0 && oldModule.getModuleGrade() != null) {
            grade(newModuleCode, oldModule.getModuleGrade(), oldModule.getModuleCredit());
        }
        delete(oldModuleCode);
        return getModule(newModuleCode);
    }

    /**
     * Checks if the module to be graded is in the moduleMap and assigns the grade to the module.
     *
     * @param moduleCode
     * Module code of the module to be graded
     * @param grade
     * The grade to be assigned
     * @param moduleCredit
     * The module's assigned module credit
     * @throws ModuleNotFoundException
     * If there is no such module in the user's module list
     */
    public static void grade(String moduleCode, String grade, double moduleCredit) throws ModuleNotFoundException {
        if (doesContainMod(moduleCode)) {
            getModule(moduleCode).setModuleGrade(grade);
            getModule(moduleCode).setModuleCredit(moduleCredit);
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
    public static boolean doesContainMod(String moduleCode) {
        for (String eachCode : modulesMap.keySet()) {
            if (eachCode.equalsIgnoreCase(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a module to the Module List. Uses the module code to grab the module object from the NUSMods list.
     * @param moduleCode
     *  The module code string of the module to add to the module list
     * @throws DuplicateModuleException
     *  When the module to be added already exists in the module list
     * @throws ModuleNotProvidedException
     *  When the module to be added is not a valid NUS module
     */
    public static void add(String moduleCode) throws DuplicateModuleException, ModuleNotProvidedException {
        logger.getLogger().info("Adding module with code: " + moduleCode);
        if (doesContainMod(moduleCode)) {
            logger.getLogger().warning("Can't add module because it already exists!");
            throw new DuplicateModuleException();
        }
        Module verifiedNusMod = getNusModule(moduleCode);
        modulesMap.put(moduleCode, verifiedNusMod);
    }

    /**
     * Removes a module from the Module List using the module code.
     * @param moduleCode
     *  The module code of the module to remove from the module list
     * @throws ModuleNotFoundException
     *  When the module to be deleted is not in the module list
     */
    public static void delete(String moduleCode) throws ModuleNotFoundException {
        logger.getLogger().info("Deleting module with code: " + moduleCode);
        if (!doesContainMod(moduleCode)) {
            logger.getLogger().warning("Can't delete module because it doesn't exist!");
            throw new ModuleNotFoundException();
        }
        modulesMap.remove(moduleCode);
    }

    /**
     *  Finds a module with the specified module code in the NUSMods Module List.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotProvidedException
     *  If the module is not found in the Module List
     */
    public static Module getNusModule(String moduleCode) throws ModuleNotProvidedException {
        for (Module module : nusModsMap.values()) {
            if (module.getModuleCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
        throw new ModuleNotProvidedException();
    }

    public static String[] getModCodeList() {
        return modulesMap.keySet().toArray(new String[0]);
    }


    public static String[] getNusModCodeList() {
        return nusModsMap.keySet().toArray(new String[0]);
    }

    /**
     * List modules in the module map.
     *
     * @return
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

}
