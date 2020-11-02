package seedu.duke.data;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.DuplicateModuleException;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.LessonOverlapException;
import seedu.duke.exception.ModuleNotFoundException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.exception.TimeTableInitialiseException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TimeTableTest {
    static Lesson lesson1;
    static Lesson lesson2;
    static Lesson lesson3;
    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CG2271";
    static final String MOD_CODE_3 = "MA1511";
    static final LocalTime time_12 = LocalTime.of(12, 0);
    static final LocalTime time_13 = LocalTime.of(13, 0);
    static final LocalTime time_14 = LocalTime.of(14, 0);
    static final LocalTime time_15 = LocalTime.of(15, 0);
    static final LocalTime time_16 = LocalTime.of(16, 0);
    static final LocalTime time_17 = LocalTime.of(17, 0);
    static final LocalDateTime now = LocalDateTime.now();
    static final int CURR_WEEK = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);

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
        lesson1 = new Lesson(MOD_CODE_1, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_13);
        lesson2 = new Lesson(MOD_CODE_2, LessonType.LECTURE, DayOfWeek.MONDAY, time_14, time_15);
        lesson3 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_16, time_17);
        TimeTableManager.clearTimeTable();
        TimeTableManager.initialise(1);
    }

    @Test
    void verify_addLesson_repeat1() throws LessonInvalidTimeException, ModuleNotFoundException, LessonOverlapException {
        TimeTableManager.addLesson(lesson1, 1);

        for (int i = CURR_WEEK; i < CURR_WEEK + 14; i++) {
            if (TimeTableManager.isEvenWeek(i)) {
                assertEquals(1, TimeTableManager.getWeekLessonCount(i));
            } else if (TimeTableManager.isOddWeek(i)) {
                assertEquals(1, TimeTableManager.getWeekLessonCount(i));
            } else {
                assertEquals(0, TimeTableManager.getWeekLessonCount(i));
            }
        }
    }

    @Test
    void verify_addLesson_repeat2() throws LessonInvalidTimeException, ModuleNotFoundException, LessonOverlapException {
        TimeTableManager.addLesson(lesson1, 2);

        for (int i = CURR_WEEK; i < CURR_WEEK + 14; i++) {
            if (TimeTableManager.isEvenWeek(i)) {
                assertEquals(1, TimeTableManager.getWeekLessonCount(i));
            } else if (TimeTableManager.isOddWeek(i)) {
                assertEquals(0, TimeTableManager.getWeekLessonCount(i));
            } else {
                assertEquals(0, TimeTableManager.getWeekLessonCount(i));
            }
        }
    }

    @Test
    void verify_addLesson_repeat3() throws LessonInvalidTimeException, ModuleNotFoundException, LessonOverlapException {
        TimeTableManager.addLesson(lesson1, 3);

        for (int i = CURR_WEEK; i < CURR_WEEK + 14; i++) {
            if (TimeTableManager.isEvenWeek(i)) {
                assertEquals(0, TimeTableManager.getWeekLessonCount(i));
            } else if (TimeTableManager.isOddWeek(i)) {
                assertEquals(1, TimeTableManager.getWeekLessonCount(i));
            } else {
                assertEquals(0, TimeTableManager.getWeekLessonCount(i));
            }
        }
    }

    @Test
    void verify_removeLesson_inSingleWeek() throws
            LessonInvalidTimeException, ModuleNotFoundException, LessonOverlapException {
        TimeTableManager.addLesson(lesson1, 1);
        TimeTableManager.addLesson(lesson2, 1);
        TimeTableManager.addLesson(lesson3, 1);

        assertEquals(3, TimeTableManager.getWeekLessonCount(CURR_WEEK));
        TimeTableManager.deleteLesson(DayOfWeek.MONDAY, 0);

        assertEquals(2, TimeTableManager.getWeekLessonCount(CURR_WEEK));
        TimeTableManager.deleteLesson(DayOfWeek.MONDAY, 0);

        assertEquals(1, TimeTableManager.getWeekLessonCount(CURR_WEEK));
        TimeTableManager.deleteLesson(DayOfWeek.MONDAY, 0);

        assertEquals(0, TimeTableManager.getWeekLessonCount(CURR_WEEK));
    }

    @Test
    void verify_removeLesson_overMultipleWeeks() throws
            LessonInvalidTimeException, ModuleNotFoundException, LessonOverlapException {
        TimeTableManager.addLesson(lesson1, 1);
        TimeTableManager.addLesson(lesson2, 2);
        TimeTableManager.addLesson(lesson3, 3);

        assertEquals(2, TimeTableManager.getWeekLessonCount(CURR_WEEK));
        assertEquals(2, TimeTableManager.getWeekLessonCount(CURR_WEEK + 1));
        assertEquals(2, TimeTableManager.getWeekLessonCount(CURR_WEEK + 2));
        TimeTableManager.deleteLesson(DayOfWeek.MONDAY, 0); // remove 'repeat 1' lesson

        assertEquals(1, TimeTableManager.getWeekLessonCount(CURR_WEEK));
        assertEquals(1, TimeTableManager.getWeekLessonCount(CURR_WEEK + 1));
        assertEquals(1, TimeTableManager.getWeekLessonCount(CURR_WEEK + 2));
        TimeTableManager.deleteLesson(DayOfWeek.MONDAY, 0); // remove 'repeat 3' lesson

        assertEquals(0, TimeTableManager.getWeekLessonCount(CURR_WEEK));
        assertEquals(1, TimeTableManager.getWeekLessonCount(CURR_WEEK + 1));
        assertEquals(0, TimeTableManager.getWeekLessonCount(CURR_WEEK + 2));
    }

    @Test
    void verify_removeLesson_throwsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> TimeTableManager.deleteLesson(DayOfWeek.MONDAY, 1));
    }

    @Test
    void verify_lessonOverlap_throwsLessonOverlap() throws
            LessonOverlapException, LessonInvalidTimeException, ModuleNotFoundException {
        TimeTableManager.addLesson(lesson1, 1);
        assertThrows(LessonOverlapException.class,
                () -> TimeTableManager.addLesson(lesson1, 0));
        assertThrows(LessonOverlapException.class,
                () -> TimeTableManager.addLesson(lesson1, 2));
        assertThrows(LessonOverlapException.class,
                () -> TimeTableManager.addLesson(lesson1, 3));

        TimeTableManager.addLesson(lesson2, 2);
        assertThrows(LessonOverlapException.class,
                () -> TimeTableManager.addLesson(lesson2, 1));
        assertThrows(LessonOverlapException.class,
                () -> TimeTableManager.addLesson(lesson2, 2));

        TimeTableManager.addLesson(lesson2, 3); // testing whether repeat 2 and 3 clashes (should not)
    }

}
