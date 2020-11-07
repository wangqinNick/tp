//@@author tobiasceg

package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.grade.GradeCommand;
import seedu.duke.exception.InvalidMatchException;
import seedu.duke.exception.InvalidModuleCreditException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradeCommandParser {
    protected static final String MODULE_GROUP = "module";
    protected static final String GRADE_GROUP = "grade";
    protected static final String MC_GROUP = "mc";
    protected static final Pattern GRADE_FORMAT =
            Pattern.compile("(?<module>[a-zA-Z0-9]+)" + "(?<mc>\\s\\S+)" + "(?<grade>\\s\\S+)");

    /**
     * Takes the user's input and parses it into the respective arguments for Grade Command.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * Grade Command with relevant arguments
     * @throws NumberFormatException
     * When a string is parsed as an integer/double
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the Grade Command
     */
    protected static Command prepareGradeCommand(String parameters)
            throws NumberFormatException, InvalidMatchException, InvalidModuleCreditException {
        Matcher matcher = GRADE_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, GradeCommand.FORMAT, GradeCommand.PROMPT_HELP);

        String module = Parser.isMatcherNull(matcher.group(MODULE_GROUP))
                ? null : matcher.group(MODULE_GROUP).toLowerCase().trim();
        String mc = Parser.isMatcherNull(matcher.group(MC_GROUP))
                ? null : matcher.group(MC_GROUP).toLowerCase().trim();
        String grade = Parser.isMatcherNull(matcher.group(GRADE_GROUP))
                ? null : matcher.group(GRADE_GROUP).trim();

        double intMc = Double.parseDouble(mc);

        isValidMc(intMc);

        return new GradeCommand(module, intMc, grade);
    }

    private static void isValidMc(double intMc) throws InvalidModuleCreditException {
        if (intMc < 0 || intMc > 20) {
            throw new InvalidModuleCreditException();
        }
    }

}
