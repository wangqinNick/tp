package seedu.duke.exception;

public class LessonOverlapException extends Throwable {
    public String overlapLessonStr;
    public LessonOverlapException(String overlapLessonStr) {
        this.overlapLessonStr = overlapLessonStr;
    }
}
