package seedu.duke.util;

public class Message {
    public static final String MESSAGE_ADD_TASK_SUCCESS = "SUCCESS!! The task has been added.\n";
    public static final String MESSAGE_ADD_MODULE_SUCCESS = "SUCCESS!! The module has been added.\n";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "SUCCESS!! The task has been deleted.\n";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "SUCCESS!! The module has been deleted.\n";
    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "SUCCESS!! The module has been updated.\n";
    public static final String MESSAGE_EMPTY_INPUT = "Please enter a command.\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Sorry, the command entered contains some unrecognised parameters starting from here:\n";
    public static final String MESSAGE_CHECK_COMMAND_FORMAT =
            "Please check to make sure to follow the command format as such:\n";
    public static final String MESSAGE_NO_EDIT_MODULE = "Please enter a new module code to edit.\n";
    public static final String MESSAGE_DONE_TASK_SUCCESS = "SUCCESS!! The task has been marked as done.\n";
    public static final String ICON_DONE = "\u2713";
    public static final String ICON_NOT_DONE = "\u2718";
    public static final String MESSAGE_LIST_PRINTED = "LIST:\n";
    public static final String MESSAGE_HELP =
            "Command                   Function\n"
            + "help -------------------- View command list\n"
            + "add -t [-by] ------------ Add task\n"
            + "add -m ------------------ Add module\n"
            + "edit -t ----------------- Edit a task's description\n"
            + "edit -m ----------------- Edit a module\n"
            + "del -t <task index>------ Delete a task\n"
            + "del -m <module index>---- Delete a module\n"
            + "list -t ----------------- List all tasks\n"
            + "list -m ----------------- List all modules\n"
            + "done <task index> ------- Mark task as done\n"
            + "exit -------------------- Exit RaVi\n";
}
