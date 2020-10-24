package seedu.duke.data;

import seedu.duke.exception.LessonInvalidTimeException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Lesson {
    private String moduleCode;
    private LessonType lessonType;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String hiddenId;

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

    public LessonType getLessonType() {
        return lessonType;
    }

    /**
     * Generates the unique hiddenId for each instance of this Lesson class.
     * No two instances of Lesson class should share a hiddenId.
     */
    public void generateHiddenId() {
        hiddenId = UUID.randomUUID().toString();
    }

    public String getHiddenId() {
        return hiddenId;
    }

    /**
     * Checks if 'this' lesson is after otherLesson.
     * @param otherLesson
     *  The other lesson object to check against
     * @return
     *  Whether 'this' lesson is after otherLesson
     */
    public boolean isAfter(Lesson otherLesson) {
        return otherLesson.getEndTime().isBefore(startTime) || otherLesson.getEndTime().equals(startTime);
    }

    /**
     * Checks if 'this' lesson is before otherLesson.
     * @param otherLesson
     *  The other lesson object to check against
     * @return
     *  Whether 'this' lesson is before otherLesson
     */
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
        // for NO OVERLAP, (isAfter ^ isBefore) is true
        // so OVERLAP is just (isAfter == isBefore)
        return isAfter(otherLesson) == isBefore(otherLesson);
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
     * e.g. CS1010 Lecture: MONDAY 1400-1500
     *
     * @return
     *  String representation of lesson
     */
    public String toString() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("Hmm");
        return String.format("%s %s: %s %s-%s", moduleCode, getLessonTypeString(),
                day.toString(), startTime.format(time), endTime.format(time));
    }

    // vvv Required for fastJSON, not used otherwise vvv
    public Lesson() {}

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public void setLessonType (LessonType lessonType) {
        this.lessonType = lessonType;
    }

    public void setHiddenId(String hiddenId) {
        this.hiddenId = hiddenId;
    }
    // ^^^ Required for fastJSON, not used otherwise ^^^
}
