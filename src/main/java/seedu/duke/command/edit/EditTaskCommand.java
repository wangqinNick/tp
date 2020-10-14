package seedu.duke.command.edit;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import seedu.duke.util.Message;

import static seedu.duke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;


public class EditTaskCommand extends EditCommand {

    private int taskID;
    private String newTaskDescription;
    public static final String COMMAND_WORD = Parser.COMMAND_WORD_EDIT + "-t";
    public static final String FORMAT = Parser.COMMAND_WORD_EDIT + "-t" + " <task ID> <new task description>";


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

    protected void edit() throws IndexOutOfBoundsException {
        TaskManager.getTaskList().get(taskID).setName(newTaskDescription);
    }

    @Override
    public CommandResult execute() {
        try {
            edit();
            return new CommandResult(MESSAGE_EDIT_TASK_SUCCESS);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Message.MESSAGE_NO_EDIT_TASK);
        }
    }
}
