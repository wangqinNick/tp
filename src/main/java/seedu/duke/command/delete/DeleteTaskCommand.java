package seedu.duke.command.delete;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.TaskManager;

import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;

public class DeleteTaskCommand extends DeleteCommand {
    private int taskId;
    public static final String FORMAT = DeleteCommand.COMMAND_WORD + " -t" + " <task ID>";
    public static final String HELP =   "Delete a task from the scheduler." +
                                        "\n\tFormat: " + FORMAT +
                                        "\n\tExample usage: delete 1\n\n";

    /**
     * Constructor to delete task from task list.
     *
     * @param taskId ID of the task to be deleted.
     */
    public DeleteTaskCommand(int taskId) {
        this.taskId = taskId;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Deletes the task from the task list.
     *
     * @param taskId ID of the task to be deleted.
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private void deleteTask(int taskId) throws TaskManager.TaskNotFoundException {
        TaskManager.delete(taskId);
    }

    /**
     * Deletes the task from the task list.
     *
     * @return CommandResult containing acknowledgement of the delete or errors.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        try {
            deleteTask(taskId);
            message = MESSAGE_DELETE_TASK_SUCCESS;
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
        return new CommandResult(message);
    }
}
