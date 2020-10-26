package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.delete.DeleteCommand;
import seedu.duke.command.delete.DeleteModuleCommand;
import seedu.duke.command.delete.DeleteTaskCommand;
import seedu.duke.command.grade.GradeCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final String TASK_MODULE_GROUP = "taskModule";
    protected static final String INVALID_GROUP = "invalid";
    protected static final Pattern DELETE_FORMAT =
            Pattern.compile("((?<commandFlag>.*-\\S+)?)"
                    + "(?<taskModule>\\s\\S+)" + "((?<invalid>.*)?)");

    protected static Command getDeleteCommand(String parameters) throws NumberFormatException {
        Matcher matcher = DELETE_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, DeleteCommand.FORMAT);

        String commandFlag = Parser.isMatcherNull(matcher.group(Parser.COMMAND_FLAG_GROUP))
                ? null : matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();

        String stringTaskIndex = matcher.group(TASK_MODULE_GROUP).trim();

        // Checks for any string after the module or index given
        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, DeleteCommand.FORMAT));
        }

        if (commandFlag.equals(MODULE_PREFIX)) {
            return new DeleteModuleCommand(stringTaskIndex); //parameter is module code
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new DeleteTaskCommand(Integer.parseInt(stringTaskIndex) - 1);//parameters is the index
        } else {
            throw new InvalidParameterException();
        }
    }
}
