package seedu.duke.data;

import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;

import java.util.ArrayList;
import java.util.HashMap;

public class ModuleManager {
    private static ArrayList<Module> moduleList;
    private static HashMap<String, String> modulesMap;

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

    public static class ModuleNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateModuleException extends DuplicateDataException {
    }
}
