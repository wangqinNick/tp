package seedu.duke.command.delete;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.ModuleManager;

import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;

public class DeleteModuleCommand extends DeleteCommand {
    private String moduleCode;

    /**
     * Constructor to delete module from module list.
     *
     * @param moduleCode Module code to be deleted.
     */
    public DeleteModuleCommand(String moduleCode) {
        this.moduleCode = moduleCode;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Deletes the module from the module list.
     *
     * @param moduleCode Module code to be deleted.
     * @throws ModuleManager.ModuleNotFoundException If the module is not found in the module list.
     */
    private void deleteModule(String moduleCode) throws ModuleManager.ModuleNotFoundException {
        ModuleManager.delete(moduleCode);
    }

    /**
     * Deletes the module from the module list.
     *
     * @return CommandResult containing acknowledgement of the delete or errors.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        try {
            deleteModule(moduleCode);
            message = MESSAGE_DELETE_MODULE_SUCCESS;
        } catch (ModuleManager.ModuleNotFoundException e) {
            message = MESSAGE_MODULE_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
