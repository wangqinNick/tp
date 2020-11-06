package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Lesson;
import seedu.duke.data.TimeTableManager;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.LessonOverlapException;
import seedu.duke.exception.ModuleNotFoundException;
import seedu.duke.exception.RepeatFrequencyInvalidException;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_INVALID_TIME;
import static seedu.duke.util.ExceptionMessage.MESSAGE_LESSON_OVERLAP;
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
     * @throws ModuleNotFoundException
     *  When the module associated with the Lesson to be added is not in the module list.
     * @throws LessonOverlapException
     *  When the Lesson to be added overlaps with an existing lesson.
     */
    public void addLessonToTimeTable() throws LessonInvalidTimeException, RepeatFrequencyInvalidException,
            ModuleNotFoundException, LessonOverlapException {
        if (repeatFreq < 0 || repeatFreq > 3) {
            throw new RepeatFrequencyInvalidException();
        }
        TimeTableManager.addLesson(newLesson, repeatFreq);
    }

    public String parseRepeatFreq() {
        switch (repeatFreq) {
        case 0:
            return "this week only.";
        case 1:
            return "every week.";
        case 2:
            return "every even week.";
        case 3:
            return "every odd week.";
        default:
            return "error!";
        }
    }

    @Override
    public CommandResult execute() {
        try {
            addLessonToTimeTable();
            return new CommandResult(
                    String.format(MESSAGE_ADD_LESSON_SUCCESS, newLesson.toString(), parseRepeatFreq()));
        } catch (LessonInvalidTimeException e) {
            return new CommandResult(MESSAGE_LESSON_INVALID_TIME, true);
        } catch (RepeatFrequencyInvalidException e) {
            return new CommandResult(MESSAGE_REPEAT_FREQUENCY_UNKNOWN, true);
        } catch (ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND, true);
        } catch (LessonOverlapException e) {
            return new CommandResult(String.format(MESSAGE_LESSON_OVERLAP, e.overlapLessonStr), true);
        }
    }
}
