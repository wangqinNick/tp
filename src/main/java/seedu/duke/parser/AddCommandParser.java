package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_TASK;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_MODULE;

public class AddCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final String COMMAND_FLAG_GROUP = "commandFlag";
    protected static final String TASK_GROUP = "task";
    protected static final String DEADLINE_GROUP = "deadline";
    protected static final String BY_GROUP = "by";
    protected static final Pattern ADD_FORMAT =
            Pattern.compile("(?<commandFlag>-\\S+)" + "(?<task>[^-]*)" + "((?<by>-by)?)" + "((?<deadline>.*)?)");

    protected static Command prepareAddCommand(String parameters)
            throws InvalidParameterException, IllegalStateException {
        Matcher matcher = ADD_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
        }

        String commandFlag = Parser.isMatcherNull(matcher.group(COMMAND_FLAG_GROUP))
                ? null : matcher.group(COMMAND_FLAG_GROUP).toLowerCase().trim();

        String addedTask = matcher.group(TASK_GROUP).trim();
        String taskDeadline = null;
        // Checks for presence of -by
        String dashBy = matcher.group(BY_GROUP);
        if (dashBy != null) {
            taskDeadline = matcher.group(DEADLINE_GROUP).trim();
            if (taskDeadline.isEmpty()) { // -by is present but empty deadline
                return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                        MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
            }
        }
        // no task input by user
        if (Parser.isEmptyParse(addedTask)) {
            return (commandFlag.equals(MODULE_PREFIX))
                    ? new IncorrectCommand(MESSAGE_NO_ADD_MODULE)
                    : new IncorrectCommand(MESSAGE_NO_ADD_TASK);
        }

        return getAddCommand(commandFlag, addedTask, taskDeadline);
    }

    private static AddCommand getAddCommand(String commandFlag, String addedTask, String taskDeadline) {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return new AddCommand(Parser.TypeOfEntries.MODULE, addedTask, taskDeadline);
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new AddCommand(Parser.TypeOfEntries.TASK, addedTask, taskDeadline);
        } else {
            throw new InvalidParameterException();
        }
    }
}
