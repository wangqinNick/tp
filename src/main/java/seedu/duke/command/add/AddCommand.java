package seedu.duke.command.add;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.ModuleManager.DuplicateModuleException;
import seedu.duke.data.Task;
import seedu.duke.data.Module;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.Message.MESSAGE_ADD_MODULE_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_ADD_TASK_SUCCESS;
import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;

public class AddCommand extends Command {
    private Parser.TypeOfEntries typeOfEntry;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    private String desc;
    private LocalDateTime dateTimeOfDeadline;
    public static final String COMMAND_WORD = "add";
    public static final String FORMAT = COMMAND_WORD + " <opt> <args>";

    /**
     * Constructs AddCommand and tests the format of the deadline.
     *
     * @param typeOfEntry Type of entry that the user wants to add.
     * @param desc Description of the entry.
     * @param deadline Deadline of the task to be added.
     * @throws DateTimeParseException If the deadline does not follow the DateTime format.
     */
    public AddCommand(Parser.TypeOfEntries typeOfEntry, String desc, String deadline) throws DateTimeParseException {
        this.typeOfEntry = typeOfEntry;
        this.desc = desc;
        if (typeOfEntry.equals(Parser.TypeOfEntries.TASK) && (deadline != null)) {
            dateTimeOfDeadline = testDeadline(deadline);
        }
        this.promptType = PromptType.EDIT;
    }

    /**
     * Test if the deadline of the task follows the DateTimeFormatter.
     *
     * @param deadline Details of the deadline.
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
     * Add the Module to the module list.
     *
     * @param desc Description of the module to be added.
     * @throws DuplicateModuleException if the module is already in the list
     */
    private void addModule(String desc) throws DuplicateModuleException {
        Module newModule = new Module(desc);
        ModuleManager.add(newModule);
    }

    /**
     * Adds the task to their respective lists.
     *
     * @return CommandResult containing acknowledgement of the add or messages from exceptions.
     */
    @Override
    public CommandResult execute() {
        String message = "";
        try {
            switch (typeOfEntry) {
            case TASK:
                addTask(desc, dateTimeOfDeadline);
                message = MESSAGE_ADD_TASK_SUCCESS;
                break;
            case MODULE:
                addModule(desc);
                message = MESSAGE_ADD_MODULE_SUCCESS;
                break;
            default:
                message = MESSAGE_INVALID_PARAMETERS;
                break;
            }
            return new CommandResult(message);
        } catch (ModuleManager.DuplicateModuleException e) {
            return new CommandResult(MESSAGE_DUPLICATE_MODULE);
        }
    }
}