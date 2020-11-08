package seedu.ravi.command.done;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.Task;
import seedu.ravi.data.TaskManager;
import seedu.ravi.ui.TextUi;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.ravi.util.Message.MESSAGE_DONE_TASK_SUCCESS;

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
        setPromptType(PromptType.EDIT);
    }

    /**
     * Marks the task from the task list as done.
     *
     * @param taskId id of task.
     * @throws TaskManager.TaskNotFoundException If the task is not found in the task list.
     */
    private Task doneTask(int taskId) throws TaskManager.TaskNotFoundException {
        return TaskManager.done(taskId);
    }

    /**
     * Marks the task from the task list as done.
     *
     * @return CommandResult containing acknowledgement of marking task as done.
     */
    @Override
    public CommandResult execute() {
        try {
            Task completedTask = doneTask(taskId);
            return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, completedTask));
        } catch (TaskManager.TaskNotFoundException e) {
            return new CommandResult(MESSAGE_TASK_NOT_FOUND, true);
        }
    }
}
