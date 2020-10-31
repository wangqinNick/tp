package seedu.duke.data;

import java.time.DayOfWeek;
import java.util.HashMap;

public class TimeTable {
    private HashMap<Integer, LessonManager> semesterMap = new HashMap<>();
    public int semStartWeekNum;
    public int semEndWeekNum;
    public int semRecessWeekNum;

    public TimeTable() {

    }

    public void initWeek(int weekNum) {
        semesterMap.put(weekNum, new LessonManager());
    }

    public int getSemEndWeekNum() {
        return semEndWeekNum;
    }

    public int getSemRecessWeekNum() {
        return semRecessWeekNum;
    }

    public int getSemStartWeekNum() {
        return semStartWeekNum;
    }

    public int countLessonManagers() {
        return semesterMap.size();
    }

    public LessonManager getLessonManagerOfWeek(int weekNum) {
        return semesterMap.get(weekNum);
    }

    public void deleteLessonById(DayOfWeek dayOfWeek, String id) {
        for (LessonManager eachWeek : semesterMap.values()) {
            eachWeek.removeLessonById(dayOfWeek, id);
        }
    }

    public int countWeekLessons(int week) {
        return semesterMap.get(week).countTotalLessons();
    }

    public int countTimetableLessons() {
        int totalLessonNum = 0;
        for (LessonManager eachWeek : semesterMap.values()) {
            totalLessonNum += eachWeek.countTotalLessons();
        }
        return totalLessonNum;
    }

    // required for fastJSON, not used otherwise
    public HashMap<Integer, LessonManager> getSemesterMap() {
        return semesterMap;
    }

    // required for fastJSON, not used otherwise
    public void setSemesterMap(HashMap<Integer, LessonManager> semesterMap) {
        this.semesterMap = semesterMap;
    }

    public void setSemEndWeekNum(int semEndWeekNum) {
        this.semEndWeekNum = semEndWeekNum;
    }

    public void setSemRecessWeekNum(int semRecessWeekNum) {
        this.semRecessWeekNum = semRecessWeekNum;
    }

    public void setSemStartWeekNum(int semStartWeekNum) {
        this.semStartWeekNum = semStartWeekNum;
    }
}
