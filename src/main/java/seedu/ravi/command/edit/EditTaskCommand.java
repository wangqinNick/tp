package seedu.ravi.command.edit;

import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.Task;
import seedu.ravi.data.TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_NO_EDIT_TASK;
import static seedu.ravi.util.Message.MESSAGE_EDIT_TASK_SUCCESS;


public class EditTaskCommand extends EditCommand {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    private final int taskId;
    private final String newTaskDescription;
    private LocalDateTime dateTimeOfDeadline;
    public static final String FORMAT = EditCommand.COMMAND_WORD + " -t <task_index> <task_name> [-by <deadline>]";
    public static final String HELP =   "Edit a task description from the task list."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + FORMAT
                                        + "\n\t@|bold,blue,BG_BLACK Example usage:|@ edit -t 1 Project meeting"
                                        + "\n\t@|bold,blue,BG_BLACK Example usage:|@ edit -t 1 Project meeting -by"
                                        + " 30-12-2020 1200\n\n";

    /**
     * Constructs the command to edit a task.
     *
     * @param taskId
     *  The ID of the task to be edited
     * @param newTaskDescription
     *  The new description of the task if any
     */
    public EditTaskCommand(int taskId, String newTaskDescription) {
        this.taskId = taskId;
        this.newTaskDescription = newTaskDescription;
        this.dateTimeOfDeadline = null;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Constructs AddTaskCommand and tests the format of the deadline.
     *
     * @param taskId
     *  The ID of the task to be edited
     * @param newTaskDescription
     *  The new description of the task if any
     * @param deadline
     *  The new deadline of the task if any
     * @throws DateTimeParseException
     *  If the deadline does not follow the DateTime format.
     */
    public EditTaskCommand(int taskId, String newTaskDescription, String deadline) throws DateTimeParseException {
        this.taskId = taskId;
        this.newTaskDescription = newTaskDescription;
        if (deadline != null) {
            dateTimeOfDeadline = testDeadline(deadline);
        }
        setPromptType(PromptType.EDIT);
    }

    /**
     * Test if the deadline of the task follows the DateTimeFormatter.
     *
     * @param deadline LocalDateTime deadline.
     * @throws DateTimeParseException if the deadline does not follow format.
     */
    private LocalDateTime testDeadline(String deadline) throws DateTimeParseException {
        LocalDateTime dateTimeOfDeadline;
        dateTimeOfDeadline = LocalDateTime.parse(deadline, formatter);
        return dateTimeOfDeadline;
    }

    protected Task edit() throws TaskManager.TaskNotFoundException {
        if (dateTimeOfDeadline == null) {
            return TaskManager.edit(taskId, newTaskDescription);
        } else {
            return TaskManager.edit(taskId, newTaskDescription, dateTimeOfDeadline);
        }
    }

    @Override
    public CommandResult execute() {
        try {
            String oldTask = TaskManager.getTask(taskId).toString();
            Task editedTask = edit();
            return new CommandResult(String.format(
                    MESSAGE_EDIT_TASK_SUCCESS, oldTask, editedTask.toString()));
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_NO_EDIT_TASK, true);
        }
    }
}
