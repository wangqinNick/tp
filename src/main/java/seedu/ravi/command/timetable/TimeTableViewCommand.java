package seedu.ravi.command.timetable;

import seedu.ravi.command.CommandResult;
import seedu.ravi.data.Lesson;
import seedu.ravi.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import java.util.ArrayList;

import static seedu.ravi.data.TimeTableManager.getCurrWeekNum;
import static seedu.ravi.data.TimeTableManager.getCurrNusWeekStr;
import static seedu.ravi.data.TimeTableManager.getSpecificDayLessons;
import static seedu.ravi.data.TimeTableManager.getSpecifiedWeekLessons;

public class TimeTableViewCommand extends TimeTableCommand {
    private final int numOfDays;
    private final LocalDate now = LocalDate.now();

    public TimeTableViewCommand(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String generateDayTimeTable() {
        ArrayList<Lesson> lessonList;
        lessonList = getSpecificDayLessons(now.getDayOfWeek());
        return "Current NUS Week: " + getCurrNusWeekStr() + "\n" + TextUi.printDayTimetable(now, lessonList);
    }

    public String generateWeekTimeTable() {
        int currentWeek = getCurrWeekNum();
        ArrayList<ArrayList<Lesson>> weekLessons = getSpecifiedWeekLessons(currentWeek);
        StringBuilder out = new StringBuilder();
        LocalDate dateIterator = now.with(previousOrSame(DayOfWeek.MONDAY));
        out.append("Current NUS Week: " + getCurrNusWeekStr() + "\n");
        for (ArrayList<Lesson> dayLesson : weekLessons) {
            out.append(TextUi.printDayTimetable(dateIterator, dayLesson));
            dateIterator = dateIterator.plusDays(1);
        }

        return out.toString();
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
