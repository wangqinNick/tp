package seedu.duke.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {
    static Task normalTask;
    static Task datedTask;

    @BeforeEach
    void setupTaskObjects() {
        normalTask = new Task("ABC");
        LocalDateTime myDate = LocalDateTime.of(2020, 10, 1, 0, 0);
        datedTask = new Task("DEF", myDate);
        TaskManager.clear();
        TaskManager.add(normalTask);
        TaskManager.add(datedTask);
    }

    @Test
    void getTaskCount_isEquals2() {
        assertEquals(2, TaskManager.getTaskCount());
    }

    @Test
    void check_taskNotFoundException_isThrown() {
        assertThrows(TaskManager.TaskNotFoundException.class, () -> TaskManager.getTask(2));
    }

    @Test
    void editTask_getName_equalsNewName() throws TaskManager.TaskNotFoundException {
        String newName1 = "NEW";
        String newName2 = "NAME";
        normalTask.setName(newName1);
        datedTask.setName(newName2);
        TaskManager.edit(normalTask, 0);
        TaskManager.edit(datedTask, 1);

        assertEquals(newName1, TaskManager.getTask(0).getName());
        assertEquals(newName2, TaskManager.getTask(1).getName());
    }

    @Test
    void deleteTask_geTaskCount_isEquals0() throws TaskManager.TaskNotFoundException {
        TaskManager.delete(1);
        TaskManager.delete(0);

        assertEquals(0, TaskManager.getTaskCount());
        assertThrows(TaskManager.TaskNotFoundException.class, () -> TaskManager.getTask(0));
    }

    @Test
    void summary_checkTaskOrder_isCorrect() {
        LocalDateTime myDate = LocalDateTime.of(2020, 10, 1, 1, 0);
        Task laterDatedTask = new Task("LATER", myDate);
        TaskManager.add(laterDatedTask);

        ArrayList<Task> sortedTaskWithDeadlines = TaskManager.summary().get(0);
        Task earlierTask = sortedTaskWithDeadlines.get(0);
        Task laterTask = sortedTaskWithDeadlines.get(1);
        // Assert that laterTask actually has a later deadline than earlierTask
        assertTrue(laterTask.getDeadline().compareTo(earlierTask.getDeadline()) > 0);
        assertEquals(2, sortedTaskWithDeadlines.size());
    }

    @Test
    void summary_checkNoDeadlineList_isCorrect() {
        ArrayList<Task> sortedTasksWithoutDeadlines = TaskManager.summary().get(1);
        Task taskWithoutDeadline = sortedTasksWithoutDeadlines.get(0);
        assertTrue(taskWithoutDeadline.getDeadline() == null);
        assertEquals(1, sortedTasksWithoutDeadlines.size());
    }

    @Test
    void summary_checkCompletedList_isCorrect() throws TaskManager.TaskNotFoundException {
        TaskManager.done(0);
        TaskManager.done(1);
        ArrayList<Task> completedTasks = TaskManager.summary().get(2);
        assertTrue(completedTasks.get(0).getStatus());
        assertEquals(2, completedTasks.size());
    }
}