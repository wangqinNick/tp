package seedu.duke.data;

import seedu.duke.directory.Task;
import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.util.DateTime;

import java.util.ArrayList;

/**
 * every module manager has a task manager to manage tasks within module.
 */
public class TaskManager {
    private ArrayList<Task> taskList;

    /**
     * Initiates an empty Task List.
     */
    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the entire Task List.
     *
     * @return
     *  The Task List
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Sets the entire Task List to a new list.
     *
     * @param taskList
     *  The new Task List to be set
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Finds a task with the specified task description in the Task List.
     *
     * @param description
     *  The description of the task to be found
     * @return
     *  The found task with the specified description
     * @throws TaskNotFoundException
     *  If the task is not found in the Task List
     */
    public Task getTask(String description) throws TaskNotFoundException {
        for (Task task : taskList) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        throw new TaskNotFoundException();
    }

    /**
     * Finds a task with the specified task id in the Task List.
     *
     * @param id
     *  The description of the task to be found
     * @return
     *  The found task with the specified description
     * @throws IndexOutOfBoundsException
     *  If the task is not found in the Task List
     */
    public Task getTask(int id) throws IndexOutOfBoundsException {
        return taskList.get(id);
    }

    /**
     * Checks for duplicates of the same task description in the Task List.
     *
     * @param taskDescription
     *  The task description to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    private boolean contains(String taskDescription) {
        for (Task task : taskList) {
            if (task.isSameTask(taskDescription)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a task to the Task List.
     *
     * @param toAdd
     *  The task to be added
     * @throws DuplicateTaskException
     *  If there exists a duplicate task in the Task List with the same task description
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        if (contains(toAdd.getDescription())) {
            throw new DuplicateTaskException();
        } else {
            taskList.add(toAdd);
        }
    }


    /**
     * Deletes the specified task from the Task List.
     *
     * @param toDelete
     *  The task to be deleted
     */
    public void delete(Task toDelete) {
        taskList.remove(toDelete);
    }

    /**
     * Deletes a <b>Task</b> with the specified <code>description</code> from the <b>Task List</b>.
     *
     * @param description The description of the <b>Task</b> to be deleted
     * @return  The deleted task
     * @throws TaskNotFoundException  If the task with the specified description is not found in the <b>Task List</b>
     * @see Task
     */
    public Task delete(String description) throws TaskNotFoundException {
        Task toDelete = getTask(description);
        taskList.remove(toDelete);
        return toDelete;
    }

    /**
     * Edits a task in the Task List.
     *
     * @param toEdit
     *  The task to be edited
     * @param newTaskDescription
     *  The new task description of the task
     * todo @param newPriority
     * The new priority of the task
     * @throws DuplicateTaskException
     *  If there are duplicate tasks with the same task description as the new task description in the Task List
     */
    public void edit(Task toEdit, String newTaskDescription)
            throws DuplicateTaskException {
        if (!toEdit.isSameTask(newTaskDescription) && contains(newTaskDescription)) {
            throw new DuplicateTaskException();
        }
        toEdit.setDescription(newTaskDescription);
    }

    /**
     * Counts the total number of tasks in the Task List.
     *
     * @return
     *  The total number of tasks in the Task List
     */
    public int countTotalTasks() {
        return taskList.size();
    }

    /**
     * Filter for tasks in the Task List with description that contains the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param taskKeyword
     *  The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public ArrayList<Task> filter(String taskKeyword) {
        String noKeyword = "";
        if (taskKeyword.equals(noKeyword)) {
            return this.getTaskList();
        }
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().contains(taskKeyword.toLowerCase())) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * Filter for tasks in the Task List with description that matches <b>exactly</b> the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param taskKeyword
     *  The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public ArrayList<Task> filterExact(String taskKeyword) {
        // Returns all tasks in the Task List if no keyword is provided.
        String noKeyword = "";
        if (taskKeyword.equals(noKeyword)) {
            return this.getTaskList();
        }

        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().toLowerCase().equals(taskKeyword.toLowerCase())) {
                filteredTaskList.add(task);
            }
        }
        return filteredTaskList;
    }

    /**
     * compare this task with other task.
     * @param other target task
     * @return true if tasks are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && this.taskList.equals(((TaskManager) other).taskList));
    }

    public static class TaskNotFoundException extends DataNotFoundException {
    }

    public static class DuplicateTaskException extends DuplicateDataException {
    }
}
