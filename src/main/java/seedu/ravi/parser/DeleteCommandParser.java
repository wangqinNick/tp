package seedu.ravi.parser;

import seedu.ravi.command.Command;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.command.delete.DeleteCommand;
import seedu.ravi.command.delete.DeleteModuleCommand;
import seedu.ravi.command.delete.DeleteTaskCommand;
import seedu.ravi.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ravi.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.ravi.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final String TASK_MODULE_GROUP = "taskModule";
    protected static final String INVALID_GROUP = "invalid";
    protected static final Pattern DELETE_FORMAT =
            Pattern.compile("((?<commandFlag>.*-\\S+)?)"
                    + "(?<taskModule>\\s\\S+)" + "((?<invalid>.*)?)");

    /**
     * Takes the user's input and parses it into the respective arguments for Delete Command.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * Delete command with relevant arguments
     * @throws NumberFormatException
     * When a string is parsed as an integer/double
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the Delete Command
     */
    protected static Command getDeleteCommand(String parameters) throws NumberFormatException, InvalidMatchException {
        Matcher matcher = DELETE_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, DeleteCommand.FORMAT, DeleteCommand.PROMPT_HELP);

        String commandFlag = Parser.isMatcherNull(matcher.group(Parser.COMMAND_FLAG_GROUP))
                ? null : matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();

        String stringTaskIndex = matcher.group(TASK_MODULE_GROUP).trim();

        // Checks for any string after the module or index given
        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n\n%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT,
                    DeleteCommand.FORMAT, DeleteCommand.PROMPT_HELP));
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
