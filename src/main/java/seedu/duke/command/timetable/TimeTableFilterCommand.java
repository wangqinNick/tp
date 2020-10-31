package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonFilter;
import seedu.duke.data.TimeTableManager;
import seedu.duke.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static seedu.duke.data.TimeTableManager.getCurrNusWeekStr;

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
