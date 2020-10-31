package seedu.duke.command.edit;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.TaskManager;
import seedu.duke.util.ExceptionMessage;

import static seedu.duke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;


public class EditTaskCommand extends EditCommand {

    private final int taskID;
    private final String newTaskDescription;
    public static final String FORMAT = EditCommand.COMMAND_WORD + " -t" + " <task_index> <task_name>";
    public static final String HELP =   "Edit a task description from the task list."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: edit -t 1 Project meeting\n\n";

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
        setPromptType(PromptType.EDIT);
    }

    protected void edit() throws IndexOutOfBoundsException {
        TaskManager.getTaskList().get(taskID).setName(newTaskDescription);
    }

    @Override
    public CommandResult execute() {
        try {
            edit();
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(ExceptionMessage.MESSAGE_NO_EDIT_TASK);
        }
    }
}
