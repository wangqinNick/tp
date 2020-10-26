package seedu.duke.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.data.LessonType;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.InvalidMatchException;
import seedu.duke.exception.TimeTableInitialiseException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.parser.TimeTableCommandParser.ADD_FORMAT;

public class TimeTableCommandParserTest {
    static final int GOOD_REPEAT_FREQ = 1;
    static final String BAD_TIMETABLE_COMMAND_FORMAT = "-no";
    static final String GOOD_MOD_CODE = "CG2271";
    static final String BAD_MOD_CODE = "CS111111";
    static final String BAD_LESSON_START_TIME = "2pm";
    static final String BAD_DAY_OF_WEEK = "mon";
    static final String DAY_OF_WEEK_STRING = "MONDAY";
    static final String LESSON_TYPE_STRING = "LECTURE";
    static final String LESSON_START_TIME_STRING = "1400";
    static final String LESSON_END_TIME_STRING = "1500";

    static final LessonType LESSON_TYPE = LessonType.LECTURE;


    static final String BAD_MODULE_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + BAD_MOD_CODE + " "
            + DAY_OF_WEEK_STRING + " " + LESSON_START_TIME_STRING + " " + LESSON_END_TIME_STRING + " "
            + LESSON_TYPE_STRING + " " + GOOD_REPEAT_FREQ;
    static final String BAD_LESSON_TIME_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + GOOD_MOD_CODE + " "
            + DAY_OF_WEEK_STRING + " " + BAD_LESSON_START_TIME + " " + LESSON_END_TIME_STRING + " " + LESSON_TYPE + " "
            + GOOD_REPEAT_FREQ;
    static final String BAD_DATETIME_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + GOOD_MOD_CODE + " "
            + BAD_DAY_OF_WEEK + " " + LESSON_START_TIME_STRING + " " + LESSON_END_TIME_STRING + " " + LESSON_TYPE + " "
            + GOOD_REPEAT_FREQ;

    @BeforeEach
    void setupTimeTable() throws TimeTableInitialiseException, ModuleManager.DuplicateModuleException,
            ModuleManager.ModuleNotFoundException {
        ModuleManager.clearModules();
        ModuleManager.add(new Module(GOOD_MOD_CODE));
        TimeTableManager.clearTimeTable();
        TimeTableManager.initialise(1);
    }

    @Test
    void badTimeTableCommandFlag_IncorrectCommand_isReturned() throws InvalidMatchException {
        Command command = TimeTableCommandParser.parseTimeTableCommand(BAD_TIMETABLE_COMMAND_FORMAT);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void bad_Module_TimeTableAddCommandFlag_IncorrectCommand_isReturned() throws InvalidMatchException {
        Command command = TimeTableCommandParser.parseTimeTableCommand(BAD_TIMETABLE_COMMAND_FORMAT);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void bad_Lesson_Time_TimeTableAddCommandFlag_IllegalArgumentException_isThrown() {
        assertThrows(IllegalArgumentException.class,
                () -> TimeTableCommandParser.parseTimeTableCommand(BAD_LESSON_TIME_TIMETABLE_ADD_COMMAND_FORMAT));
    }

    @Test
    void bad_DateTime_TimeTableAddCommandFlag_IllegalArgumentException_isThrown() {
        assertThrows(IllegalArgumentException.class,
                () -> TimeTableCommandParser.parseTimeTableCommand(BAD_DATETIME_TIMETABLE_ADD_COMMAND_FORMAT));
    }
}
