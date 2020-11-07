package seedu.ravi.command.add;

import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.Module;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.exception.DuplicateModuleException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_MODULE_NOT_PROVIDED;
import static seedu.ravi.util.Message.MESSAGE_ADD_MODULE_SUCCESS;

public class AddModuleCommand extends AddCommand {
    private final String module;
    public static final String FORMAT = COMMAND_WORD + " -m <module_code>";
    public static final String HELP =   "Add a module from NUSMods to the scheduler."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: add -m CS2113T";

    /**
     * Constructs AddModuleCommand.
     *
     * @param module Module code to be added.
     */
    public AddModuleCommand(String module) {
        this.module = module.toUpperCase();
        setPromptType(PromptType.EDIT);
    }

    /**
     * Add the Module to the module list.
     *
     * @param module Module code to be added.
     * @throws DuplicateModuleException if the module is already in the list
     */
    private Module addModule(String module) throws
            DuplicateModuleException, ModuleNotProvidedException, ModuleNotFoundException {
        ModuleManager.add(module);
        return ModuleManager.getModule(module);
    }

    /**
     * Executes the AddModuleCommand to add the module to the module list.
     *
     * @return CommandResult containing acknowledgement of the add module or messages from exceptions.
     */
    @Override
    public CommandResult execute() {
        try {
            Module newModule = addModule(module);
            return new CommandResult(String.format(MESSAGE_ADD_MODULE_SUCCESS, newModule.toString()));
        } catch (DuplicateModuleException e) {
            return new CommandResult(MESSAGE_DUPLICATE_MODULE, true);
        } catch (ModuleNotProvidedException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_PROVIDED, true);
        } catch (ModuleNotFoundException e) {
            // should not happen
            return new CommandResult("AddModuleCommand caught ModuleNotFoundException", true);
        }
    }
}
