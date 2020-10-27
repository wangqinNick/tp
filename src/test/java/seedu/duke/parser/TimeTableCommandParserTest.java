package seedu.duke.parser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.InvalidMatchException;
import seedu.duke.exception.TimeTableInitialiseException;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.parser.TimeTableCommandParser.ADD_FORMAT;

public class TimeTableCommandParserTest {
    static final int REPEAT_FREQ = 1;
    static final String WRONG_TIMETABLE_COMMAND_FORMAT = "-no";
    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CS111111";
    static final String LESSON_START_TIME_1 = "2pm";
    static final String LESSON_START_TIME_2 = "10";
    static final String LESSON_START_TIME_3 = "1400";
    static final String LESSON_END_TIME = "1500";
    static final String DAY_OF_WEEK_1 = "mon";
    static final String DAY_OF_WEEK_2 = "MONDAY";
    static final String LESSON_TYPE = "LECTURE";

    static final String WRONG_MOD_TIMETABLE_ADD_COMMAND_FORMAT = MOD_CODE_2 + " " + DAY_OF_WEEK_1 + " "
            + LESSON_START_TIME_3 + " " + LESSON_END_TIME + " " + LESSON_TYPE + " " + REPEAT_FREQ;
    static final String WRONG_LESSON_TIME_1_TIMETABLE_ADD_COMMAND_FORMAT = MOD_CODE_1 + " " + DAY_OF_WEEK_1 + " "
            + LESSON_START_TIME_1 + " " + LESSON_END_TIME + " " + LESSON_TYPE + " " + REPEAT_FREQ;
    static final String WRONG_LESSON_TIME_2_TIMETABLE_ADD_COMMAND_FORMAT = MOD_CODE_1 + " " + DAY_OF_WEEK_2 + " "
            + LESSON_START_TIME_2 + " " + LESSON_END_TIME + " " + LESSON_TYPE + " " + REPEAT_FREQ;
    static final String WRONG_DAY_TIMETABLE_ADD_COMMAND_FORMAT = ADD_FORMAT + " " + MOD_CODE_1 + " " + DAY_OF_WEEK_1
            + " " + LESSON_START_TIME_3 + " " + LESSON_END_TIME + " " + LESSON_TYPE + " " + REPEAT_FREQ;

    @BeforeAll
    static void setupUserMods() throws ModuleManager.DuplicateModuleException, ModuleManager.ModuleNotFoundException {
        InputOutputManager.loadNusModSave();
        ModuleManager.clearModules();
        ModuleManager.add(new Module(MOD_CODE_1));
    }

    @BeforeEach
    void setupTimeTable() throws TimeTableInitialiseException {
        TimeTableManager.clearTimeTable();
        TimeTableManager.initialise(1);
    }

    @Test
    void badTimeTableCommandFlag_IncorrectCommand_isReturned() throws InvalidMatchException {
        Command command = TimeTableCommandParser.parseTimeTableCommand(WRONG_TIMETABLE_COMMAND_FORMAT);
        assertTrue(command instanceof IncorrectCommand);
    }

    @Test
    void bad_Module_TimeTableAddCommandFlag_ModuleNotFoundException_isThrown() {
        assertThrows(ModuleManager.ModuleNotFoundException.class,
            () -> TimeTableCommandParser.parseTimeTableAddCommand(WRONG_MOD_TIMETABLE_ADD_COMMAND_FORMAT));
    }

    @Test
    void bad_Lesson_Time_1_TimeTableAddCommandFlag_InvalidMatchException_isThrown() {
        assertThrows(InvalidMatchException.class,
            () -> TimeTableCommandParser.parseTimeTableAddCommand(WRONG_LESSON_TIME_1_TIMETABLE_ADD_COMMAND_FORMAT));
    }

    @Test
    void bad_Lesson_Time_2_TimeTableAddCommandFlag_DateTimeParseException_isThrown() {
        assertThrows(DateTimeParseException.class,
            () -> TimeTableCommandParser.parseTimeTableAddCommand(WRONG_LESSON_TIME_2_TIMETABLE_ADD_COMMAND_FORMAT));
    }

    @Test
    void bad_DateTime_TimeTableAddCommandFlag_IllegalArgumentException_isThrown() {
        assertThrows(IllegalArgumentException.class,
            () -> TimeTableCommandParser.parseTimeTableCommand(WRONG_DAY_TIMETABLE_ADD_COMMAND_FORMAT));
    }
}
