package seedu.duke.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.LessonNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LessonTest {
    static Lesson lesson1;
    static Lesson lesson2;
    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CG2271";
    static final String MOD_CODE_3 = "MA1511";
    static final LocalTime time_12 = LocalTime.of(12,0);
    static final LocalTime time_13 = LocalTime.of(13,0);
    static final LocalTime time_14 = LocalTime.of(14,0);
    static final LocalTime time_15 = LocalTime.of(15,0);

    @BeforeEach
    void setupModObjects() throws LessonInvalidTimeException{
        lesson1 = new Lesson(MOD_CODE_1, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_13);
        lesson2 = new Lesson(MOD_CODE_2, LessonType.LECTURE, DayOfWeek.MONDAY, time_14, time_15);
        LessonManager.initialise();
        LessonManager.addLesson(lesson1);
        LessonManager.addLesson(lesson2);
    }

    @Test
    void getOverlapException_addLesson() throws LessonInvalidTimeException, LessonNotFoundException {
        Lesson testLesson1 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_13);
        assertThrows(LessonInvalidTimeException.class, () -> LessonManager.addLesson(testLesson1));

        Lesson testLesson2 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_14);
        assertThrows(LessonInvalidTimeException.class, () -> LessonManager.addLesson(testLesson2));

        Lesson testLesson3 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_13, time_15);
        assertThrows(LessonInvalidTimeException.class, () -> LessonManager.addLesson(testLesson3));
    }

    @Test
    void verify_LessonNotFoundException_isThrown() {
        assertThrows(LessonNotFoundException.class, () -> LessonManager.removeLesson(DayOfWeek.TUESDAY, 0));
        assertThrows(LessonNotFoundException.class, () -> LessonManager.removeLesson(DayOfWeek.MONDAY, -1));
        assertThrows(LessonNotFoundException.class, () -> LessonManager.removeLesson(DayOfWeek.MONDAY, 2));
    }

    @Test
    void verify_addLesson_sortsLessons() throws LessonNotFoundException, LessonInvalidTimeException {
        assertEquals(MOD_CODE_1, LessonManager.getDayLessonList(DayOfWeek.MONDAY).get(0).getModuleCode());
        assertEquals(MOD_CODE_2, LessonManager.getDayLessonList(DayOfWeek.MONDAY).get(1).getModuleCode());

        String testCode = "Test Code";
        Lesson testLesson = new Lesson(testCode, LessonType.LECTURE, DayOfWeek.MONDAY, time_13, time_14);
        LessonManager.addLesson(testLesson);
        assertEquals(MOD_CODE_1, LessonManager.getDayLessonList(DayOfWeek.MONDAY).get(0).getModuleCode());
        assertEquals(testCode, LessonManager.getDayLessonList(DayOfWeek.MONDAY).get(1).getModuleCode());
        assertEquals(MOD_CODE_2, LessonManager.getDayLessonList(DayOfWeek.MONDAY).get(2).getModuleCode());
    }

    @Test
    void verifyLessonCount_afterDeletingLessons() throws LessonNotFoundException {
        assertEquals(2, LessonManager.getLessonCountOnDay(DayOfWeek.MONDAY));
        LessonManager.removeLesson(DayOfWeek.MONDAY, 0);
        assertEquals(1, LessonManager.getLessonCountOnDay(DayOfWeek.MONDAY));
        LessonManager.removeLesson(DayOfWeek.MONDAY, 0);
        assertEquals(0, LessonManager.getLessonCountOnDay(DayOfWeek.MONDAY));
    }
}
