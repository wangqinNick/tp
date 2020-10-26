package seedu.duke.command.add;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;

import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_PROVIDED;
import static seedu.duke.util.Message.MESSAGE_ADD_MODULE_SUCCESS;

public class AddModuleCommand extends AddCommand {
    private String module;

    /**
     * Constructs AddModuleCommand.
     *
     * @param module Module code to be added.
     */
    public AddModuleCommand(String module) {
        this.module = module;
        this.promptType = PromptType.EDIT;
    }

    /**
     * Add the Module to the module list.
     *
     * @param module Module code to be added.
     * @throws ModuleManager.DuplicateModuleException if the module is already in the list
     */
    private void addModule(String module) throws ModuleManager.DuplicateModuleException,
            ModuleManager.ModuleNotFoundException {
        Module newModule = new Module(module);
        ModuleManager.add(newModule);
    }

    /**
     * Executes the AddModuleCommand to add the module to the module list.
     *
     * @return CommandResult containing acknowledgement of the add module or messages from exceptions.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        try {
            addModule(module);
            message = MESSAGE_ADD_MODULE_SUCCESS;
        } catch (ModuleManager.DuplicateModuleException e) {
            message = MESSAGE_DUPLICATE_MODULE;
        } catch (ModuleManager.ModuleNotFoundException e) {
            message = MESSAGE_MODULE_NOT_PROVIDED;
        }
        return new CommandResult(message);
    }
}
