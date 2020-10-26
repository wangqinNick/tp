package seedu.duke.command.filtercommand;

import seedu.duke.command.Command;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.DirectoryLevel;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

public abstract class FilterCommand extends Command {
    protected ArrayList<Module> createFilteredModuleList(String moduleKeyword, boolean isExact) {
        return filterModules(moduleKeyword, isExact);
    }

    private ArrayList<Module> filterModules(String moduleKeyword, boolean isExact) {
        return isExact ? ModuleManager.filterExact(moduleKeyword) : ModuleManager.filter(moduleKeyword);
    }

    /**
     * Sorts modules in a list by their module codes.
     * @param toSort
     *  The list of modules to be sorted
     */
    protected void sortModuleList(ArrayList<Module> toSort) {
        Comparator<Module> sortByModule =
                Comparator.comparing(Module::getModuleCode);

        toSort.sort(sortByModule);
    }
}
