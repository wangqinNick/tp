package seedu.duke.util;

import seedu.duke.directory.Directory;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Message {

    public static final String MESSAGE_LOGO = "      ___          ___          ___          ___     \n"
            + "     /__/\\        /__/\\        /__/|        /  /\\    \n"
            + "     \\  \\:\\       \\  \\:\\      |  |:|       /  /:/_   \n"
            + "      \\  \\:\\       \\  \\:\\     |  |:|      /  /:/ /\\  \n"
            + "  _____\\__\\:\\  ___  \\  \\:\\  __|  |:|     /  /:/ /:/_ \n"
            + " /__/::::::::\\/__/\\  \\__\\:\\/__/\\_|:|____/__/:/ /:/ /\\\n"
            + " \\  \\:\\~~\\~~\\/\\  \\:\\ /  /:/\\  \\:\\/:::::/\\  \\:\\/:/ /:/\n"
            + "  \\  \\:\\  ~~~  \\  \\:\\  /:/  \\  \\::/~~~~  \\  \\::/ /:/ \n"
            + "   \\  \\:\\       \\  \\:\\/:/    \\  \\:\\       \\  \\:\\/:/  \n"
            + "    \\  \\:\\       \\  \\::/      \\  \\:\\       \\  \\::/   \n"
            + "     \\__\\/        \\__\\/        \\__\\/        \\__\\/    \n"
            + "\n";

    public static final String MESSAGE_WELCOME_1 = "Welcome to NUKE Task Tracker!";
    public static final String MESSAGE_WELCOME_2 = "What would you like to do today?";
    public static final String DIVIDER = "-".repeat(100);
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon.";

    public static String messageTaskSuccessfullyList(int taskCount) {
        return String.format("There are (is) %d task(s) in the list!\n", taskCount);
    }

    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Sorry, the command entered contains some unrecognised parameters starting from here:\n";
    public static final String MESSAGE_CHECK_COMMAND_FORMAT =
            "Please check to make sure to follow the command format as such:\n";
    public static final String MESSAGE_UNKNOWN_COMMAND_WORD =
            "Sorry, the command word at the start of your input is unrecognised.\n";
    public static final String MESSAGE_EXTRA_PARAMETERS =
            "There seems to be some extra parameters in the command.\n"
            + "Please check the command format again or enter help to find out more.\n";
    public static final String MESSAGE_NO_PREFIX_ALLOWED = "Sorry, no prefix allowed for this command.\n";
    public static final String MESSAGE_EMPTY_INPUT = "Please enter a command.\n";

    public static final String MESSAGE_MISSING_MODULE_CODE = "Please enter a module code.\n";
    public static final String MESSAGE_MISSING_CATEGORY_NAME = "Please enter a category name.\n";
    public static final String MESSAGE_MISSING_TASK_DESCRIPTION = "Please enter a task description.\n";
    public static final String MESSAGE_MISSING_FILE_NAME = "Please enter a file name.\n";
    public static final String MESSAGE_MISSING_FILE_PATH = "Please enter a file path.\n";
    public static final String MESSAGE_MISSING_TAG_NAME = "Please enter a tag name.\n";


    public static final String MESSAGE_SHOW_LIST = "";
    public static final String MESSAGE_NO_MODULES_TO_SHOW = "There are no modules to show.\n";
    public static final String MESSAGE_NO_CATEGORIES_TO_SHOW = "There are no categories to show.\n";
    public static final String MESSAGE_NO_TASKS_TO_SHOW = "There are no tasks to show.\n";
    public static final String MESSAGE_NO_FILES_TO_SHOW = "There are no files to show.\n";
    public static final String MESSAGE_NO_TAGS_TO_SHOW = "There are no tags to show.\n";

    public static final String MESSAGE_DEADLINE_OR_PRIORITY =
            "Please choose either to sort by deadline or priority.\n";

    public static final String MESSAGE_NO_MODULES_FOUND = "Sorry. No modules found.\n";
    public static final String MESSAGE_NO_CATEGORIES_FOUND = "Sorry. No categories found.\n";
    public static final String MESSAGE_NO_TASKS_FOUND = "Sorry. No tasks found.\n";
    public static final String MESSAGE_NO_FILES_FOUND = "Sorry. No files found.\n";
    public static final String MESSAGE_NO_TAGS_FOUND = "Sorry. No tasks with the tag found.\n";

    public static final String MESSAGE_HELP = "Here are valid commands and corresponding format:\n";

    public static final String MESSAGE_TAG_ADDED = "Tag added!\n";

    public static final String MESSAGE_CATEGORY_EXCEED_LIMIT =
            "Sorry, the category name cannot exceed 15 characters.\n";
    public static final String MESSAGE_TASK_EXCEED_LIMIT = "Sorry, the task description cannot exceed 25 characters.\n";
    public static final String MESSAGE_FILE_EXCEED_LIMIT = "Sorry, the file name cannot exceed 30 characters.\n";

    public static String messageAddModuleSuccess(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s is added.\n", moduleCode, title);
    }

    public static String messageAddCategorySuccess(String categoryName) {
        return String.format("SUCCESS!! Category %s is created.\n", categoryName);
    }

    public static String messageAddTaskSuccess(String taskDescription) {
        return String.format("SUCCESS!! Task %s is created.\n", taskDescription);
    }

    public static String messageAddFileSuccess(String fileName) {
        return String.format("SUCCESS!! File %s is added.\n", fileName);
    }

    public static String messageAddTagSuccess(String tag) {
        return String.format("SUCCESS!! Tag %s is added.\n", tag);
    }

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "SUCCESS!! Module(s) have been deleted.\n";
    public static final String MESSAGE_DELETE_CATEGORY_SUCCESS = "SUCCESS!! Category(s) have been deleted.\n";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "SUCCESS!! Task(s) have been deleted.\n";
    public static final String MESSAGE_DELETE_FILE_SUCCESS = "SUCCESS!! Files(s) have been deleted.\n";
    public static final String MESSAEGE_DELETE_TAG_SUCCESS = "SUCCESS!! Tag(s) have been deleted.\n";

    public static final String MESSAGE_DELETE_ABORTED = "The deletion is aborted.\n";

    /**
     * Creates the message to confirm deletion of a module.
     *
     * @param toDelete
     *  The module to be deleted
     * @return
     *  The message to confirm deletion of the module
     */
    public static String messageConfirmDeleteModule(Module toDelete) {
        return String.format("Confirm delete %s %s?\n", toDelete.getModuleCode(), toDelete.getTitle());
    }

    /**
     * Creates the message to confirm deletion of multiple modules.
     *
     * @param filteredModules
     *  The filtered list of modules
     * @param toDeleteIndices
     *  The indices of the modules to be deleted from the list
     * @return
     *  The message to confirm deletion of modules
     */
    public static String messageConfirmDeleteModule(ArrayList<Module> filteredModules,
                                                    ArrayList<Integer> toDeleteIndices) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("Confirm delete these modules?\n");
        for (int index : toDeleteIndices) {
            String toDeleteModuleCode = filteredModules.get(index).getModuleCode();
            String toDeleteTitle = filteredModules.get(index).getTitle();
            promptMessage.append(String.format("%s %s\n", toDeleteModuleCode, toDeleteTitle));
        }
        return promptMessage.toString();
    }

    /**
     * Creates the message to confirm deletion of a task.
     *
     * @param toDelete
     *  The task to be deleted
     * @return
     *  The message to confirm deletion of the task
     */
    public static String messageConfirmDeleteTask(Task toDelete) {
        return String.format("Confirm delete %s?\n", toDelete.getDescription());
    }

    /**
     * Creates the message to confirm deletion of multiple tasks.
     *
     * @param filteredTasks
     *  The filtered list of tasks
     * @param toDeleteIndices
     *  The indices of the tasks to be deleted from the list
     * @return
     *  The message to confirm deletion of tasks
     */
    public static String messageConfirmDeleteTask(ArrayList<Task> filteredTasks, ArrayList<Integer> toDeleteIndices) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("Confirm delete these tasks?\n");
        for (int index : toDeleteIndices) {
            String toDeleteTaskDescription = filteredTasks.get(index).getDescription();
            promptMessage.append(String.format("%s\n", toDeleteTaskDescription));
        }
        return promptMessage.toString();
    }
    

    public static final String MESSAGE_PROMPT_FORMAT = "Enter 'yes' to confirm or 'no' to abort.\n";
    public static final String MESSAGE_INVALID_DELETE_INDICES = "Deletion aborted due to invalid index provided.\n";
    public static final String MESSAGE_INVALID_DONE_INDICES = "Marking aborted due to invalid index provided.\n";
    public static final String MESSAGE_ILLEGAL_DELETE =
            "Attempting to delete the current or its parent's directory.\nPlease move out of the directory first.\n";
    public static final String MESSAGE_MISSING_DIRECTORY_TO_DELETE =
            "Please enter the name of the directory to delete.\n";
    public static final String MESSAGE_NO_DIRECTORY_TO_DELETE = "Sorry, there is nothing else to delete here.\n";

    public static final String MESSAGE_INVALID_TIME_SPECIFIER =
            "Sorry, the time specifier you entered is not recognised.\n"
            + "Please make sure the time specifier entered is either:\n\t"
            + "'before', 'after' or 'on'\n"
            + "Alternatively, you can also enter 'over' without the date information to see all your overdue tasks.\n";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "SUCCESS!! The module has been updated.\n";
    public static final String MESSAGE_EDIT_CATEGORY_SUCCESS = "SUCCESS!! The category has been updated.\n";
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "SUCCESS!! The task has been updated.\n";
    public static final String MESSAGE_EDIT_FILE_SUCCESS = "SUCCESS!! The file has been updated.\n";
    public static final String MESSAGE_NO_EDIT = "There is nothing to edit.\n";
    public static final String MESSAGE_NO_EDIT_MODULE = "Please enter a new module code to edit.\n";
    public static final String MESSAGE_NO_EDIT_CATEGORY =
            "Please enter either a new category name or priority to edit.\n";
    public static final String MESSAGE_NO_EDIT_TASK =
            "Please enter a new task description, priority or deadline to edit.\n";
    public static final String MESSAGE_NO_EDIT_FILE = "Please enter a new file name to edit.\n";
    public static final String MESSAGE_DONE_TASK = "SUCCESS!! The task has been marked as done.\n";
    public static final String MESSAGE_ALREADY_DONE_TASK = "Hm... The task seems to have already been done.\n";

    public static final String MESSAGE_REDO_SUCCESS = "SUCCESS!! Data has been reverted back to the next state.\n";
    public static final String MESSAGE_UNDO_SUCCESS = "SUCCESS!! Data has been reverted to the previous state.\n";

    public static final String MESSAGE_OPEN_FILE_SUCCESS = "The files are opening now...\n";
    public static final String MESSAGE_NO_FILE_CHOSEN = "No file was chosen.\n";
    public static final String MESSAGE_INVALID_FILE_NAME =
            "Sorry, the name of the file you are adding contains words that begins with a symbol.\n"
            + "Please enter a new file name.\n";

    public static final String MESSAGE_INCORRECT_DIRECTORY_LEVEL_GENERIC =
            "Sorry, unable to execute the command at the current directory level.\n";
}
