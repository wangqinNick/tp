package seedu.duke.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashMap;

public class TimeTableManager {
    private static final HashMap<Integer, LessonManager> semesterMap = new HashMap<>();

    public TimeTableManager() {
        initialise();
    }

    /**
     * Initialise the semesterMap when it is empty.
     */
    public static void initialise() {
        LocalDateTime now = LocalDateTime.now();
        int semStartWeekNum = now.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        int semEndWeekNum = semStartWeekNum + 18; // One sem is 18 weeks

        for (int weekNum = semStartWeekNum; weekNum < semEndWeekNum ; weekNum++) {
            semesterMap.put(weekNum, new LessonManager());
        }
    }

    public static LessonManager getLessonManager(int weekNum) {
        LessonManager weekManager = semesterMap.get(weekNum);
        return weekManager;
    }
}
