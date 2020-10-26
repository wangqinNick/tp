package seedu.duke.command.filtercommand.deletecommand;

import seedu.duke.command.CommandResult;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.DataNotFoundException;

import static seedu.duke.util.Message.MESSAGE_ALREADY_DONE_TASK;
import static seedu.duke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_DONE_TASK;
import static seedu.duke.util.Message.MESSAGE_INVALID_DELETE_INDICES;
import static seedu.duke.util.Message.MESSAGE_INVALID_DONE_INDICES;

public class DeleteTaskCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delt";
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Module module = (Module) DirectoryTraverser.getCurrentDirectory();
            Task toDelete = module.getTasks().getTask(taskIndex);
            module.getTasks().delete(toDelete);
            return new CommandResult(MESSAGE_DELETE_TASK_SUCCESS);
        } catch (IndexOutOfBoundsException e){
            return new CommandResult(MESSAGE_INVALID_DELETE_INDICES);
        }
    }
}
