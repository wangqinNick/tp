package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonManager;
import seedu.duke.ui.TextUi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static seedu.duke.data.TimeTableManager.getCurrWeekNum;
import static seedu.duke.data.TimeTableManager.getSpecifiedWeekLessons;

public class TimeTableViewCommand extends TimeTableCommand {
    private int numOfDays;
    private LocalDateTime now = LocalDateTime.now();

    public TimeTableViewCommand(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public String generateDayTimeTable() {
        ArrayList<Lesson> dayLessonList;
        String out = "";
        for (;numOfDays > 0; numOfDays--) {
            dayLessonList = LessonManager.getDayLessonList(now.getDayOfWeek());

            out += TextUi.DIVIDER_LINE + System.lineSeparator();
            out += now.getDayOfWeek() + System.lineSeparator();

            DateTimeFormatter time = DateTimeFormatter.ofPattern("Hmm");

            for (Lesson lesson : dayLessonList) {
                String start = lesson.getStartTime().format(time);
                String end = lesson.getEndTime().format(time);
                out += printTimetableBlock(lesson.getModuleCode(), lesson.getLessonTypeString(), start, end);
            }
        }
        return out;
    }

    public String printTimetableBlock(String moduleCode, String lessonType, String start, String end){
        String message = "";
        message += " | " + start + "-" + end;
        message += " | " + moduleCode + " " + lessonType + " |\n";
        return message;
    }

    public String generateWeekTimeTable() {
        int currWeek = getCurrWeekNum();
        ArrayList<ArrayList<Lesson>> weekLessons = getSpecifiedWeekLessons(currWeek);
        
        try {
            String output = "";
            for (Lesson eachLesson : weekLessons.get(0)) {
                output += eachLesson.toString() + "\n";
            }
            return output;
        } catch (IndexOutOfBoundsException e) {
            return "oops";
        }
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
