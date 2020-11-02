package seedu.duke.command.timetable;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonType;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.DuplicateModuleException;
import seedu.duke.exception.InvalidMatchException;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.exception.TimeTableInitialiseException;
import seedu.duke.parser.TimeTableCommandParser;
import seedu.duke.util.ExceptionMessage;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeTableCommandTest {
    static final int BAD_REPEAT_FREQ = 4;
    static final int REPEAT_FREQ_WEEKLY = 1;
    static final int BAD_LESSON_INDEX = 5;

    static Lesson LESSON_1;
    static Lesson LESSON_1_OVERLAP;
    static Lesson LESSON_2;

    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CG2271";
    static final String MOD_CODE_3 = "MA1511";
    static final String BAD_VIEW_FORMAT = "-no";

    static final DayOfWeek DAY_OF_WEEK = DayOfWeek.MONDAY;
    static final LocalTime LESSON_1_START_TIME = LocalTime.of(14, 0);
    static final LocalTime LESSON_1_END_TIME = LocalTime.of(16, 0);
    static final LocalTime OTHER_LESSON_START = LocalTime.of(9, 0);
    static final LocalTime OTHER_LESSON_END = LocalTime.of(10, 0);
    static final LessonType LESSON_TYPE = LessonType.LECTURE;

    @BeforeAll
    static void setupUserMods() throws DuplicateModuleException, ModuleNotProvidedException {
        InputOutputManager.loadNusModSave();
        ModuleManager.clearModules();
        ModuleManager.add(MOD_CODE_1);
        ModuleManager.add(MOD_CODE_2);
        ModuleManager.add(MOD_CODE_3);
    }

    @BeforeEach
    void setupTimeTable_andLessons() throws LessonInvalidTimeException, TimeTableInitialiseException {
        LESSON_1 = new Lesson(MOD_CODE_1, LESSON_TYPE, DAY_OF_WEEK, LESSON_1_START_TIME, LESSON_1_END_TIME);
        LESSON_2 = new Lesson(MOD_CODE_2, LESSON_TYPE, DAY_OF_WEEK, OTHER_LESSON_START, OTHER_LESSON_END);
        TimeTableManager.clearTimeTable();
        TimeTableManager.initialise(1);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_1, REPEAT_FREQ_WEEKLY);
        timeTableAddCommand.execute();
    }

    @Test
    void addLesson_badRepeatFreq_RepeatFrequencyMessage_isShown() {
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_2, BAD_REPEAT_FREQ);
        CommandResult commandResult = timeTableAddCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_REPEAT_FREQUENCY_UNKNOWN, commandResult.feedbackToUser);
    }

    @Test
    void addLesson_overlapLessonTime_LessonOverlapMessage_isShown() throws LessonInvalidTimeException {
        LESSON_1_OVERLAP = new Lesson(MOD_CODE_2, LESSON_TYPE, DAY_OF_WEEK, LESSON_1_START_TIME, LESSON_1_END_TIME);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_1_OVERLAP, REPEAT_FREQ_WEEKLY);
        CommandResult commandResult = timeTableAddCommand.execute();
        assertEquals(String.format(ExceptionMessage.MESSAGE_LESSON_OVERLAP, LESSON_1.toString()),
                commandResult.feedbackToUser);
    }

    @Test
    void deleteLesson_lessonNotFound_LessonNotFoundMessage_isShown() {
        TimeTableDeleteCommand timeTableDeleteCommand = new TimeTableDeleteCommand(DAY_OF_WEEK, BAD_LESSON_INDEX);
        CommandResult commandResult = timeTableDeleteCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LESSON_NOT_FOUND, commandResult.feedbackToUser);
    }

    @Test
    void viewTimeTable_badViewFormat_IncorrectCommand_isReturned() throws InvalidMatchException {
        Command timeTableviewCommand = TimeTableCommandParser.parseTimeTableCommand(BAD_VIEW_FORMAT);
        assertTrue(timeTableviewCommand instanceof IncorrectCommand);
    }
}
