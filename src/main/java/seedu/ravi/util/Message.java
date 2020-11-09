package seedu.ravi.util;

public class Message {
    public static final String MESSAGE_GOODBYE = "@|bold,magenta,BG_BLACK Goodbye, hope to see you soon!|@";
    public static final String MESSAGE_WELCOME = "@|bold,magenta,BG_BLACK Welcome to ra.VI v2.0|@";
    public static final String MESSAGE_SHUTDOWN = "@|bold,magenta,BG_BLACK Shutting down ra.VI...|@";

    public static final String MESSAGE_LOADING_TEMPLATE = "Loading %s: %s";

    public static final String MESSAGE_ADD_TASK_SUCCESS =
            "Your task has been @|bold,green added successfully|@.\n"
            + "Your new task:\n'%s'\n";
    public static final String MESSAGE_ADD_MODULE_SUCCESS =
            "Your module has been @|bold,green added successfully|@.\n"
            + "Your new module:\n'%s'\n";
    public static final String MESSAGE_ADD_LESSON_SUCCESS =
            "Your lesson has been @|bold,green added successfully|@.\n"
            + "Your new lesson:\n'%s'\n"
            + "Added to: %s\n";

    public static final String MESSAGE_DONE_TASK_SUCCESS =
            "The task has been @|bold,green successfully|@ marked as complete.\n"
            + "Your completed task - '%s'\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS =
            "Your task has been @|bold,green deleted successfully|@.\n"
            + "Your deleted task - '%s'\n";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS =
            "Your module has been @|bold,green deleted successfully|@.\n"
            + "Your deleted module - '%s'\n";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS =
            "Your lesson has been @|bold,green deleted successfully|@ from "
            + "all weeks.\n"
            + "Your deleted lesson - '%s'\n";

    public static final String MESSAGE_EDIT_TASK_SUCCESS =
            "Your task has been @|bold,green edited successfully|@.\n"
            + "Your task before editing - '%s'\n"
            + "Your task after editing  - '%s'\n";
    public static final String MESSAGE_EDIT_MODULE_SUCCESS =
            "Your module has been @|bold,green edited successfully|@.\n"
            + "Your module before editing - '%s'\n"
            + "Your module after editing  - '%s'\n";

    public static final String MESSAGE_EMPTY_INPUT = "Please enter a command for me.\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "The command entered contains some @|bold,blue,BG_BLACK unrecognised parameters|@ starting from here:\n";
    public static final String MESSAGE_CHECK_COMMAND_FORMAT =
            "@|bold,red,BG_BLACK,underline Please check to make sure to follow the command format:|@\n";
    public static final String ICON_DONE = "\u221A";
    public static final String ICON_NOT_DONE = "\u0078";
    public static final String MESSAGE_LIST_PRINTED = "Here's your list:\n";
    public static final String MESSAGE_UNDO_SUCCESS = "Undo is @|bold,green successful|@.\n"
            + "Command undone - '%s'\n";
    public static final String MESSAGE_UNDO_AT_BEGINNING = "There is nothing to undo.";
    public static final String MESSAGE_GRADE_MODULE_SUCCESS =
            "Your module has been @|bold,green graded successfully|@.\n"
            + "The module - '%s'. \n";
    public static final String MESSAGE_CAP_DISPLAY = "Your current CAP is \n";

    public static final String MESSAGE_SUMMARY_PRINTED = "Here's a summary of your latest tasks...\n";
    public static final String MESSAGE_COMPLETED_TASKLIST =
            "\n@|bold,green,BG_BLACK,underline Completed tasks:|@";
    public static final String MESSAGE_INCOMPLETE_UNDATED_TASKLIST =
            "\n@|bold,blue,BG_BLACK,underline Incomplete tasks with no deadline:|@";
    public static final String MESSAGE_INCOMPLETE_DATED_TASKLIST =
            "\n@|bold,red,BG_BLACK,underline Incomplete tasks with deadlines:|@";

    public static final String MESSAGE_GENERAL_HELP =
            "Hello! I'm @|bold,magenta,BG_BLACK ra.VI|@, your personal NUS assistant.\n"
            + "I'm here to help you manage your tasks, modules, and lessons.\n"
            + "This help message appears when you type @|bold,green,BG_BLACK 'help'|@, or an unrecognised command.\n"
            + "To find out more about any of my commands, type @|bold,green,BG_BLACK 'help <command>'|@.\n"
            + "Here's a list of my commands to help you out:\n\n"
            + "@|bold,red,BG_BLACK,underline Action commands:|@\n"
            + "\t» add       - Add a task or module\n"
            + "\t» del       - Delete a task or module\n"
            + "\t» edit      - Edit a task or module\n"
            + "\t» done      - Mark a task as complete\n"
            + "\t» grade     - Grades and allocates MCs to a Module\n"
            + "\t» cap       - Calculates your CAP\n"
            + "\t» undo      - Undo the previous action (if you made changes)\n"
            + "\t» timetable - Manage your timetable\n"
            + "@|bold,blue,BG_BLACK,underline Viewing commands:|@\n"
            + "\t» list      - Lists all tasks or modules\n"
            + "\t» summary   - See a neat summary of your tasks\n"
            + "\t» timetable - View your timetable, by day or by week\n"
            + "@|bold,yellow,BG_BLACK,underline Utility commands:|@\n"
            + "\t» help      - Get detailed help for each command\n"
            + "\t» bye       - Exit ra.VI (saves all changes!)";
    public static final String MESSAGE_TIMETABLE_INIT =
            "Please enter the @|bold,blue,BG_BLACK current week number|@ e.g. 1 - 14"
                    + " where 7 is recess week,\n" + "and NUS week 7 onwards is week 8 onwards. \n\n"
                    + "@|bold,red,BG_BLACK Before|@ recess week : please input the current NUS week number.\n"
                    + "@|bold,red,BG_BLACK During|@ recess week : please input 7.\n"
                    + "@|bold,red,BG_BLACK After|@ recess week  : please input the current NUS week number + 1.\n\n"
                    + "E.g. if it's week 7, enter '8'.\n";
    public static final String MESSAGE_NO_LESSONS = "\nNo lessons on ";
    public static final String MESSAGE_TIMETABLE_RESET = "Timetable reset successful.";
}
