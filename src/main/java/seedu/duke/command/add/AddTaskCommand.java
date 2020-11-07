//@@author aseanseen
package seedu.duke.command.add;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.duke.util.Message.MESSAGE_ADD_TASK_SUCCESS;

public class AddTaskCommand extends AddCommand {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    private final String desc;
    private LocalDateTime dateTimeOfDeadline;
    public static final String FORMAT = COMMAND_WORD + " -t <task_name> [-by <deadline>]";
    public static final String HELP =   "Add a task to the scheduler."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: add -t Read Book"
                                        + "\n\t               add -t Return Book -by 30-12-2020 1800\n\n";

    /**
     * Constructs AddTaskCommand without the deadline.
     *
     * @param desc Description of the entry.
     * @throws DateTimeParseException If the deadline does not follow the DateTime format.
     */
    public AddTaskCommand(String desc) {
        this.desc = desc;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Constructs AddTaskCommand and tests the format of the deadline.
     *
     * @param desc Description of the entry.
     * @param deadline Deadline of the task to be added.
     * @throws DateTimeParseException If the deadline does not follow the DateTime format.
     */
    public AddTaskCommand(String desc, String deadline) throws DateTimeParseException {
        this.desc = desc;
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

    /**
     * Add the Task with deadline to the task list.
     *
     * @param desc Description of the task to be added.
     * @param dateTimeOfDeadline LocalDateTime of the deadline.
     * @return newTask The new task object that was added.
     */
    private Task addTask(String desc, LocalDateTime dateTimeOfDeadline) {
        Task newTask;
        // Option for user to input a deadline
        if (dateTimeOfDeadline == null) {
            newTask = new Task(desc);
        } else {
            newTask = new Task(desc, dateTimeOfDeadline);
        }
        TaskManager.add(newTask);
        return newTask;
    }

    /**
     * Executes the AddTaskCommand to add the task to the task list.
     *
     * @return CommandResult containing acknowledgement of the add task or messages from exceptions.
     */
    @Override
    public CommandResult execute() {
        String message;
        Task addedTask = addTask(desc, dateTimeOfDeadline);

        message = String.format(MESSAGE_ADD_TASK_SUCCESS, addedTask.toString());
        return new CommandResult(message);
    }
}
