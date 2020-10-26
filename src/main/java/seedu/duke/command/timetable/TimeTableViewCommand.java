package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.LessonManager;
import seedu.duke.ui.TextUi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static seedu.duke.data.TimeTableManager.getCurrWeekNum;
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

        ArrayList<Lesson> dayLessonList;
        String out = "";
        for (;numOfDays > 0; numOfDays--) {
            dayLessonList = LessonManager.getDayLessonList(now.getDayOfWeek());
            out += TextUi.DIVIDER_LINE + System.lineSeparator();
            out += now.getDayOfWeek() + System.lineSeparator();

            DateTimeFormatter time = DateTimeFormatter.ofPattern("Hmm");
            int lastLesson = dayLessonList.size() - 1;
            String lessonsEnd = dayLessonList.get(lastLesson).getEndTime().format(time);
            String lessonsStart = dayLessonList.get(0).getStartTime().format(time);
            out+= printTimeline(lessonsStart, lessonsEnd);
            out+= System.lineSeparator();
            for (Lesson lesson : dayLessonList) {
                int start = Integer.parseInt(lesson.getStartTime().format(time));
                int end = Integer.parseInt(lesson.getEndTime().format(time));
                out += printTimetableBlock(lesson.getModuleCode(), lesson.getLessonTypeChar(), start, end);
                //out+= lesson.getModuleCode() + " " + lesson.getLessonTypeChar() + System.lineSeparator();
                //out += lesson.toString() + System.lineSeparator();
            }
        }
        return out;

    }

    public String printTimetableBlock(String moduleCode, String lessonType, int startTime, int endTime){
        String block = "";
        while (startTime < endTime) {
            block += "  " + moduleCode + " " + lessonType + "  |";
            startTime += 100;
        }
        return block;
    }

    public String printTimeline(String startTime, String endTime){
        final StringBuilder message = new StringBuilder();
        int start = Integer.parseInt(startTime);
        int end = Integer.parseInt(endTime);
        while (start < end) {
            message.append(start);
            message.append("----------");
            start += 100;
        }
        message.append(end);
        return message.toString();
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
