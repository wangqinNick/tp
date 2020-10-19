package seedu.duke.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;

public abstract class TimeTableManager {
    private static final HashMap<Integer, LessonManager> semesterMap = new HashMap<>();
    public static int semStartWeekNum ;
    public static int semEndWeekNum;
    public TimeTableManager() {
        initialise();
    }

    /**
     * Initialise the semesterMap when it is empty.
     */
    public void initialise() {
        LocalDateTime now = LocalDateTime.now();
        semStartWeekNum = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        semEndWeekNum = semStartWeekNum + 18; // One sem is 18 weeks
        for (int weekNum = semStartWeekNum; weekNum < semEndWeekNum ; weekNum++) {
            semesterMap.put(weekNum, new LessonManager());
        }
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
}
