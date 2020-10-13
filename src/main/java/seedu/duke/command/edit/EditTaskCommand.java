package seedu.duke.command.edit;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.Directory;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.Directory;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.TaskNotFoundException;
import seedu.duke.util.Message;

import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;


public class EditTaskCommand extends EditCommand {

    private int taskID;
    private String newTaskDescription;
    public static final String COMMAND_WORD = "edt";

    /**
     * Constructs the command to edit a task.
     *
     * @param taskID
     *  The ID of the task to be edited
     * @param newTaskDescription
     *  The new description of the task if any
     */
    public EditTaskCommand(int taskID, String newTaskDescription) {
        this.taskID = taskID;
        this.newTaskDescription = newTaskDescription;
    }

    protected void edit() throws TaskManager.TaskNotFoundException {
        TaskManager.edit(taskID, newTaskDescription);
    }

    @Override
    public CommandResult execute() {
        try {
            edit();
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(Message.MESSAGE_NO_EDIT_TASK);
        }
    }
}
