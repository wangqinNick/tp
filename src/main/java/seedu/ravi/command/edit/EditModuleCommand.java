package seedu.ravi.command.edit;

import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.Module;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.exception.DuplicateModuleException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;

import java.util.regex.Pattern;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_MODULE_NOT_PROVIDED;
import static seedu.ravi.util.Message.MESSAGE_EDIT_MODULE_SUCCESS;

public class EditModuleCommand extends EditCommand {
    public static final String FORMAT = EditCommand.COMMAND_WORD + " -m" + " <module_code> <new_module_code>";
    public static final String HELP =   "Edit a module code from the module list."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + FORMAT
                                        + "\n\t@|bold,blue,BG_BLACK Example usage:|@ edit -m CS2113 CS2113T";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(edit)) -m(?<moduleCode>(?:\\s+\\w\\S*))(?<moduleCode1>(?:\\s+\\w\\S*))"
    );

    private final String oldModuleCode;
    private final String newModuleCode;

    /**
     * Constructs the command to edit a module.
     *
     * @param oldModuleCode
     *  The module code of the module to be edited
     * @param newModuleCode
     *  The new module code for the module if any
     */
    public EditModuleCommand(String oldModuleCode, String newModuleCode) {
        this.oldModuleCode = oldModuleCode.toUpperCase();
        this.newModuleCode = newModuleCode.toUpperCase();
        setPromptType(PromptType.EDIT);
    }

    /**
     * Executes the <b>Edit Module Command</b> to edit a <b>Module</b> with the <code>module code</code>
     * from the <b>Module List</b>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see Module
     * @see ModuleManager
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        try {
            Module oldModule = ModuleManager.getModule(oldModuleCode);
            Module editedModule = ModuleManager.edit(newModuleCode, oldModuleCode);
            String message = String.format(
                    MESSAGE_EDIT_MODULE_SUCCESS, oldModule.toString(), editedModule.toString());
            return new CommandResult(message);
        } catch (ModuleNotProvidedException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_PROVIDED, true);
        } catch (DuplicateModuleException e) {
            return new CommandResult(MESSAGE_DUPLICATE_MODULE, true);
        } catch (ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND, true);
        }
    }
}