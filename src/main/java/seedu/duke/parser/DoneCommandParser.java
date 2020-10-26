package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.done.DoneCommand;
import seedu.duke.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoneCommandParser {
    protected static final String NUMBER_GROUP = "digit";
    protected static final Pattern DONE_FORMAT = Pattern.compile("(?<digit>.*)");

    protected static Command prepareDoneCommand(String parameters)
            throws NumberFormatException, InvalidParameterException, InvalidMatchException {
        Matcher matcher = DONE_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, DoneCommand.FORMAT, DoneCommand.PROMPT_HELP);

        String digit = Parser.isMatcherNull(matcher.group(NUMBER_GROUP))
                ? null : matcher.group(NUMBER_GROUP).trim();

        if (digit == null) {
            throw new InvalidParameterException();
        }

        int intDigit = Integer.parseInt(digit) - 1;

        return new DoneCommand(intDigit);
    }

}
