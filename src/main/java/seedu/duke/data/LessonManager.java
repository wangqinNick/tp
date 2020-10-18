package seedu.duke.data;

import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.LessonNotFoundException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class LessonManager {
    private static final HashMap<DayOfWeek, ArrayList<Lesson>> lessonMap = new HashMap<>();

    /**
     * Initialise the lessonMap when it is empty.
     */
    public static void initialise() {
        for (DayOfWeek eachDay : DayOfWeek.values()) {
            lessonMap.put(eachDay, new ArrayList<>());
        }
    }

    /**
     * Inserts a Lesson object in the correct position in the correct ArrayList in lessonMap, based on its day and time.
     *
     * @param newLesson
     *  The new Lesson object
     */
    public static void addLesson(Lesson newLesson) throws LessonInvalidTimeException{
        DayOfWeek lessonDay = newLesson.getDay();

        // if lessonMap is not initialised yet...
        if (lessonMap.keySet().size() == 0) {
            initialise();
            lessonMap.get(lessonDay).add(newLesson);
            return;
        }

        int indexToInsertNewLesson = -1;
        for (int i = 0; i < lessonMap.get(lessonDay).size(); i++) {
            Lesson eachLesson = lessonMap.get(lessonDay).get(i);
            if (newLesson.checkOverlap(eachLesson)) {
                throw new LessonInvalidTimeException();
            }
            // insert before the lesson which starts right after newLesson
            // each day ArrayList in lessonMap is hence always sorted due to addLesson's logic
            if (eachLesson.isAfter(newLesson)) {
                indexToInsertNewLesson = i;
                break;
            }
        }

        if (indexToInsertNewLesson == -1) { // lesson is the latest lesson in the day
            lessonMap.get(lessonDay).add(newLesson);
        } else {
            lessonMap.get(lessonDay).add(indexToInsertNewLesson, newLesson);
        }
    }

    /**
     * Removes the lesson at lessonIndex on the given day.
     *
     * @param day
     *  The specified day
     * @param lessonIndex
     *  The index of the lesson to be removed
     */
    public static void removeLesson(DayOfWeek day, int lessonIndex) throws LessonNotFoundException {
        if (!lessonMap.containsKey(day)) {
            throw new LessonNotFoundException();
        }
        if (lessonIndex < 0 || lessonIndex >= lessonMap.get(day).size()) {
            throw new LessonNotFoundException();
        }
        lessonMap.get(day).remove(lessonIndex);
    }

    /**
     * Returns the ArrayList of lessons on the given day.
     *
     * @param day
     *  The specified day
     * @return
     *  The ArrayList of lessons on that day
     * @throws LessonNotFoundException
     *  If there are no lessons on that day
     */
    public static ArrayList<Lesson> getDayLessonList(DayOfWeek day) {
        return lessonMap.get(day);
    }

    public static int getLessonCountOnDay(DayOfWeek day) {
        return lessonMap.get(day).size();
    }

    /**
     * Clears the lesson ArrayList for a given day.
     *
     * @param day
     *  The specified day
     */
    public static void clearDayLesson(DayOfWeek day) {
        lessonMap.put(day, new ArrayList<>());
    }
}
