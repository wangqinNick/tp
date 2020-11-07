//@@author aseanseen

package seedu.ravi.command.timetable;

import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.Lesson;
import seedu.ravi.data.TimeTableManager;

import java.time.DayOfWeek;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_LESSON_NOT_FOUND;
import static seedu.ravi.util.Message.MESSAGE_DELETE_LESSON_SUCCESS;

public class TimeTableDeleteCommand extends TimeTableCommand {
    private final DayOfWeek dayOfWeek;
    private final int lessonIndexToDelete;

    public TimeTableDeleteCommand(DayOfWeek dayOfWeek, int lessonIndexToDelete) {
        this.dayOfWeek = dayOfWeek;
        this.lessonIndexToDelete = lessonIndexToDelete;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Method runs during execution. Deletes the lesson from the timetable.
     *
     * @return
     *  The deleted lesson object.
     * @throws IndexOutOfBoundsException
     *  When the index given by the user is out of bounds.
     */
    public Lesson removeLessonFromTimeTable() throws IndexOutOfBoundsException {
        return TimeTableManager.deleteLesson(dayOfWeek, lessonIndexToDelete);
    }

    @Override
    public CommandResult execute() {
        try {
            Lesson deletedLesson = removeLessonFromTimeTable();
            return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, deletedLesson.toString()));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(MESSAGE_LESSON_NOT_FOUND, true);
        }
    }
}
