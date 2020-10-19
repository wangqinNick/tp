package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonNotFoundException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_NOT_FOUND;

public class TimeTableDeleteCommand extends TimeTableCommand {
    private Lesson lessonToDelete;
    private int currWeekNum;
    private int lessonIndexToDelete;

    public TimeTableDeleteCommand(Lesson lessonToDelete, int currWeekNum, int lessonIndexToDelete) {
        this.lessonToDelete = lessonToDelete;
        this.currWeekNum = currWeekNum;
        this.lessonIndexToDelete = lessonIndexToDelete;
    }

    public void removeLessonFromTimeTable() throws LessonNotFoundException {
        LessonManager lessonManager = TimeTableManager.getLessonManager(currWeekNum);
        lessonManager.removeLesson(lessonToDelete.getDay() , lessonIndexToDelete);
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
