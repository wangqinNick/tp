package seedu.duke.data;

import seedu.duke.exception.LessonInvalidTimeException;
import seedu.duke.exception.LessonNotFoundException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class LessonManager {
    private final HashMap<DayOfWeek, ArrayList<Lesson>> lessonMap = new HashMap<>();

    /**
     * Initialise the lessonMap when it is empty.
     */
    public void initialise() {
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
    public void addLesson(Lesson newLesson) throws LessonInvalidTimeException {
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
    public void removeLesson(DayOfWeek day, int lessonIndex) throws LessonNotFoundException {
        if (!lessonMap.containsKey(day)) {
            throw new LessonNotFoundException();
        }
        if (lessonIndex < 0 || lessonIndex >= lessonMap.get(day).size()) {
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
            return;
        }
        for (int i = 0; i < lessonMap.get(day).size(); i++) {
            if (lessonMap.get(day).get(i).getHiddenId() == id) {
                lessonMap.get(day).remove(i);
                return;
            }
        }
    }

    /**
     * Checks if a lesson is in the timetable.
     *
     * @param lesson Lesson object to find.
     * @return boolean value of true if the lesson is found, false otherwise.
     */
    public boolean isLessonInTimeTable(Lesson lesson) {
        boolean condition = false;
        for (DayOfWeek eachDay: DayOfWeek.values()) {
            if (!lessonMap.containsKey(eachDay)) {
                break;
            } else if (lessonMap.get(eachDay).contains(lesson)) {
                condition = true;
            }
        }
        return condition;
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
    public ArrayList<Lesson> getDayLessonList(DayOfWeek day) {
        return lessonMap.get(day);
    }

    public int getLessonCountOnDay(DayOfWeek day) {
        return lessonMap.get(day).size();
    }

    /**
     * Returns an ArrayList with lessons by filtering all lessons in lessonMap through the given LessonFilter.
     *
     * @param currentFilter
     *  The current LessonFilter in use
     * @return
     *  The filtered ArrayList of lessons generated from lessonMap
     */
    public ArrayList<Lesson> filterLessons(LessonFilter currentFilter) {
        ArrayList<Lesson> outputList = new ArrayList<>();

        for (DayOfWeek eachDay : DayOfWeek.values()) {
            ArrayList<Lesson> currentDay = lessonMap.get(eachDay);
            for (Lesson eachLesson : currentDay) {
                if (currentFilter.filter(eachLesson)) {
                    outputList.add(eachLesson);
                }
            }
        }
        return outputList;
    }

    /**
     * Clears the lesson ArrayList for a given day.
     *
     * @param day
     *  The specified day
     */
    public void clearDayLesson(DayOfWeek day) {
        lessonMap.put(day, new ArrayList<>());
    }
}
