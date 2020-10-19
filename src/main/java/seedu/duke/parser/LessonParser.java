package seedu.duke.parser;

import seedu.duke.data.Lesson;
import seedu.duke.data.LessonType;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.exception.LessonInvalidTimeException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class LessonParser {
    private static final String MODULE_GROUP = "module";
    private static final String DAY_GROUP = "day";
    private static final String START_TIME_GROUP = "start";
    private static final String END_TIME_GROUP = "end";
    private static final String LESSON_TYPE_GROUP = "type";

    /**
     * Parses the user input for the Lesson parameters, to create a Lesson.
     *
     * @param lessonMatcher Matcher for the parameters.
     * @return Lesson created from the Lesson parameters.
     * @throws ModuleManager.ModuleNotFoundException When the module is not found in the module list.
     * @throws LessonInvalidTimeException When the start is greater than or equal to end time of the Lesson.
     * @throws DateTimeParseException When the time of either the start or end is in the wrong format.
     */
    public static Lesson parseLesson(Matcher lessonMatcher) throws ModuleManager.ModuleNotFoundException, LessonInvalidTimeException, DateTimeParseException {
        String modString = lessonMatcher.group(MODULE_GROUP).toLowerCase().trim();
        String dayString = lessonMatcher.group(DAY_GROUP).toLowerCase().trim();
        String startTimeString = lessonMatcher.group(START_TIME_GROUP).toLowerCase().trim();
        String endTimeString = lessonMatcher.group(END_TIME_GROUP).toLowerCase().trim();
        String lessonTypeString = lessonMatcher.group(LESSON_TYPE_GROUP).toLowerCase().trim();
        // Check if modString is in module list
        Module moduleCode;
        if (!ModuleManager.contains(modString)) {
            throw new ModuleManager.ModuleNotFoundException();
        } else {
            moduleCode = ModuleManager.getModule(modString);
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
        Lesson newLesson = new Lesson(moduleCode, lessonType, dayOfWeek, startTime, endTime);
        return newLesson;
    }
}
