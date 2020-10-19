package seedu.duke.command.timetable;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.TimeTableType;

import java.time.LocalDateTime;

import static seedu.duke.ui.TextUi.DIVIDER_LINE;

public class TimeTableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String FORMAT = COMMAND_WORD + " <opt>";
    private int numOfDays;

    /**
     * Option for timetable for the day or for the week
     *
     * @param typeOfTimeTable Determines if the day or week timetable is shown
     */
    public TimeTableCommand (TimeTableType typeOfTimeTable) {
        if (typeOfTimeTable.equals(TimeTableType.DAY)) {
            numOfDays = 1;
        } else if (typeOfTimeTable.equals(TimeTableType.WEEK)) {
            numOfDays = 7;
        }
    }

    /**
     * Option for timetable for a custom number of days
     *
     * @param numOfDays Determines the custom number of days timetable to be shown
     */
    public TimeTableCommand (int numOfDays) {
        this.numOfDays = numOfDays;
    }

    private LocalDateTime now = LocalDateTime.now();

    public String generateTimeTable(int numOfDays) {
        ArrayList<Lesson> dayLessonList;
        String out = "";
        for (;numOfDays > 0; numOfDays--) {
            dayLessonList = LessonManager.getDayLessonList(now.getDayOfWeek());
            out += DIVIDER_LINE + System.lineSeparator();
            out += now.getDayOfWeek() + System.lineSeparator();
            for (Lesson lesson : dayLessonList) {
                out += lesson.toString() + System.lineSeparator();
            }
        }
        out += DIVIDER_LINE;
        return out;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(generateTimeTable(numOfDays));
    }
}
