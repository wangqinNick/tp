package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.timetable.TimeTableAddCommand;
import seedu.duke.command.timetable.TimeTableCommand;
import seedu.duke.command.timetable.TimeTableDeleteCommand;
import seedu.duke.command.timetable.TimeTableViewCommand;
import seedu.duke.data.Lesson;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.command.timetable.TimeTableCommand.TIMETABLE_LESSON_DELETE_USER_FORMAT;
import static seedu.duke.command.timetable.TimeTableCommand.TIMETABLE_LESSON_PARAMETER_USER_FORMAT;
import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_OVERLAP;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public abstract class TimeTableCommandParser {
    private static final String REPEAT_GROUP = "repeat";
    private static final String ADD_FORMAT = "-add";
    private static final String DELETE_FORMAT = "-del";
    private static final String FILTER_FORMAT = "-filter";
    private static final String VIEW_DAY_FORMAT = "-day";
    private static final String VIEW_WEEK_FORMAT = "-week";
    private static final Pattern TIMETABLE_FORMAT = Pattern.compile("(?<commandFlag>-[a-zA-Z0-9]+\\s*)");
    private static final Pattern TIMETABLE_LESSON_PARAMETER_FORMAT =
            Pattern.compile("(?<module>\\S+\\s*)(?<day>\\S+\\s*)(?<start>\\d+\\s*)(?<end>\\d+\\s*)"
            + "(?<type>\\S+\\s*)(?<repeat>\\d+\\s*)");
    private static final Pattern TIMETABLE_DELETE_PARAMETER_FORMAT =
            Pattern.compile("(?<day>\\S+\\s*)(?<week>\\d+\\s*)(?<index>\\d+\\s*)");

    /**
     * Parses all timetable related commands into their respective parsers.
     *
     * @param parameters User input after determining it is a timetable related command.
     * @return IncorrectCommand or any of the timetable commands.
     * @throws NumberFormatException When the timetable view command is not given DAY, WEEK or a number.
     */
    public static Command parseTimeTableCommand(String parameters) throws NumberFormatException {
        Command command;
        Matcher matcher = TIMETABLE_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, TimeTableCommand.FORMAT));
        }
        try {
            String commandFlag = matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();
            switch (commandFlag) {
            case ADD_FORMAT:
                command = parseTimeTableAddCommand();
                break;
            case DELETE_FORMAT:
                command = parseTimeTableDeleteCommand();
                break;
            case FILTER_FORMAT:
                command = parseTimeTableFilterCommand(parameters);
                break;
            default: // Check if it is a view command
                command = parseTimeTableViewCommand(parameters);
                break;
            }
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new IncorrectCommand(MESSAGE_MODULE_NOT_FOUND);
        } catch (LessonInvalidTimeException e) {
            return new IncorrectCommand(MESSAGE_LESSON_OVERLAP);
        }
        return command;
    }

    /**
     * Parses the timetable view command.
     *
     * @param commandFlag User input determining if it is day, week or custom view.
     * @return TimeTableViewCommand or IncorrectCommand
     */
    public static Command parseTimeTableViewCommand(String commandFlag) throws NumberFormatException {
        int daysToView;
        switch (commandFlag) {
        case VIEW_DAY_FORMAT:
            daysToView = 1;
            break;
        case VIEW_WEEK_FORMAT:
            daysToView = 7;
            break;
        default:
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, commandFlag,
                    MESSAGE_CHECK_COMMAND_FORMAT, TimeTableCommand.FORMAT));
        }
        return new TimeTableViewCommand(daysToView);
    }

    /**
     * Parses the timetable add command. For the adding of Lessons into the timetable.
     *
     * @return TimeTableAddCommand or IncorrectCommand
     * @throws ModuleManager.ModuleNotFoundException When the module is not found in the module list.
     * @throws LessonInvalidTimeException When the start is greater than or equal to end time of the Lesson.
     * @throws DateTimeParseException When the time of either the start or end is in the wrong format.
     */
    public static Command parseTimeTableAddCommand()
            throws ModuleManager.ModuleNotFoundException, LessonInvalidTimeException, DateTimeParseException {
        // TODO: Get all params from original command instead of using TextUi
        String lessonParams = TextUi.getLessonParams();
        Matcher lessonMatcher = TIMETABLE_LESSON_PARAMETER_FORMAT.matcher(lessonParams);
        if (!lessonMatcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                MESSAGE_INVALID_COMMAND_FORMAT, lessonParams, MESSAGE_CHECK_COMMAND_FORMAT,
                    TIMETABLE_LESSON_PARAMETER_USER_FORMAT));
        }
        Lesson newLesson = LessonParser.parseLesson(lessonMatcher);
        // Convert repeatString to int
        String repeatString = lessonMatcher.group(REPEAT_GROUP).toLowerCase().trim();
        int repeatFreq = Integer.parseInt(repeatString);
        return new TimeTableAddCommand(newLesson, repeatFreq);
    }

    /**
     * Parses the timetable delete command. For the deleting of Lessons from the timetable.
     * Requires the DAY, WEEK_NUM, INDEX.
     *
     * @return TimeTableDeleteCommand or IncorrectCommand
     */
    public static Command parseTimeTableDeleteCommand() {
        // TODO: Get all params from original command instead of using TextUi
        String deleteParams = TextUi.getTimeTableDeleteParams();
        Matcher lessonMatcher = TIMETABLE_DELETE_PARAMETER_FORMAT.matcher(deleteParams);
        if (!lessonMatcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, deleteParams, MESSAGE_CHECK_COMMAND_FORMAT,
                    TIMETABLE_LESSON_DELETE_USER_FORMAT));
        }
        // Must account for the user input vs the actual week number
        String dayString = lessonMatcher.group("day").toUpperCase().trim();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayString);
        int indexToBeDeleted = Integer.parseInt(lessonMatcher.group("index"));
        return new TimeTableDeleteCommand(dayOfWeek, indexToBeDeleted);
    }

    public static Command parseTimeTableFilterCommand(String parameters) {
        return null;
    }
}
