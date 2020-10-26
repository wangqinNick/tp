package seedu.duke.command.filtercommand.listcommand;

import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.directory.DirectoryLevel;
import seedu.duke.directory.Module;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

import static seedu.duke.util.Message.MESSAGE_NO_MODULES_TO_SHOW;
import static seedu.duke.util.Message.MESSAGE_SHOW_LIST;

public class ListModuleCommand extends ListCommand {
    public static final String COMMAND_WORD = "lsm";
    public static final String FORMAT = COMMAND_WORD ;
    public static final String MESSAGE_USAGE = String.format(
            "%s -show module(s)\n"
                    + "Format: %s\n"
                    + "Example: lsm\n",
            COMMAND_WORD, FORMAT);

    private String moduleKeyWord;
    private boolean isExact;

    /**
     * Constructs the command to list modules.
     *
     * @param moduleKeyWord
     *  The keyword to filter the modules
     * @param isExact
     *  Checks if modules are to be filtered exactly
     */
    public ListModuleCommand(String moduleKeyWord, boolean isExact) {
        this.moduleKeyWord = moduleKeyWord;
        this.isExact = isExact;
    }

    /**
     * Executes the <b>List Module Command</b> to show a filtered list of modules.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Module
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        ArrayList<Module> filteredModuleList;
        if (moduleKeyWord.isEmpty()){
            filteredModuleList = ModuleManager.getModuleList();
        } else {
            filteredModuleList = createFilteredModuleList(moduleKeyWord, isExact);
            if (filteredModuleList.isEmpty()) {
                return new CommandResult(MESSAGE_NO_MODULES_TO_SHOW);
            }
            sortModuleList(filteredModuleList);
        }
        var listMessage = TextUi.getAppendedModules(filteredModuleList);
        return new CommandResult(listMessage);
    }
}

