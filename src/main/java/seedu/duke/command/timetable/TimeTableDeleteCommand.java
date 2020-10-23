package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.LessonManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonNotFoundException;

import java.time.DayOfWeek;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_NOT_FOUND;

public class TimeTableDeleteCommand extends TimeTableCommand {
    private DayOfWeek dayOfWeek;
    private int currWeekNum;
    private int lessonIndexToDelete;

    public TimeTableDeleteCommand(DayOfWeek dayOfWeek, int currWeekNum, int lessonIndexToDelete) {
        this.dayOfWeek = dayOfWeek;
        this.currWeekNum = currWeekNum;
        this.lessonIndexToDelete = lessonIndexToDelete;
    }

    public void removeLessonFromTimeTable() throws LessonNotFoundException {
        LessonManager lessonManager = TimeTableManager.getLessonManager(currWeekNum);
        lessonManager.removeLesson(dayOfWeek, lessonIndexToDelete);
    }

    @Override
    public CommandResult execute() {
        String message = "";
        try {
            removeLessonFromTimeTable();
        } catch (LessonNotFoundException e) {
            message = MESSAGE_LESSON_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
