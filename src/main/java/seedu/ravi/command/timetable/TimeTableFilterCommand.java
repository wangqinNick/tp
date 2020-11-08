package seedu.ravi.command.timetable;

import seedu.ravi.command.CommandResult;
import seedu.ravi.data.Lesson;
import seedu.ravi.data.LessonFilter;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static seedu.ravi.data.TimeTableManager.getCurrNusWeekStr;

public class TimeTableFilterCommand extends TimeTableCommand {
    private final ArrayList<LessonFilter> filterList;
    private final LocalDate now = LocalDate.now();

    public TimeTableFilterCommand(ArrayList<LessonFilter> filterList) {
        this.filterList = filterList;
    }

    public String getFilteredWeekTimetable() {
        ArrayList<ArrayList<Lesson>> weekLessons = TimeTableManager.filterWeekWithFilterList(filterList);
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
        return new CommandResult(getFilteredWeekTimetable());
    }
}
