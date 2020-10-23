package seedu.duke.data;

import seedu.duke.exception.TimeTableInitialiseException;
import seedu.duke.ui.TextUi;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;

public abstract class TimeTableManager {
    private static final HashMap<Integer, LessonManager> semesterMap = new HashMap<>();
    public static int semStartWeekNum;
    public static int semEndWeekNum;
    public static int semReadingWeekNum;
    public static boolean isInitialised = false;
    
    /**
     * Initialise the semesterMap when it is empty.
     */
    public static void initialise() throws TimeTableInitialiseException {
        int currWeekNum = TextUi.getCurrentWeekNum();
        if (semesterMap.size() == 0) {
            LocalDateTime now = LocalDateTime.now();
            // One sem has 13 weeks of lessons
            if (currWeekNum <= 6) {
                semStartWeekNum = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) - currWeekNum + 1; //+1 to start at week 1
            } else if (currWeekNum > 6 || currWeekNum <= 13) {
                semStartWeekNum = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR) - currWeekNum; //Accounted for reading week
            } else {
                throw new TimeTableInitialiseException();
            }
            semEndWeekNum = semStartWeekNum + 13;
            semReadingWeekNum = semStartWeekNum + 6;
            for (int weekNum = currWeekNum; weekNum < semEndWeekNum; weekNum++) {
                // Needs to skip reading week
                if (weekNum == semReadingWeekNum) {
                    continue;
                }
                semesterMap.put(weekNum, new LessonManager());
            }
        }
        isInitialised = true;
    }

    public static boolean isInitialised() {
        return isInitialised;
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

    public static int getSemReadingWeekNum() {
        return semReadingWeekNum;
    }

    public static int getCurrWeekNum() {
        LocalDateTime now = LocalDateTime.now();
        return now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }

    public static boolean isSemReadingWeek(int week) {
        return week == semReadingWeekNum;
    }
}
