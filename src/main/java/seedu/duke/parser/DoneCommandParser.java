package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.done.DoneCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoneCommandParser {
    protected static final String NUMBER_GROUP = "digit";
    protected static final Pattern DONE_FORMAT = Pattern.compile("(?<digit>.*)");

    protected static Command prepareDoneCommand(String parameters)
            throws NumberFormatException, InvalidParameterException {
        Matcher matcher = DONE_FORMAT.matcher(parameters);
        matcher.matches();

        String digit = Parser.isMatcherNull(matcher.group(NUMBER_GROUP))
                ? null : matcher.group(NUMBER_GROUP).trim();

        if (digit == null) {
            throw new InvalidParameterException();
        }

        int intDigit = Integer.parseInt(digit);

        return new DoneCommand(intDigit);
    }

}
