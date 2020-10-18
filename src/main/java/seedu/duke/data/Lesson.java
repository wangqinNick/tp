package seedu.duke.data;

import seedu.duke.exception.LessonInvalidTimeException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Lesson {
    private String moduleCode;
    private String description;
    private LessonType lessonType;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;

    public Lesson(String moduleCode, LessonType lessonType, DayOfWeek day, LocalTime startTime, LocalTime endTime)
            throws LessonInvalidTimeException {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new LessonInvalidTimeException();
        }

        this.moduleCode = moduleCode;
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

    public String getModuleCode() {
        return moduleCode;
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

    public boolean isAfter(Lesson otherLesson) {
        return otherLesson.getEndTime().isBefore(startTime) || otherLesson.getEndTime().equals(startTime);
    }

    public boolean isBefore(Lesson otherLesson) {
        return otherLesson.getStartTime().isAfter(endTime) || otherLesson.getStartTime().equals(endTime);
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
        // lessons are constructed with valid start-end times
        // to check NO OVERLAP, ensure otherEnd <= currentStart xor otherStart >= currentEnd
        return !(isAfter(otherLesson) ^ isBefore(otherLesson));
    }

    /**
     * Generates string based on lesson type.
     *
     * @return
     *  String of lesson type
     */
    private String getLessonTypeString() {
        switch (lessonType) {
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
        DateTimeFormatter time = DateTimeFormatter.ofPattern("Hmm");
        return String.format("%s %s: %s %s-%s", moduleCode, getLessonTypeString(),
                day.toString(), startTime.format(time), endTime.format(time));
    }
}
