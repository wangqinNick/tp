package seedu.duke.data;

import seedu.duke.exception.DataNotFoundException;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Comparator;

public class TaskManager {
    private static ArrayList<Task> tasksList = new ArrayList<>(); // Main task list.

    /**
     *  Finds a task with the specified task index (id) in the Task List.
     *
     * @param taskId
     *  The index of the task to be found
     * @return
     *  The found task with the specified task index
     * @throws TaskNotFoundException
     *  If the task is not found in the Task List
     */
    public static Task getTask(int taskId) throws TaskNotFoundException {
        if (taskId < 0 || taskId > tasksList.size() - 1) {
            throw new TaskNotFoundException();
        }
        return tasksList.get(taskId);
    }

    /**
     * Edits a task in the Task List.
     *
     * @param editedTask
     *  The edited task
     * @param taskId
     *  The index of the task in the Task List.
     * @throws TaskNotFoundException
     *  If
     */
    public static void edit(Task editedTask, int taskId) throws TaskNotFoundException {
        if (taskId < 0 || taskId > tasksList.size() - 1) {
            throw new TaskNotFoundException();
        }
        tasksList.set(taskId, editedTask);
    }

    /**
     * Adds a task to the Task List.
     * @param newTask
     *  The task object to add to the task list
     */
    public static void add(Task newTask) {
        tasksList.add(newTask);
    }

    /**
     * Removes a task from the Task List using the task index (id).
     * @param taskId
     *  The index of the task to be deleted
     * @throws TaskNotFoundException
     *  When the task to be removed is not in the task list
     */
    public static void delete(int taskId) throws TaskNotFoundException {
        if (taskId < 0 || taskId > tasksList.size() - 1) {
            throw new TaskNotFoundException();
        }
        tasksList.remove(taskId);
    }

    public static void done(int taskId) throws TaskNotFoundException {
        Task task;
        if (taskId < 0 || taskId > tasksList.size() - 1) {
            throw new TaskNotFoundException();
        }
        task = getTask(taskId);
        task.setStatus(true);
    }

    /**
     * Generate an ordered ArrayList of ArrayLists.
     * First ArrayList contains a list of uncompleted tasks with deadlines, sorted by deadlines.
     * Second ArrayList contains a list of uncompleted tasks without deadlines.
     * Last ArrayList contains a list of completed tasks.
     *
     * @return
     *  The ArrayList of ArrayLists mentioned above.
     */
    public static ArrayList<ArrayList<Task>> summary() {
        ArrayList<ArrayList<Task>> summaryLists = new ArrayList<>();
        ArrayList<Task> incompleteTasksDated = new ArrayList<>();
        ArrayList<Task> incompleteTasksUndated = new ArrayList<>();
        ArrayList<Task> completedTasks = new ArrayList<>();

        for (Task eachTask : tasksList) {
            if (eachTask.getStatus()) {
                completedTasks.add(eachTask);
            } else if (eachTask.retrieveDeadline() == null) {
                incompleteTasksUndated.add(eachTask);
            } else {
                incompleteTasksDated.add(eachTask);
            }
        }

        Comparator<Task> compareByDeadline = Comparator.comparing(Task::retrieveDeadline);
        incompleteTasksDated.sort(compareByDeadline);

        // Do not change the adding order!
        summaryLists.add(incompleteTasksDated);
        summaryLists.add(incompleteTasksUndated);
        summaryLists.add(completedTasks);
        return summaryLists;
    }

    /**
     * Gets Task summary.
     *
     * @return
     *  The message containing the task summary
     */
    public static String getSummary() {
        ArrayList<ArrayList<Task>> summaryLists = summary();
        String message = TextUi.getSummaryList(summaryLists);
        return message;
    }

    /**
     * Gets Task List.
     *
     * @return tasksList
     */
    public static ArrayList<Task> getTaskList() {
        return tasksList;
    }

    /**
     * Prints all tasks in task list.
     *
     * @return
     *  The formatted task list from TextUi or null if list is empty
     */
    public static String list() {
        if (getTaskList().size() > 0) {
            return TextUi.getIndexTaskList(tasksList);
        } else {
            return null;
        }
    }

    /**
     * Loads the file loaded task list into TaskManager's own task list.
     *
     * @param loadedTasksList the loaded task list from file
     */
    public static void loadTasks(ArrayList<Task> loadedTasksList) {
        tasksList = loadedTasksList;
    }

    /**
     * Returns the number of tasks.
     *
     * @return the number of tasks
     */
    public static int getTaskCount() {
        return tasksList.size();
    }

    /**
     * Clears the current task list.
     */
    public static void clear() {
        tasksList = new ArrayList<>();
    }

    public static class TaskNotFoundException extends DataNotFoundException {
    }
}