package seedu.duke.command.editcommand;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.IncorrectDirectoryLevelException;
import seedu.duke.exception.ModuleNotProvidedException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.duke.util.Message.MESSAGE_ALREADY_DONE_TASK;
import static seedu.duke.util.Message.MESSAGE_DONE_TASK;
import static seedu.duke.util.Message.MESSAGE_INVALID_DELETE_INDICES;
import static seedu.duke.util.Message.MESSAGE_INVALID_DONE_INDICES;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private final int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Module module = (Module) DirectoryTraverser.getCurrentDirectory();
            Task toMarkAsDone = module.getTasks().getTask(taskIndex);
            if (toMarkAsDone.isDone()) {
                return new CommandResult(MESSAGE_ALREADY_DONE_TASK);
            }
            toMarkAsDone.setDone(true);
            assert toMarkAsDone.isDone() : "How can this be?";
            return new CommandResult(MESSAGE_DONE_TASK);
        } catch (IndexOutOfBoundsException e){
            return new CommandResult(MESSAGE_INVALID_DONE_INDICES);
        }
    }
}
