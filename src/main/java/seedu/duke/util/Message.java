package seedu.duke.util;

public class Message {
    public static final String MESSAGE_ADD_TASK_SUCCESS = "SUCCESS!! The task has been added.\n";
    public static final String MESSAGE_ADD_MODULE_SUCCESS = "SUCCESS!! The module has been added.\n";
    public static final String MESSAGE_ADD_LESSON_SUCCESS = "SUCCESS!! The lesson has been added.\n";
    public static final String MESSAGE_DELETE_TASK_SUCCESS = "SUCCESS!! The task has been deleted.\n";
    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "SUCCESS!! The module has been deleted.\n";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "SUCCESS!! The lesson has been deleted.\n";
    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "SUCCESS!! The module has been updated.\n";
    public static final String MESSAGE_EDIT_TASK_SUCCESS = "SUCCESS!! The task has been updated.\n";
    public static final String MESSAGE_EMPTY_INPUT = "Please enter a command.\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT =
            "Sorry, the command entered contains some unrecognised parameters starting from here:\n";
    public static final String MESSAGE_CHECK_COMMAND_FORMAT =
            "Please check to make sure to follow the command format as such:\n";
    public static final String MESSAGE_NO_EDIT_MODULE = "Please enter a new module code to edit.\n";
    public static final String MESSAGE_NO_EDIT_TASK = "Please enter a new task index to edit.\n";
    public static final String MESSAGE_NO_ADD_MODULE = "Please enter a new module code to add.";
    public static final String MESSAGE_NO_ADD_TASK = "Please enter a new task to add.\n";
    public static final String MESSAGE_GOODBYE = "Good bye!";
    public static final String MESSAGE_WELCOME = "Welcome to RAVI";
    public static final String MESSAGE_DONE_TASK_SUCCESS = "SUCCESS!! The task has been marked as done.\n";
    public static final String ICON_DONE = "\u2713";
    public static final String ICON_NOT_DONE = "\u2718";
    public static final String MESSAGE_LIST_PRINTED = "List:\n";
    public static final String MESSAGE_UNDO_SUCCESS = "Undo successfully!";
    public static final String MESSAGE_UNDO_UNSUCCESSFUL = "Sorry, there was an IO error when undoing the state.";
    public static final String MESSAGE_UNDO_AT_BEGINNING = "You are already at the initial state!";
    public static final String MESSAGE_GRADE_MODULE_SUCCESS = "Module graded successfully! \n";
    public static final String MESSAGE_CAP_DISPLAY = "Your current CAP is \n";
    public static final String MESSAGE_COMPLETED_TASKLIST = "\nCompleted tasks:";
    public static final String MESSAGE_INCOMPLETE_UNDATED_TASKLIST = "\nIncomplete undated tasks:";
    public static final String MESSAGE_INCOMPLETE_DATED_TASKLIST = "\nIncomplete dated tasks:";
    public static final String MESSAGE_SUMMARY_PRINTED = "Your Task Summary\n";
    public static final String MESSAGE_COMMAND_LIST =
            "Command                                        Function\n"
                    + "help ----------------------------------------- View command list\n"
                    + "add -t <task desc.> [-by] -------------------- Add task\n"
                    + "add -m <module> ------------------------------ Add module\n"
                    + "cap <total mc taken> <current cap> ----------- Calculate the accumulated cap after a semester\n"
                    + "edit -t <task index> <new task desc.> -------- Edit a task's description\n"
                    + "edit -m <module code> <new module code> ------ Edit a module\n"
                    + "del -t <task index> -------------------------- Delete a task\n"
                    + "del -m <module index> ------------------------ Delete a module\n"
                    + "grade <module code> <grade> ------------------ Grade an existing module\n"
                    + "list -t -------------------------------------- List all tasks\n"
                    + "list -m -------------------------------------- List all modules\n"
                    + "done <task index> ---------------------------- Mark task as done\n"
                    + "undo ----------------------------------------- Undo previous action\n"
                    + "summary -------------------------------------- View task summary\n"
                    + "timetable -day ------------------------------- View the timetable for today\n"
                    + "timetable -week ------------------------------ View the timetable for the week\n"
                    + "timetable -add <module> <day> <start time> --- Add a module to the timetable\n"
                    + "<end time> <lesson type> <repeat> ------------ Add a lesson to the timetable\n"
                    + "timetable -del <day> <lesson index> ---------- Delete a lesson from the timetable\n"
                    + "bye ------------------------------------------ Exit RaVi\n";

    public static final String MESSAGE_TIMETABLE_INIT = "Please enter the current week num e.g. 1 - 14"
            + " where 7 is recess week." + System.lineSeparator() + "In here, NUS week 7 onwards is week 8 onwards.";
}
