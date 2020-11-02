package seedu.duke.parser;

import seedu.duke.data.Lesson;
import seedu.duke.data.LessonFilter;
import seedu.duke.data.LessonType;
import seedu.duke.data.ModuleManager;
import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.ModuleNotFoundException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;

import static seedu.duke.parser.TimeTableCommandParser.DAY_GROUP;
import static seedu.duke.parser.TimeTableCommandParser.END_TIME_GROUP;
import static seedu.duke.parser.TimeTableCommandParser.LESSON_TYPE_GROUP;
import static seedu.duke.parser.TimeTableCommandParser.MODULE_GROUP;
import static seedu.duke.parser.TimeTableCommandParser.START_TIME_GROUP;

public class LessonParser {
    public static final String SKIP_PARAMETER_CHAR = "-";

    /**
     * Parses the user input for the Lesson parameters, to create a Lesson.
     *
     * @param lessonMatcher Matcher for the parameters.
     * @return Lesson created from the Lesson parameters.
     * @throws ModuleNotFoundException When the module is not found in the module list.
     * @throws LessonInvalidTimeException When the start is greater than or equal to end time of the Lesson.
     * @throws DateTimeParseException When the time of either the start or end is in the wrong format.
     * @throws IllegalArgumentException When the Lesson to be created has illegal arguments.
     */
    public static Lesson parseLesson(Matcher lessonMatcher) throws
            ModuleNotFoundException, LessonInvalidTimeException, DateTimeParseException {
        String modString = lessonMatcher.group(MODULE_GROUP).toUpperCase().trim();
        String dayString = lessonMatcher.group(DAY_GROUP).toUpperCase().trim();
        String startTimeString = lessonMatcher.group(START_TIME_GROUP).trim();
        String endTimeString = lessonMatcher.group(END_TIME_GROUP).trim();
        String lessonTypeString = lessonMatcher.group(LESSON_TYPE_GROUP).toUpperCase().trim();
        // Check if modString is in module list
        if (!ModuleManager.doesContainMod(modString)) {
            throw new ModuleNotFoundException();
        }
        // Convert dayString to DayOfWeek
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayString);
        // Convert start, end strings to LocalTime
        DateTimeFormatter time = DateTimeFormatter.ofPattern("Hmm");
        LocalTime startTime = LocalTime.parse(startTimeString, time);
        LocalTime endTime = LocalTime.parse(endTimeString, time);
        // Convert lessonTypeString to lessonType
        LessonType lessonType = LessonType.valueOf(lessonTypeString);
        // Create the Lesson object
        Lesson newLesson = new Lesson(modString, lessonType, dayOfWeek, startTime, endTime);
        return newLesson;
    }


    public static ArrayList<LessonFilter> parseFilterLesson(Matcher filterMatcher) throws
            ModuleNotFoundException, DateTimeParseException,
            IllegalArgumentException {
        final String modString = filterMatcher.group(MODULE_GROUP).toUpperCase().trim();
        final String dayString = filterMatcher.group(DAY_GROUP).toUpperCase().trim();
        final String startTimeString = filterMatcher.group(START_TIME_GROUP).trim();
        final String endTimeString = filterMatcher.group(END_TIME_GROUP).trim();
        final String lessonTypeString = filterMatcher.group(LESSON_TYPE_GROUP).toUpperCase().trim();

        ArrayList<LessonFilter> filterList = new ArrayList<>();

        if (!modString.equals(SKIP_PARAMETER_CHAR)) {
            // Check if modString is in module list
            if (!ModuleManager.doesContainMod(modString)) {
                throw new ModuleNotFoundException();
            } else {
                LessonFilter modFilter = (l) -> l.getModuleCode().equals(modString);
                filterList.add(modFilter);
            }
        }

        if (!dayString.equals(SKIP_PARAMETER_CHAR)) {
            // Convert dayString to DayOfWeek
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayString);
            LessonFilter dayFilter = (l) -> l.getDay().equals(dayOfWeek);
            filterList.add(dayFilter);
        }

        // Convert start, end strings to LocalTime
        DateTimeFormatter time = DateTimeFormatter.ofPattern("Hmm");

        // Filter for start time. i.e. Filter lessons that start after HH:MM
        if (!startTimeString.equals(SKIP_PARAMETER_CHAR)) {
            LocalTime startTime = LocalTime.parse(startTimeString, time);
            LessonFilter startFilter = (l) ->
                    (l.getStartTime().isAfter(startTime) || l.getStartTime().equals(startTime));
            filterList.add(startFilter);
        }

        // Filter for start time. i.e. Filter lessons that end before HH:MM
        if (!endTimeString.equals(SKIP_PARAMETER_CHAR)) {
            LocalTime endTime = LocalTime.parse(endTimeString, time);
            LessonFilter endFilter = (l) ->
                    (l.getEndTime().isBefore(endTime) || l.getEndTime().equals(endTime));
            filterList.add(endFilter);
        }

        if (!lessonTypeString.equals(SKIP_PARAMETER_CHAR)) {
            // Convert lessonTypeString to lessonType
            LessonType lessonType = LessonType.valueOf(lessonTypeString);
            LessonFilter lessonTypeFilter = (l) -> l.getLessonType().equals(lessonType);
            filterList.add(lessonTypeFilter);
        }

        return filterList;
    }

}
