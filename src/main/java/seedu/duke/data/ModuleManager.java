package seedu.duke.data;

import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;

import java.util.HashMap;

public class ModuleManager {
    private static HashMap<String, Module> modulesMap = new HashMap<>(); // Main module list. Maps module code to module object.

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
        for (Module module : modulesMap.values()) {
            if (module.getCode().equalsIgnoreCase(moduleCode)) {
                return module;
            }
        }
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
        Module oldModule = modulesMap.get(oldModuleCode);
        if (!modulesMap.containsKey(oldModuleCode)) {
            throw new ModuleNotProvidedException();
        }
        if (oldModule.isSameModule(newModule)) {
            throw new DuplicateModuleException();
        }
        modulesMap.remove(oldModuleCode);
        modulesMap.put(newModule.getCode(), newModule);
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
        if (contains(newModule.getCode())) {
            throw new DuplicateModuleException();
        }
        modulesMap.put(newModule.getCode(), newModule);
    }

    /**
     * Removes a module from the Module List using the module code
     * @param moduleCode
     *  The module code of the module to remove from the module list
     */
    public static boolean delete(String moduleCode) throws ModuleNotFoundException {
        if (!contains(moduleCode)) {
            throw new ModuleNotFoundException();
        }
        modulesMap.remove(moduleCode);
        return false;
    }

    public static class ModuleNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateModuleException extends DuplicateDataException {
    }
}
