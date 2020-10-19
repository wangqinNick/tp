package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.timetable.TimeTableCommand;
import seedu.duke.data.TimeTableType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.data.TimeTableType.DAY;
import static seedu.duke.data.TimeTableType.WEEK;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public abstract class TimeTableCommandParser {
    protected static final Pattern TIMETABLE_FORMAT = Pattern.compile("(?<commandFlag>-\\S+)");

    protected static Command parseTimeTableCommand(String parameters) {
        Matcher matcher = TIMETABLE_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, TimeTableCommand.FORMAT));
        }
        String commandFlag = Parser.isMatcherNull(matcher.group(Parser.COMMAND_FLAG_GROUP))
                ? null : matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();
        TimeTableType typeOfTimeTable;
        switch (commandFlag) {
        case "-d":
            typeOfTimeTable = DAY;
            break;
        case "-w":
            typeOfTimeTable = WEEK;
            break;
        default:
            int days = Integer.parseInt(commandFlag.substring(1));
            return new TimeTableCommand(days);
        }
        return new TimeTableCommand(typeOfTimeTable);
    }
}
