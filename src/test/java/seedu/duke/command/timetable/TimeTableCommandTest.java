package seedu.duke.command.timetable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonType;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.TimeTableInitialiseException;
import seedu.duke.util.ExceptionMessage;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeTableCommandTest {
    static final int BAD_REPEAT_FREQ = 4;
    static final int REPEAT_FREQ_ONCE = 0;
    static final int REPEAT_FREQ_WEEKLY = 1;
    static final int REPEAT_FREQ_EVEN = 2;
    static final int REPEAT_FREQ_ODD = 3;
    static final int BAD_LESSON_INDEX = 5;
    static final int ONE_LESSON_COUNT = 1;
    static final int EVEN_ODD_LESSON_COUNT = 7;
    static final int WEEKLY_LESSON_COUNT = 13;

    static Lesson LESSON_1;
    static Lesson LESSON_1_OVERLAP;
    static Lesson LESSON_2;
    static Lesson LESSON_3;

    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CG2271";
    static final String MOD_CODE_3 = "MA1511";

    static final DayOfWeek DAY_OF_WEEK = DayOfWeek.MONDAY;
    static final LocalTime LESSON_1_START = LocalTime.of(14,0);
    static final LocalTime LESSON_1_END = LocalTime.of(16,0);
    static final LocalTime OTHER_LESSON_START = LocalTime.of(9,0);
    static final LocalTime OTHER_LESSON_END = LocalTime.of(10,0);
    static final LessonType LESSON_TYPE = LessonType.LECTURE;

    @BeforeEach
    void setUp() throws TimeTableInitialiseException, LessonInvalidTimeException {
        TimeTableManager.initialise(1);
        LESSON_1 = new Lesson(MOD_CODE_1, LESSON_TYPE, DAY_OF_WEEK, LESSON_1_START, LESSON_1_END);
        LESSON_2 = new Lesson(MOD_CODE_2, LESSON_TYPE, DAY_OF_WEEK, OTHER_LESSON_START, OTHER_LESSON_END);
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
        LESSON_1_OVERLAP = new Lesson(MOD_CODE_2, LESSON_TYPE, DAY_OF_WEEK, LESSON_1_START, LESSON_1_END);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_1_OVERLAP, REPEAT_FREQ_WEEKLY);
        CommandResult commandResult = timeTableAddCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LESSON_OVERLAP, commandResult.feedbackToUser);
    }

    @Test
    void addLesson_repeat_0_verifyOneLessonCreated() throws LessonInvalidTimeException {
        LESSON_3 = new Lesson(MOD_CODE_3, LESSON_TYPE, DAY_OF_WEEK, OTHER_LESSON_START, OTHER_LESSON_END);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_3, REPEAT_FREQ_ONCE);
        timeTableAddCommand.execute();
        assertEquals(ONE_LESSON_COUNT, TimeTableManager.getCountOfLesson(LESSON_3));
    }

    @Test
    void addLesson_repeat_1_verifyWeeklyLessonCreated() throws LessonInvalidTimeException {
        LESSON_3 = new Lesson(MOD_CODE_3, LESSON_TYPE, DAY_OF_WEEK, OTHER_LESSON_START, OTHER_LESSON_END);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_3, REPEAT_FREQ_WEEKLY);
        timeTableAddCommand.execute();
        assertEquals(WEEKLY_LESSON_COUNT, TimeTableManager.getCountOfLesson(LESSON_3));
    }

    @Test
    void addLesson_repeat_2_verifyEvenLessonCreated() throws LessonInvalidTimeException {
        LESSON_3 = new Lesson(MOD_CODE_3, LESSON_TYPE, DAY_OF_WEEK, OTHER_LESSON_START, OTHER_LESSON_END);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_3, REPEAT_FREQ_EVEN);
        timeTableAddCommand.execute();
        assertEquals(7, TimeTableManager.getCountOfLesson(LESSON_3));
    }

    @Test
    void addLesson_repeat_3_verifyOddLessonCreated() throws LessonInvalidTimeException {
        LESSON_3 = new Lesson(MOD_CODE_3, LESSON_TYPE, DAY_OF_WEEK, OTHER_LESSON_START, OTHER_LESSON_END);
        TimeTableAddCommand timeTableAddCommand = new TimeTableAddCommand(LESSON_3, REPEAT_FREQ_ODD);
        timeTableAddCommand.execute();
        assertEquals(EVEN_ODD_LESSON_COUNT, TimeTableManager.getCountOfLesson(LESSON_3));
    }

    @Test
    void deleteLesson_lessonNotFound_LessonNotFoundMessage_isShown() {
        TimeTableDeleteCommand timeTableDeleteCommand = new TimeTableDeleteCommand(DAY_OF_WEEK, BAD_LESSON_INDEX);
        CommandResult commandResult = timeTableDeleteCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LESSON_NOT_FOUND, commandResult.feedbackToUser);
    }
}
