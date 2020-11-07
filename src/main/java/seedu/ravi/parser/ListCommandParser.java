package seedu.ravi.parser;

import seedu.ravi.command.Command;
import seedu.ravi.command.list.ListCommand;
import seedu.ravi.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final Pattern LIST_FORMAT =
            Pattern.compile("(?<commandFlag>.*-\\S+)");

    /**
     * Takes the user's input and parses it into the respective arguments for List Command.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * List Command with relevant arguments
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the List Command
     */
    protected static Command getListCommand(String parameters) throws InvalidMatchException {
        Matcher matcher = LIST_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, ListCommand.FORMAT, ListCommand.PROMPT_HELP);

        String commandFlag = Parser.isMatcherNull(matcher.group(Parser.COMMAND_FLAG_GROUP))
                ? null : matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();

        if (commandFlag.equals(MODULE_PREFIX)) {
            return new ListCommand(Parser.TypeOfEntries.MODULE);
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new ListCommand(Parser.TypeOfEntries.TASK);
        } else {
            throw new InvalidParameterException();
        }
    }
}
