package seedu.duke.command.done;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.TaskManager;
import seedu.duke.ui.TextUi;

import static seedu.duke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DONE_TASK_SUCCESS;

public class DoneCommand extends Command {
    private final int taskId;
    public static final String COMMAND_WORD = "done";
    public static final String FORMAT = COMMAND_WORD + " <task_index>";
    public static final String HELP =   "Mark a task as done."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: done 1";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);

    public DoneCommand(int taskId) {
        this.taskId = taskId;
        this.promptType = PromptType.EDIT;
    }

    /**
     * Marks the task from the task list as done.
     *
     * @param taskId id of task.
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private void doneTask(int taskId) throws TaskManager.TaskNotFoundException {
        TaskManager.done(taskId);
    }

    /**
     * Marks the task from the task list as done.
     *
     * @return CommandResult containing acknowledgement of marking task as done.
     */
    @Override
    public CommandResult execute() {
        String message;
        try {
            doneTask(taskId);
            message = MESSAGE_DONE_TASK_SUCCESS;
            return new CommandResult(message);
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND);
        }
    }
}
