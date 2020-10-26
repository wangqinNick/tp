package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.grade.GradeCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class GradeCommandParser {
    protected static final String MODULE_GROUP = "module";
    protected static final String GRADE_GROUP = "grade";
    protected static final String MC_GROUP = "mc";
    protected static final Pattern GRADE_FORMAT =
            Pattern.compile("(?<module>[a-zA-Z0-9]+)" + "(?<mc>\\s\\S+)" + "(?<grade>.*)");

    protected static Command prepareGradeCommand(String parameters) throws NumberFormatException {
        Matcher matcher = GRADE_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, GradeCommand.FORMAT);

        String module = Parser.isMatcherNull(matcher.group(MODULE_GROUP))
                ? null : matcher.group(MODULE_GROUP).toLowerCase().trim();
        String mc = Parser.isMatcherNull(matcher.group(MC_GROUP))
                ? null : matcher.group(MC_GROUP).toLowerCase().trim();
        String grade = Parser.isMatcherNull(matcher.group(GRADE_GROUP))
                ? null : matcher.group(GRADE_GROUP).trim();

        int intMc = Integer.parseInt(mc);

        return new GradeCommand(module,intMc,grade);
    }

}
