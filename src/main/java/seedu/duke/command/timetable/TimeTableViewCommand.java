package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.previousOrSame;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import java.util.ArrayList;

import static seedu.duke.data.TimeTableManager.getCurrWeekNum;
import static seedu.duke.data.TimeTableManager.getSpecificDayLessons;
import static seedu.duke.data.TimeTableManager.getSpecifiedWeekLessons;

public class TimeTableViewCommand extends TimeTableCommand {
    private final int numOfDays;
    private final LocalDate now = LocalDate.now();

    public TimeTableViewCommand(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String generateDayTimeTable() {
        ArrayList<Lesson> lessonList;
        lessonList = getSpecificDayLessons(now.getDayOfWeek());
        return TextUi.printDayTimetable(now, lessonList);
    }

    public String generateWeekTimeTable() {
        int currentWeek = getCurrWeekNum();
        ArrayList<ArrayList<Lesson>> weekLessons = getSpecifiedWeekLessons(currentWeek);
        StringBuilder out = new StringBuilder();
        LocalDate dateIterator = now.with(previousOrSame(DayOfWeek.MONDAY));

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
