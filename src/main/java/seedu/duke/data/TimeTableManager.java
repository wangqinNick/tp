package seedu.duke.data;

import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.TimeTableInitialiseException;

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
     * Takes in the current week number in order to initialise.
     */
    public static void initialise(int userCurrWeekNum) throws TimeTableInitialiseException {
        LocalDateTime now = LocalDateTime.now();
        // One sem has 13 weeks of lessons
        if (userCurrWeekNum > 0 && userCurrWeekNum < 15) {
            // e.g. Current week is 45, NUS week 2
            // semStartWeekNum is NUS week 1
            // therefore, semStartWeekNum = 45 - (2 - 1) = 44
            semStartWeekNum = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) - (userCurrWeekNum - 1);
        } else {
            throw new TimeTableInitialiseException();
        }
        semEndWeekNum = semStartWeekNum + 14;
        semRecessWeekNum = semStartWeekNum + 6;
        // e.g. semStartWeekNum is 44, NUS week 1
        // Current week is NUS week 2
        // therefore, userCurrWeekNum = 44 + 2 - 1 = 45
        userCurrWeekNum = semStartWeekNum + userCurrWeekNum - 1;
        for (int weekNum = userCurrWeekNum; weekNum < semEndWeekNum; weekNum++) {
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
     * The repeat parameter where
     * 0: Current week only.
     * 1: Every week.
     * 2: Every even week.
     * 3: Every odd week.
     *
     * @param lesson The lesson object.
     * @param repeat The repeat parameter.
     * @throws LessonInvalidTimeException when the lesson to be added overlaps with an existing lesson.
     */
    public static void addLesson(Lesson lesson, int repeat) throws LessonInvalidTimeException {
        int currWeek = getCurrNusWeekNum();
        LessonManager lessonManager = getLessonManager(currWeek);
        // add for current week
        if (repeat == 0) {
            lessonManager.addLesson(lesson);
            return;
        }

        lesson.generateHiddenId(); // tag lesson with unique ID for identifying when removing

        // add for every week, or alternate weeks
        for (;currWeek < semEndWeekNum;currWeek++) { // don't add for reading week
            lessonManager = getLessonManager(currWeek);
            if (currWeek == semRecessWeekNum) { // don't add for recess week
            } else if (repeat == 1) { // if repeat every week
                lessonManager.addLesson(lesson);
            } else if (currWeek % 2 == 0 && repeat == 2) { // if repeat every even week
                lessonManager.addLesson(lesson);
            } else if (currWeek % 2 == 1 && repeat == 3) { // if repeat every odd week
                lessonManager.addLesson(lesson);
            }
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
    public static void removeLesson(DayOfWeek dayOfWeek, int lessonIndex) throws IndexOutOfBoundsException {
        int currWeek = getCurrNusWeekNum();
        LessonManager lessonManager = getLessonManager(currWeek);

        // retrieve the unique ID, as index does not stay constant over different weeks
        String id = lessonManager.getDayLessonList(dayOfWeek).get(lessonIndex).getHiddenId();

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
        LessonManager lessonManager = getLessonManager(currWeek);
        return lessonManager.getDayLessonList(dayOfWeek);
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
        LessonManager lessonManager = getLessonManager(week);
        for (DayOfWeek eachDay : DayOfWeek.values()) {
            outputList.add(lessonManager.getDayLessonList(eachDay));
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
        int currNusWeek = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
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
