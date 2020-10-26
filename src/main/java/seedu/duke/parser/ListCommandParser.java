package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
<<<<<<< HEAD
=======
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.grade.GradeCommand;
>>>>>>> e6b5d898618635cfd869bc2418f58e426745af6f
import seedu.duke.command.list.ListCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class ListCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final Pattern LIST_FORMAT =
            Pattern.compile("(?<commandFlag>.*-\\S+)");

    protected static Command getListCommand(String parameters) {
        Matcher matcher = LIST_FORMAT.matcher(parameters);
<<<<<<< HEAD
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n%s\n\n%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    ListCommand.FORMAT_TASK, ListCommand.FORMAT_MODULE, ListCommand.PROMPT_HELP));
        }
=======

        Parser.matcherMatches(matcher, parameters, ListCommand.FORMAT);
>>>>>>> e6b5d898618635cfd869bc2418f58e426745af6f

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
