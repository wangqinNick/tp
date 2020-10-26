package seedu.duke.ui;

import seedu.duke.directory.Module;
import seedu.duke.directory.Task;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.util.Constant.NEWLINE;

public class TextUi {
    public static final int LIST_INDEX_OFFSET = 1;
    public static final int INDEX_OFF_SET = -1;
    public static final String MESSAGE_LIST_RESPOND_FORMAT = "%s";
    public static final String MESSAGE_MODULE_LIST = "%d. %s %s";
    public static final String MESSAGE_TASK_LIST = "%d. %s";
    private static StringBuilder modulesMessages;
    private static StringBuilder tasksMessages;
    /**
     * Return the message of all modules in a specific task list
     * @param modules the specific task list
     * @return the appended task message
     */
    public static String getAppendedModules(ArrayList<Module> modules){
        getModuleListMessage(modules);
        return modulesMessages.toString();
    }

    /**
     * get taskList message
     */
    private static void getModuleListMessage(ArrayList<Module> taskListToPrint) {
        modulesMessages = new StringBuilder();
        for (int index = LIST_INDEX_OFFSET; index <= taskListToPrint.size() ; index++) {
            Module module = taskListToPrint.get(index+ INDEX_OFF_SET);
            appendAllModuleMessage(index, module);
        }
    }

    /**
     * append all tasks message
     * @param index index of the task
     * @param module the task to append message
     */
    private static void appendAllModuleMessage(int index, Module module) {
        modulesMessages.append(
                String.format(
                        MESSAGE_LIST_RESPOND_FORMAT,
                        String.format(
                                MESSAGE_MODULE_LIST,
                                index,
                                module.getModuleCode(),
                                module.getTitle())
                )
        ).append(NEWLINE);
    }

    /**
     * Return the message of all modules in a specific task list
     * @param tasks the specific task list
     * @return the appended task message
     */
    public static String getAppendedTasks(ArrayList<Task> tasks){
        getTaskListMessage(tasks);
        return tasksMessages.toString();
    }

    /**
     * get taskList message
     */
    private static void getTaskListMessage(ArrayList<Task> taskListToPrint) {
        tasksMessages = new StringBuilder();
        for (int index = LIST_INDEX_OFFSET; index <= taskListToPrint.size() ; index++) {
            Task task = taskListToPrint.get(index+ INDEX_OFF_SET);
            appendAllModuleMessage(index, task);
        }
    }

    /**
     * append all tasks message
     * @param index index of the task
     * @param task the task to append message
     */
    private static void appendAllModuleMessage(int index, Task task) {
        tasksMessages.append(
                String.format(
                        MESSAGE_LIST_RESPOND_FORMAT,
                        String.format(
                                MESSAGE_TASK_LIST,
                                index,
                                task.getDescription())
                )
        ).append(NEWLINE);
    }
}
