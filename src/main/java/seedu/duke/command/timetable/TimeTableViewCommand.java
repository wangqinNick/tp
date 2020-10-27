package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.duke.data.TimeTableManager.getCurrWeekNum;
import static seedu.duke.data.TimeTableManager.getSpecificDayLessons;
import static seedu.duke.data.TimeTableManager.getSpecifiedWeekLessons;

public class TimeTableViewCommand extends TimeTableCommand {
    private final int numOfDays;
    private final LocalDateTime now = LocalDateTime.now();

    public TimeTableViewCommand(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String generateDayTimeTable() {
        ArrayList<Lesson> lessonList;
        DayOfWeek day = now.getDayOfWeek();
        lessonList = getSpecificDayLessons(day);
        return TextUi.printDayTimetable(day, lessonList);
    }

    public String generateWeekTimeTable() {
        int currentWeek = getCurrWeekNum();
        int dayVal = 1;
        ArrayList<ArrayList<Lesson>> weekLessons = getSpecifiedWeekLessons(currentWeek);
        StringBuilder out = new StringBuilder();

        for (ArrayList<Lesson> dayLesson : weekLessons) {
            DayOfWeek day = DayOfWeek.of(dayVal);
            out.append(TextUi.printDayTimetable(day, dayLesson));
            dayVal++;
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
