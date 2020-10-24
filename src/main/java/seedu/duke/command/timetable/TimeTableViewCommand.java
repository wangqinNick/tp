package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.duke.data.TimeTableManager.getCurrNusWeekNum;
import static seedu.duke.data.TimeTableManager.getSpecificDayLessons;
import static seedu.duke.data.TimeTableManager.getSpecifiedWeekLessons;

public class TimeTableViewCommand extends TimeTableCommand {
    private int numOfDays;
    private LocalDateTime now = LocalDateTime.now();

    public TimeTableViewCommand(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String generateDayTimeTable() {
        DayOfWeek today = now.getDayOfWeek();
        ArrayList<Lesson> todayLessons = getSpecificDayLessons(today);

        // TODO: do display logic inside TextUi
        return "TODO";

        /*
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
        */
    }

    public String generateWeekTimeTable() {
        ArrayList<ArrayList<Lesson>> weekLessons = getSpecifiedWeekLessons(getCurrNusWeekNum());
        // TODO: do display logic inside TextUi
        return "TODO";
    }

    @Override
    public CommandResult execute() {
        if (numOfDays == 1) {
            return new CommandResult(generateDayTimeTable());
        } else {
            return new CommandResult(generateWeekTimeTable());
        }
    }
}
