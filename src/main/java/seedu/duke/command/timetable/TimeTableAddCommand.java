package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Lesson;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.RepeatFrequencyInvalidException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_INVALID_TIME;
import static seedu.duke.util.ExceptionMessage.MESSAGE_REPEAT_FREQUENCY_UNKNOWN;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.Message.MESSAGE_ADD_LESSON_SUCCESS;

public class TimeTableAddCommand extends TimeTableCommand {
    private final Lesson newLesson;
    private final int repeatFreq;

    public TimeTableAddCommand(Lesson newLesson, int repeatFreq) {
        this.newLesson = newLesson;
        this.repeatFreq = repeatFreq;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Method runs during execution. Adds the lesson to the timetable.
     *
     * @throws LessonInvalidTimeException
     *  When the lesson overlaps with an existing lesson.
     * @throws RepeatFrequencyInvalidException
     *  When the repeat parameter given by the user is not from 0 - 3.
     * @throws ModuleManager.ModuleNotFoundException
     *  When the module to be added to the timetable is not in the module list.
     */
    public void addLessonToTimeTable() throws LessonInvalidTimeException, RepeatFrequencyInvalidException,
            ModuleManager.ModuleNotFoundException {
        if (repeatFreq < 0 || repeatFreq > 3) {
            throw new RepeatFrequencyInvalidException();
        }
        TimeTableManager.addLesson(newLesson, repeatFreq);
    }

    @Override
    public CommandResult execute() {
        String message;
        try {
            addLessonToTimeTable();
            message = MESSAGE_ADD_LESSON_SUCCESS;
        } catch (LessonInvalidTimeException e) {
            message = MESSAGE_LESSON_INVALID_TIME;
        } catch (RepeatFrequencyInvalidException e) {
            message = MESSAGE_REPEAT_FREQUENCY_UNKNOWN;
        } catch (ModuleManager.ModuleNotFoundException e) {
            message = MESSAGE_MODULE_NOT_FOUND;
        }
        return new CommandResult(message);
    }
}
