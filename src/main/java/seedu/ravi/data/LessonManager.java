package seedu.ravi.data;

import seedu.ravi.RaviLogger;
import seedu.ravi.exception.LessonInvalidTimeException;
import seedu.ravi.exception.LessonNotFoundException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class LessonManager {
    private HashMap<DayOfWeek, ArrayList<Lesson>> lessonMap = new HashMap<>();
    private static final RaviLogger logger = new RaviLogger(LessonManager.class.getName());

    /**
     * Initialise the lessonMap upon construction.
     */
    public LessonManager() {
        for (DayOfWeek eachDay : DayOfWeek.values()) {
            lessonMap.put(eachDay, new ArrayList<>());
        }
    }

    /**
     * Inserts a Lesson object in the correct position in the correct ArrayList in lessonMap, based on its day and time.
     *
     * @param newLesson
     *  The new Lesson object
     * @throws LessonInvalidTimeException
     *  When the new lesson overlaps with an existing lesson
     */
    public void addLesson(Lesson newLesson) throws LessonInvalidTimeException {
        DayOfWeek lessonDay = newLesson.getDay();
        logger.getLogger().info("Trying to add lesson: " + newLesson.toString());

        // if lessonMap is not initialised yet...
        if (lessonMap.keySet().size() == 0) {
            logger.getLogger().info("Adding to uninitialised lessonMap");
            lessonMap.get(lessonDay).add(newLesson);
            return;
        }

        int indexToInsertNewLesson = -1;
        for (int i = 0; i < lessonMap.get(lessonDay).size(); i++) {
            Lesson eachLesson = lessonMap.get(lessonDay).get(i);
            if (newLesson.checkOverlap(eachLesson)) {
                logger.getLogger().warning("New lesson overlaps with the lesson: " + eachLesson.toString());
                throw new LessonInvalidTimeException();
            }
            // insert before the lesson which starts right after newLesson
            // each day ArrayList in lessonMap is hence always sorted due to addLesson's logic
            if (eachLesson.isAfter(newLesson)) {
                logger.getLogger().info("Lesson inserted at index " + i);
                indexToInsertNewLesson = i;
                break;
            }
        }

        if (indexToInsertNewLesson == -1) { // lesson is the latest lesson in the day
            logger.getLogger().info("Lesson inserted at index " + lessonMap.get(lessonDay).size());
            lessonMap.get(lessonDay).add(newLesson);
        } else {
            logger.getLogger().info("Lesson inserted at index " + indexToInsertNewLesson);
            lessonMap.get(lessonDay).add(indexToInsertNewLesson, newLesson);
        }
    }

    // required for fastJSON, not used otherwise
    public void addLesson(DayOfWeek day, Lesson lesson) {
        lessonMap.get(day).add(lesson);
    }

    /**
     * Removes the lesson at lessonIndex on the given day.
     *
     * @param day
     *  The specified day
     * @param lessonIndex
     *  The index of the lesson to be removed
     * @throws LessonNotFoundException
     *  When the lesson to be removed does not exist in the lesson map
     */
    public void removeLesson(DayOfWeek day, int lessonIndex) throws LessonNotFoundException {
        if (!lessonMap.containsKey(day)) {
            logger.getLogger().warning("lessonMap does not contain day: " + day);
            throw new LessonNotFoundException();
        }
        if (lessonIndex < 0 || lessonIndex >= lessonMap.get(day).size()) {
            logger.getLogger().warning("The lesson index is out of bounds; size is: " + lessonMap.get(day).size());
            throw new LessonNotFoundException();
        }
        lessonMap.get(day).remove(lessonIndex);
    }

    /**
     * Removes the lesson at lessonIndex on the given day.
     *
     * @param day
     *  The specified day
     * @param id
     *  The hiddenId of the lesson to be removed
     */
    public void removeLessonById(DayOfWeek day, String id) {
        if (!lessonMap.containsKey(day)) {
            logger.getLogger().warning("lessonMap does not contain day: " + day);
            return;
        }
        for (int i = 0; i < lessonMap.get(day).size(); i++) {
            if (lessonMap.get(day).get(i).getHiddenId().equals(id)) {
                lessonMap.get(day).remove(i);
                logger.getLogger().info("Lesson removed at index: " + i);
                return;
            }
        }
    }

    /**
     * Returns the ArrayList of lessons on the given day.
     *
     * @param day
     *  The specified day
     * @return
     *  The ArrayList of lessons on that day
     */
    public ArrayList<Lesson> getDayLessonList(DayOfWeek day) {
        return lessonMap.get(day);
    }

    public int getLessonCountOnDay(DayOfWeek day) {
        return lessonMap.get(day).size();
    }

    public int countTotalLessons() {
        int numLessons = 0;
        for (ArrayList<Lesson> eachDay : lessonMap.values()) {
            numLessons += eachDay.size();
        }
        return numLessons;
    }

    /**
     * Resets lessonMap to blank state.
     */
    public void clearAllLessons() {
        for (DayOfWeek eachDay : DayOfWeek.values()) {
            lessonMap.put(eachDay, new ArrayList<>());
        }
    }

    // required for fastJSON, not used otherwise
    public HashMap<DayOfWeek, ArrayList<Lesson>> getLessonMap() {
        return lessonMap;
    }

    // required for fastJSON, not used otherwise
    public void setLessonMap(HashMap<DayOfWeek, ArrayList<Lesson>> lessonMap) {
        this.lessonMap = lessonMap;
    }
}
