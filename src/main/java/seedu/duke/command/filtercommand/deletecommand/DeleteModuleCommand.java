package seedu.duke.command.filtercommand.deletecommand;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.ModuleManager;
import seedu.duke.directory.Module;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;

import static seedu.duke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_NO_MODULES_FOUND;
import static seedu.duke.util.Message.MESSAGE_NO_MODULES_TO_SHOW;

public class DeleteModuleCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delm";
    public static final String FORMAT = COMMAND_WORD + " [ <module keyword> ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Filter and show module(s)\n"
                    + "Note: -e to filter for exact keywords\n"
                    + "Format: %s\n"
                    + "Example: delm CS1231 \n",
            COMMAND_WORD, FORMAT);
    private final String moduleCode;
    /**
     * Constructor to delete task from task list.
     *
     * @param moduleCode module code
     */
    public DeleteModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
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
        try{
            ModuleManager.remove(moduleCode);
        } catch (ModuleManager.ModuleNotFoundException e){
            return new CommandResult(MESSAGE_NO_MODULES_FOUND);
        }
        return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS);
    }
}
