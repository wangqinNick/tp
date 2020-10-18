package seedu.duke.data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Lesson {
    private Module module;
    private String description;
    private LessonType lessonType;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Lesson(Module module, LessonType lessonType, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
        this.module = module;
        this.lessonType = lessonType;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public DayOfWeek getDay() {
        return day;
    }

    /**
     * Checks for time period overlap with the specified Lesson.
     *
     * @param otherLesson
     *  The specified lesson
     * @return
     *  Whether their start and end times overlap
     */
    public boolean checkOverlap(Lesson otherLesson) {
        if (otherLesson.getDay() != day) {
            return false;
        }
        LocalTime otherStart = otherLesson.getStartTime();
        LocalTime otherEnd = otherLesson.getEndTime();

        if (otherStart.isAfter(startTime) && otherStart.isBefore(endTime)) {
            return true;
        } else if (otherEnd.isAfter(startTime) && otherEnd.isBefore(endTime)) {
            return true;
        }
        return false;
    }

    /**
     * Generates string based on lesson type.
     *
     * @return
     *  String of lesson type
     */
    private String getLessonTypeString() {
        switch(lessonType) {
        case LECTURE:
            return "Lecture";
        case TUTORIAL:
            return "Tutorial";
        case LAB:
            return "Lab";
        case SEMINAR:
            return "Seminar";
        case RECITATION:
            return "Recitation";
        default:
            return "Session";
        }
    }

    /**
     * Creates string representation of lesson.
     * e.g. CS1010 Lecture: Monday 1400-1500
     *
     * @return
     *  String representation of lesson
     */
    public String toString() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("Hm");
        return String.format("%s %s: %s %s-%s", module.getModuleCode(), getLessonTypeString(),
                day.toString(), startTime.format(time), endTime.format(time));
    }
}
