package seedu.duke.data;

import seedu.duke.exception.LessonNotFoundException;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class LessonManager {
    private static HashMap<DayOfWeek, ArrayList<Lesson>> lessonMap = new HashMap<>();

    /**
     * Inserts a Lesson object in the correct position in the correct ArrayList in lessonMap, based on its day and time.
     *
     * @param newLesson
     *  The new Lesson object
     */
    public void addLesson(Lesson newLesson) {
        DayOfWeek lessonDay = newLesson.getDay();

        // if there are no lessons on that day yet...
        if (!lessonMap.containsKey(lessonDay)) {
            ArrayList<Lesson> newDayLessons = new ArrayList<>();
            newDayLessons.add(newLesson);
            lessonMap.put(lessonDay, new ArrayList<>());
            return;
        }

        int indexToInsertNewLesson = 0;
        for (int i = 0; i < lessonMap.get(lessonDay).size(); i++) {
            Lesson eachLesson = lessonMap.get(lessonDay).get(i);
            if (newLesson.checkOverlap(eachLesson)) {
                return;
            }
            // insert before the lesson which starts right after newLesson
            // each day ArrayList in lessonMap is hence always sorted due to addLesson's logic
            if (eachLesson.getStartTime().isAfter(newLesson.getEndTime())) {
                indexToInsertNewLesson = i;
            }
        }
        lessonMap.get(lessonDay).add(indexToInsertNewLesson, newLesson);
    }

    /**
     * Removes the lesson at lessonIndex on the given day.
     *
     * @param day
     *  The specified day
     * @param lessonIndex
     *  The index of the lesson to be removed
     */
    public void removeLesson(DayOfWeek day, int lessonIndex) throws LessonNotFoundException{
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
    public ArrayList<Lesson> getDayLessonList(DayOfWeek day) throws LessonNotFoundException{
        if (!lessonMap.containsKey(day)) {
            throw new LessonNotFoundException();
        }
        return lessonMap.get(day);
    }

}
