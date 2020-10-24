package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonNotFoundException;

import java.time.DayOfWeek;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_DELETE_LESSON_SUCCESS;

public class TimeTableDeleteCommand extends TimeTableCommand {
    private DayOfWeek dayOfWeek;
    private int lessonIndexToDelete;

    public TimeTableDeleteCommand(DayOfWeek dayOfWeek, int lessonIndexToDelete) {
        this.dayOfWeek = dayOfWeek;
        this.lessonIndexToDelete = lessonIndexToDelete;
    }

    public void removeLessonFromTimeTable() {
        TimeTableManager.removeLesson(dayOfWeek, lessonIndexToDelete);
    }

    @Override
    public CommandResult execute() {
        String message = "";
        try {
            removeLessonFromTimeTable();
            message = MESSAGE_DELETE_LESSON_SUCCESS;
        } catch (IndexOutOfBoundsException e) {
            message = MESSAGE_LESSON_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
