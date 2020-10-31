package seedu.duke.command.addcommand;

import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.IncorrectDirectoryLevelException;
import seedu.duke.util.DateTime;

import java.util.regex.Pattern;

import static seedu.duke.parser.Parser.DEADLINE_PREFIX;
import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.Message.messageAddTaskSuccess;

public class AddTaskCommand extends AddCommand {
    /** addt read a book */
    public static final String COMMAND_WORD = "addt";
    public static final Pattern REGEX_FORMAT = Pattern.compile(
            "(?<identifier>(?:\\s+\\w\\S*)*)"
            +  "(?<deadline>(?:\\s+" + DEADLINE_PREFIX + "(?:\\s+\\w\\S*)+)?)"
    );
    private String description;
    private DateTime deadline;


    public AddTaskCommand(String description, DateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        try {
            Module parentModule = DirectoryTraverser.getBaseModule();
            Task toAdd = new Task(parentModule, description, deadline);
            parentModule.getTasks().add(toAdd);
            return new CommandResult(messageAddTaskSuccess(description));
        } catch (TaskManager.DuplicateTaskException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IncorrectDirectoryLevelException e) {
            return new CommandResult(MESSAGE_INCORRECT_DIRECTORY_LEVEL);
        }
    }
}
