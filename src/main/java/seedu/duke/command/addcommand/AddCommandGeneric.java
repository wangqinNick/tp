package seedu.duke.command.addcommand;

import seedu.duke.command.CommandResult;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.IncorrectDirectoryLevelException;

import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.duke.util.Message.messageAddTaskSuccess;

public abstract class AddCommandGeneric extends AddCommand {

    public static final String COMMAND_WORD = "add";
}
