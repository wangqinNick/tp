package seedu.duke.command.add;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.duke.util.Message.MESSAGE_ADD_TASK_SUCCESS;

public class AddTaskCommand extends AddCommand {
    private Parser.TypeOfEntries typeOfEntry;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    private String desc;
    private LocalDateTime dateTimeOfDeadline;

    /**
     * Constructs AddTaskCommand without the deadline.
     *
     * @param desc Description of the entry.
     * @param deadline Deadline of the task to be added.
     * @throws DateTimeParseException If the deadline does not follow the DateTime format.
     */
    public AddTaskCommand(String desc) {
        this.typeOfEntry = typeOfEntry;
        this.desc = desc;
        this.promptType = PromptType.EDIT;
    }

    /**
     * Constructs AddTaskCommand and tests the format of the deadline.
     *
     * @param desc Description of the entry.
     * @param deadline Deadline of the task to be added.
     * @throws DateTimeParseException If the deadline does not follow the DateTime format.
     */
    public AddTaskCommand(String desc, String deadline) throws DateTimeParseException {
        this.typeOfEntry = typeOfEntry;
        this.desc = desc;
        if (deadline != null) {
            dateTimeOfDeadline = testDeadline(deadline);
        }
        this.promptType = PromptType.EDIT;
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
     */
    private void addTask(String desc, LocalDateTime dateTimeOfDeadline) {
        Task newTask;
        // Option for user to input a deadline
        if (dateTimeOfDeadline == null) {
            newTask = new Task(desc);
        } else {
            newTask = new Task(desc, dateTimeOfDeadline);
        }
        TaskManager.add(newTask);
    }

    /**
     * Adds the task to the task list.
     *
     * @return CommandResult containing acknowledgement of the add task or messages from exceptions.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        addTask(desc, dateTimeOfDeadline);
        message = MESSAGE_ADD_TASK_SUCCESS;
        return new CommandResult(message);
    }
}
