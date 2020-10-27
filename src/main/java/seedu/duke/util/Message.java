package seedu.duke.util;

public class Message {
    public static final String MESSAGE_ADD_TASK_SUCCESS = "Your task has been added successfully.\n";
    public static final String MESSAGE_ADD_MODULE_SUCCESS = "Your module has been added successfully.\n";
    public static final String MESSAGE_ADD_LESSON_SUCCESS = "Your lesson has been added successfully.\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Your task has been deleted successfully.\n";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "Your module has been deleted successfully.\n";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Your lesson has been deleted successfully.\n";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Your task has been edited successfully.\n";
    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Your module has been edited successfully.\n";

    public static final String MESSAGE_EMPTY_INPUT = "Please enter a command for me.\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Sorry, the command entered contains some unrecognised parameters starting from here:\n";
    public static final String MESSAGE_CHECK_COMMAND_FORMAT =
            "Please check to make sure to follow the command format:\n";
    public static final String MESSAGE_NO_EDIT_MODULE = "Please enter a new module code to edit.\n";
    public static final String MESSAGE_NO_EDIT_TASK = "Please enter a new task index to edit.\n";
    public static final String MESSAGE_NO_ADD_MODULE = "Please enter a new module code to add.";
    public static final String MESSAGE_NO_ADD_TASK = "Please enter a new task to add.\n";
    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_WELCOME = "Welcome to RAVI";
    public static final String MESSAGE_DONE_TASK_SUCCESS = "SUCCESS!! The task has been marked as done.\n";
    public static final String ICON_DONE = "\u221A";
    public static final String ICON_NOT_DONE = "\u0078";
    public static final String MESSAGE_LIST_PRINTED = "List:\n";
    public static final String MESSAGE_UNDO_SUCCESS = "Undo successfully!";
    public static final String MESSAGE_UNDO_AT_BEGINNING = "There is nothing to undo!";
    public static final String MESSAGE_GRADE_MODULE_SUCCESS = "Module graded successfully! \n";
    public static final String MESSAGE_CAP_DISPLAY = "Your current CAP is \n";
    public static final String MESSAGE_COMPLETED_TASKLIST = "\n▻ Completed tasks:";
    public static final String MESSAGE_INCOMPLETE_UNDATED_TASKLIST = "\n▻ Incomplete undated tasks:";
    public static final String MESSAGE_INCOMPLETE_DATED_TASKLIST = "\n▻ Incomplete dated tasks:";
    public static final String MESSAGE_SUMMARY_PRINTED = "Your latest task summary \n";
    public static final String MESSAGE_GENERAL_HELP =
            "Hello! I'm ra.VI, your personal NUS assistant.\n"
            + "I'm here to help you manage your tasks, modules, and lessons.\n"
            + "This help message appears when you type 'help', or an unrecognised command.\n"
            + "To find out more about any of my commands, type 'help <command>'.\n"
            + "Here's a list of my commands to help you out:\n\n"
            + "  Action commands:\n"
            + "\t▻ add       → Add a task or module\n"
            + "\t▻ del       → Delete a task or module\n"
            + "\t▻ edit      → Edit a task or module\n"
            + "\t▻ done      → Mark a task as complete\n"
            + "\t▻ grade     → Grades and allocates MCs to a Module\n"
            + "\t▻ undo      → Undo the previous action (if you made changes)\n"
            + "\t▻ timetable → Manage your timetable\n"
            + "  Viewing commands:\n"
            + "\t▻ list      → Lists all tasks or modules\n"
            + "\t▻ summary   → See a neat summary of your tasks\n"
            + "\t▻ timetable → View your timetable, by day or by week\n"
            + "  Utility commands:\n"
            + "\t▻ help      → Get detailed help for each command\n"
            + "\t▻ bye       → Exit ra.VI (saves all changes!)";
    public static final String MESSAGE_TIMETABLE_INIT =
            "Please enter the current week num e.g. 1 - 14"
                    + " where 7 is recess week.\n" + "In here, NUS week 7 onwards is week 8 onwards.";
    public static final String MESSAGE_TIMETABLE_HEADER =
            "  _________________________________\n"
                    + " |   Time    | ID |     Lesson     |\n"
                    + " |___________|____|________________|\n";
    public static final String MESSAGE_TIMETABLE_FOOTER = "\n |___________|____|________________|\n";
    public static final String MESSAGE_NO_LESSONS = "\nNo lessons on ";
    /*
    public static final String MESSAGE_COMMAND_LIST =
            "Command                                       | Function\n"
                    + "┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈┈\n"
                    + "help ----------------------------------------- View command list\n"
                    + "help [<command_word>] ------------------------ View command information\n"
                    + "add -t <task_name> [-by <deadline>] ---------- Add task to the scheduler\n"
                    + "add -m <module_code> ------------------------- Add a module from NUSMods to the scheduler\n"
                    + "cap <total_mc> <current_cap> ----------------- Calculate your CAP\n"
                    + "del -t <task_index> -------------------------- Delete a task from the scheduler\n"
                    + "del -m <module_code> ------------------------- Delete a module from the scheduler\n"
                    + "done <task_index> ---------------------------- Mark a task as done\n"
                    + "edit -t <task_index> <task_name> ------------- Edit a task's description from the task list\n"
                    + "edit -m <module_code> <new_module_code> ------ Edit a module code from the module list\n"
                    + "grade <module_code> <grade> ------------------ Grades and allocates the Module Credit to the Module\n"
                    + "list -t -------------------------------------- List all tasks in the task list\n"
                    + "list -m -------------------------------------- List all modules in the module list\n"
                    + "undo ----------------------------------------- Undo previous action\n"
                    + "summary -------------------------------------- View task summary\n"
                    + "timetable -day ------------------------------- View the timetable for today\n"
                    + "timetable -week ------------------------------ View the timetable for the week\n"
                    + "timetable -add <module_code> <day> <start_time>\n"
                    + "<end time> <lesson type> <repeat> ------------ Add a lesson to the timetable\n"
                    + "timetable -del <day> <lesson_index> ---------- Delete a lesson from the timetable\n"
                    + "bye ------------------------------------------ Exit RaVi\n";
    */
}
