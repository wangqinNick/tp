//@@author tobiasceg

package seedu.ravi.parser;

import seedu.ravi.command.Command;
import seedu.ravi.command.help.HelpCommand;
import seedu.ravi.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpCommandParser {
    protected static final String HCW_GROUP = "helpCommandWord";
    protected static final Pattern HELP_FORMAT =
            Pattern.compile("((?<helpCommandWord>\\S+)?)");

    /**
     * Takes the user's input and parses it into the respective arguments for Help Command.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * Help Command with relevant arguments
     * @throws InvalidParameterException
     * When the user inputs parameters that arent accepted by the commands format
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the Help Command
     */
    protected static Command prepareHelpCommand(String parameters)
            throws InvalidParameterException, InvalidMatchException {
        Matcher matcher = HELP_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, HelpCommand.FORMAT, HelpCommand.HELP);

        String helpCommandWord = Parser.isMatcherNull(matcher.group(HCW_GROUP))
                ? "genericHelp" : matcher.group(HCW_GROUP).trim();

        return new HelpCommand(helpCommandWord);
    }
}
