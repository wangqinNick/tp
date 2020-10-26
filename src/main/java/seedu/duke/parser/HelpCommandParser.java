package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.help.HelpCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelpCommandParser {
    protected static final String HCW_GROUP = "helpCommandWord";
    protected static final Pattern HELP_FORMAT =
            Pattern.compile("((?<helpCommandWord>\\S+)?)");

    protected static Command prepareHelpCommand(String parameters) throws InvalidParameterException {
        Matcher matcher = HELP_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, HelpCommand.FORMAT);

        String helpCommandWord = Parser.isMatcherNull(matcher.group(HCW_GROUP))
                ? "genericHelp" : matcher.group(HCW_GROUP).trim();

        return new HelpCommand(helpCommandWord);
    }
}
