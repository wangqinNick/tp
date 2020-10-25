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
    static LessonManager manager = new LessonManager();
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
    void setupLessonObjects() throws LessonInvalidTimeException {
        lesson1 = new Lesson(MOD_CODE_1, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_13);
        lesson2 = new Lesson(MOD_CODE_2, LessonType.LECTURE, DayOfWeek.MONDAY, time_14, time_15);
        manager.clearAllLessons();
        manager.addLesson(lesson1);
        manager.addLesson(lesson2);
    }

    @Test
    void getOverlapException_addLesson() throws LessonInvalidTimeException, LessonNotFoundException {
        Lesson testLesson1 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_13);
        assertThrows(LessonInvalidTimeException.class, () -> manager.addLesson(testLesson1));

        Lesson testLesson2 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_12, time_14);
        assertThrows(LessonInvalidTimeException.class, () -> manager.addLesson(testLesson2));

        Lesson testLesson3 = new Lesson(MOD_CODE_3, LessonType.LECTURE, DayOfWeek.MONDAY, time_13, time_15);
        assertThrows(LessonInvalidTimeException.class, () -> manager.addLesson(testLesson3));
    }

    @Test
    void verify_LessonNotFoundException_isThrown() {
        assertThrows(LessonNotFoundException.class, () -> manager.removeLesson(DayOfWeek.TUESDAY, 0));
        assertThrows(LessonNotFoundException.class, () -> manager.removeLesson(DayOfWeek.MONDAY, -1));
        assertThrows(LessonNotFoundException.class, () -> manager.removeLesson(DayOfWeek.MONDAY, 2));
    }

    @Test
    void verify_addLesson_sortsLessons() throws LessonNotFoundException, LessonInvalidTimeException {
        assertEquals(MOD_CODE_1, manager.getDayLessonList(DayOfWeek.MONDAY).get(0).getModuleCode());
        assertEquals(MOD_CODE_2, manager.getDayLessonList(DayOfWeek.MONDAY).get(1).getModuleCode());

        String testCode = "Test Code";
        Lesson testLesson = new Lesson(testCode, LessonType.LECTURE, DayOfWeek.MONDAY, time_13, time_14);
        manager.addLesson(testLesson);
        assertEquals(MOD_CODE_1, manager.getDayLessonList(DayOfWeek.MONDAY).get(0).getModuleCode());
        assertEquals(testCode, manager.getDayLessonList(DayOfWeek.MONDAY).get(1).getModuleCode());
        assertEquals(MOD_CODE_2, manager.getDayLessonList(DayOfWeek.MONDAY).get(2).getModuleCode());
    }

    @Test
    void verifyLessonCount_afterDeletingLessons() throws LessonNotFoundException {
        assertEquals(2, manager.getLessonCountOnDay(DayOfWeek.MONDAY));
        manager.removeLesson(DayOfWeek.MONDAY, 0);
        assertEquals(1, manager.getLessonCountOnDay(DayOfWeek.MONDAY));
        manager.removeLesson(DayOfWeek.MONDAY, 0);
        assertEquals(0, manager.getLessonCountOnDay(DayOfWeek.MONDAY));
    }

    @Test
    void verifyException_lessonConstruction_withInvalidTime() {
        assertThrows(LessonInvalidTimeException.class,
            () -> new Lesson(MOD_CODE_1, LessonType.LAB, DayOfWeek.TUESDAY, time_13, time_12));
        assertThrows(LessonInvalidTimeException.class,
            () -> new Lesson(MOD_CODE_1, LessonType.LAB, DayOfWeek.TUESDAY, time_12, time_12));
    }

    @Test
    void verifyLessonFilter_isWorking() {
        LessonFilter filterTuesday = (l) -> l.getDay().equals(DayOfWeek.TUESDAY);
        assertEquals(0, manager.filterLessons(filterTuesday).size());

        LessonFilter filterMonday = (l) -> l.getDay().equals(DayOfWeek.MONDAY);
        assertEquals(2, manager.filterLessons(filterMonday).size());

        LessonFilter filterMonBefore2 = (l) -> l.getDay().equals(DayOfWeek.MONDAY) && l.getEndTime().isBefore(time_14);
        assertEquals(1, manager.filterLessons(filterMonBefore2).size());

        String testCode = MOD_CODE_1;
        LessonFilter filterVariableModCode = (l) -> l.getModuleCode().equalsIgnoreCase(testCode);
        assertEquals(1, manager.filterLessons(filterVariableModCode).size());
    }
}
