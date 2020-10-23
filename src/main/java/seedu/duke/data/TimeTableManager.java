package seedu.duke.data;

import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.TimeTableInitialiseException;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableManager {
    private static final HashMap<Integer, LessonManager> semesterMap = new HashMap<>();
    public static int semStartWeekNum;
    public static int semEndWeekNum;
    public static int semRecessWeekNum;
    
    /**
     * Initialise the semesterMap when it is empty.
     */
    public static void initialise(int currWeekNum) throws TimeTableInitialiseException {
        LocalDateTime now = LocalDateTime.now();
        // One sem has 13 weeks of lessons
        if (currWeekNum < 1 || currWeekNum > 15) {
            // e.g. Current week is 45, NUS week 2
            // semStartWeekNum is NUS week 1
            // therefore, semStartWeekNum = 45 - (2 - 1) = 44
            semStartWeekNum = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) - (currWeekNum - 1);
        } else {
            throw new TimeTableInitialiseException();
        }
        semEndWeekNum = semStartWeekNum + 14;
        semRecessWeekNum = semStartWeekNum + 6;
        for (int weekNum = currWeekNum; weekNum < semEndWeekNum; weekNum++) {
            semesterMap.put(weekNum, new LessonManager());
        }
    }

    /**
     * Checks if semesterMap is initialised.
     *
     * @return
     *  Boolean of whether it is initialised
     */
    public static boolean isInitialised() {
        return !(semesterMap.size() == 0);
    }

    /**
     * Adds the lesson to semesterMap based on the repeat parameter. E.g. add to every week, every other week, etc.
     * Does not add to recess week (week 7) or reading week (week 15).
     *
     * @param lesson
     *  The lesson object
     * @param repeat
     *  The repeat parameter
     *  0 - Current week only
     *  1 - Every week
     *  2 - Every even week
     *  3 - Every odd week
     * @throws LessonInvalidTimeException
     */
    public static void addLesson(Lesson lesson, int repeat) throws LessonInvalidTimeException {
        int currWeek = getCurrNusWeekNum();
        // add for current week
        if (repeat == 0) {
            getLessonManager(currWeek).addLesson(lesson);
            return;
        }

        lesson.generateHiddenId(); // tag lesson with unique ID for identifying when removing

        // add for every week, or alternate weeks
        while (currWeek != semEndWeekNum) { // don't add for reading week
            if (currWeek == semRecessWeekNum) { // don't add for recess week
                continue;
            } else if (repeat == 1) { // if repeat every week
                getLessonManager(currWeek).addLesson(lesson);
            } else if (currWeek % 2 == 0 && repeat == 2) { // if repeat every even week
                getLessonManager(currWeek).addLesson(lesson);
            } else if (currWeek % 2 == 1 && repeat == 3) { // if repeat every odd week
                getLessonManager(currWeek).addLesson(lesson);
            }
            currWeek++;
        }
    }

    /**
     * Removes lesson by identifying the unique lesson from the current week, dayOfWeek, and lessonIndex.
     * Using the params, the lesson is found and the unique hiddenId is obtained.
     * Uses the removeLessonById method of LessonManager to remove all lessons with this hiddenId.
     *
     * @param dayOfWeek
     *  The day of week
     * @param lessonIndex
     *  The lesson index
     */
    public static void removeLesson(DayOfWeek dayOfWeek, int lessonIndex) {
        int currWeek = getCurrNusWeekNum();

        // retrieve the unique ID, as index does not stay constant over different weeks
        String id = getLessonManager(currWeek).getDayLessonList(dayOfWeek).get(lessonIndex).getHiddenId();

        // remove by ID
        for (LessonManager eachWeek : semesterMap.values()) {
            eachWeek.removeLessonById(dayOfWeek, id);
        }
    }

    /**
     * Get an ArrayList of lessons happening on a certain day.
     *
     * @param dayOfWeek
     *  The specific day of week
     * @return
     *  The ArrayList of lessons
     */
    public static ArrayList<Lesson> getSpecificDayLessons(DayOfWeek dayOfWeek) {
        int currWeek = getCurrNusWeekNum();
        return getLessonManager(currWeek).getDayLessonList(dayOfWeek);
    }

    /**
     * Get an ArrayList of ArrayLists of lessons happening in the specified week.
     * Outer ArrayList: Holds one ArrayList for each day of week, starting from Monday.
     * Inner ArrayList: Holds lessons happening on that day of week.
     *
     * @return
     *  The ArrayList of ArrayList of lessons
     */
    public static ArrayList<ArrayList<Lesson>> getSpecifiedWeekLessons(int week) {
        ArrayList<ArrayList<Lesson>> outputList = new ArrayList<>();
        for (DayOfWeek eachDay : DayOfWeek.values()) {
            outputList.add(getLessonManager(week).getDayLessonList(eachDay));
        }
        return outputList;
    }

    /**
     * Get the current NUS week number.
     * Recess week is counted as week 7, and the nominal week 7 is week 8 here, etc.
     *
     * @return
     *  The current NUS week number.
     */
    public static int getCurrNusWeekNum() {
        LocalDateTime now = LocalDateTime.now();
        int currNusWeek = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) - semStartWeekNum + 1;
        return currNusWeek;
    }

    public static LessonManager getLessonManager(int weekNum) {
        LessonManager weekManager = semesterMap.get(weekNum);
        return weekManager;
    }

    public static int getSemEndWeekNum() {
        return semEndWeekNum;
    }

    public static int getSemStartWeekNum() {
        return semStartWeekNum;
    }

    public static int getSemRecessWeekNum() {
        return semRecessWeekNum;
    }

    public static boolean isSemRecessWeek(int week) {
        return week == semRecessWeekNum;
    }
}
