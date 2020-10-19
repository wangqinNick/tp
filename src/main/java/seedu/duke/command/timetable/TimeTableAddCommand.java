package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonInvalidTimeException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_OVERLAP;

public class TimeTableAddCommand extends TimeTableCommand {
    private Lesson newLesson;
    private int currWeekNum;
    private int repeatFreq;

    public TimeTableAddCommand(Lesson newLesson, int currWeekNum, int repeatFreq) {
        this.newLesson = newLesson;
        this.currWeekNum = currWeekNum;
        this.repeatFreq = repeatFreq;
    }

    public void addLessonToTimeTable() throws LessonInvalidTimeException {
        int semEndWeekNum = TimeTableManager.getSemEndWeekNum();
        int changingWeekNum = currWeekNum;
        LessonManager lessonManager;
                switch (repeatFreq) {
        case 0: // Only once
            addLesson(changingWeekNum);
            break;
        case 1: // Repeats every week
            for (; changingWeekNum <= semEndWeekNum; changingWeekNum++) {
                addLesson(changingWeekNum);
            }
            break;
        case 2: // Repeats once every 2 weeks
            for (; changingWeekNum <= semEndWeekNum; changingWeekNum+=2) {
                addLesson(changingWeekNum);
            }
            break;
        }

    }

    private void addLesson(int changingWeekNum) throws LessonInvalidTimeException {
        LessonManager lessonManager = TimeTableManager.getLessonManager(changingWeekNum);
        lessonManager.addLesson(newLesson);
    }

    @Override
    public CommandResult execute() {
        String message = "";
        try {
            addLessonToTimeTable();
        } catch (LessonInvalidTimeException e) {
            message = MESSAGE_LESSON_OVERLAP;
        }
        return new CommandResult(message);
    }
}
