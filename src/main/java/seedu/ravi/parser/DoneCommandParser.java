package seedu.ravi.parser;

import seedu.ravi.command.Command;
import seedu.ravi.command.done.DoneCommand;
import seedu.ravi.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoneCommandParser {
    protected static final String NUMBER_GROUP = "digit";
    protected static final Pattern DONE_FORMAT = Pattern.compile("(?<digit>\\S+)");

    /**
     * Takes the user's input and parses it into the respective arguments for Done Command.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * Done Command with relevant arguments
     * @throws NumberFormatException
     * When a string is parsed as an integer/double
     * @throws InvalidParameterException
     * Parameters entered by the user is invalid
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the Done Command
     */
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
