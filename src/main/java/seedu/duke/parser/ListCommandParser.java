package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.list.ListCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final Pattern LIST_FORMAT =
            Pattern.compile("(?<commandFlag>.*-\\S+)");

    protected static Command getListCommand(String parameters) {
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
