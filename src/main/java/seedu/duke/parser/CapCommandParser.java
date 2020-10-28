package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.cap.CapCommand;
import seedu.duke.exception.InvalidMatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapCommandParser {
    protected static final String TOTAL_MC_GROUP = "totalMc";
    protected static final String CAP_GROUP = "cap";
    protected static final Pattern CUMULATIVE_CAP_FORMAT =
            Pattern.compile("(?<totalMc>\\d+)" + "(?<cap>\\s[0-9.]+)");

    /**
     * Takes the user's input and parses it into the respective arguments for CapCommand.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * CapCommand with the relevant parameters
     * @throws NumberFormatException
     * When a string is parsed as an integer/double
     * @throws NullPointerException
     * referenced object is accessed by its a null
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the Cap Command
     */
    protected static Command prepareCapCommand(String parameters)
            throws NumberFormatException, NullPointerException, InvalidMatchException {
        Matcher matcher = CUMULATIVE_CAP_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher,parameters, CapCommand.FORMAT, CapCommand.PROMPT_HELP);

        String mc = Parser.isMatcherNull(matcher.group(TOTAL_MC_GROUP))
                ? null : matcher.group(TOTAL_MC_GROUP).trim();
        String cap = Parser.isMatcherNull(matcher.group(CAP_GROUP))
                ? null : matcher.group(CAP_GROUP).trim();

        int totalMcTaken = Integer.parseInt(mc);
        double currentCap = Double.parseDouble(cap);

        return new CapCommand(totalMcTaken,currentCap);
    }
}
