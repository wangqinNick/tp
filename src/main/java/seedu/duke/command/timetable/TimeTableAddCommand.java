package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.RepeatFrequencyInvalidException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_OVERLAP;
import static seedu.duke.util.ExceptionMessage.MESSAGE_REPEAT_FREQUENCY_UNKNOWN;
import static seedu.duke.util.Message.MESSAGE_ADD_LESSON_SUCCESS;

public class TimeTableAddCommand extends TimeTableCommand {
    private Lesson newLesson;
    private int repeatFreq;

    public TimeTableAddCommand(Lesson newLesson, int repeatFreq) {
        this.newLesson = newLesson;
        this.repeatFreq = repeatFreq;
    }

    public void addLessonToTimeTable() throws LessonInvalidTimeException, RepeatFrequencyInvalidException {
        if (repeatFreq < 0 || repeatFreq > 3) {
            throw new RepeatFrequencyInvalidException();
        }
        TimeTableManager.addLesson(newLesson, repeatFreq);
    }

    @Override
    public CommandResult execute() {
        String message = "";
        try {
            addLessonToTimeTable();
            message = MESSAGE_ADD_LESSON_SUCCESS;
        } catch (LessonInvalidTimeException e) {
            message = MESSAGE_LESSON_OVERLAP;
        } catch (RepeatFrequencyInvalidException e) {
            message = MESSAGE_REPEAT_FREQUENCY_UNKNOWN;
        }
        return new CommandResult(message);
    }
}
