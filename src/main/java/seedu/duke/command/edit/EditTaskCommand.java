package seedu.duke.command.edit;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.Directory;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.TaskNotFoundException;
import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;

public class EditTaskCommand extends EditCommand {

    private int taskID;
    private String newTaskDescription;

    /**
     * Constructs the command to edit a task.
     *
     * @param taskID
     *  The ID of the task to be edited
     * @param newTaskDescription
     *  The new description of the task if any
     */
    public EditTaskCommand(int taskID,
                           String newTaskDescription) {
        this.taskID = taskID;
        this.newTaskDescription = newTaskDescription;
    }

    @Override
    protected void edit(Directory toEdit) throws DuplicateDataException {
        TaskManager.edit((Task) toEdit, newTaskDescription);
    }

    @Override
    public CommandResult execute() {
        try {
            Task toEdit = TaskManager.get(taskID);
            edit(toEdit);
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        } catch (DuplicateDataException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        }
    }
}
