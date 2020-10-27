package seedu.duke.command.help;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    static final String HELP_MESSAGE =
            "Command                                        Function\n"
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

    @Test
    void testHelpCommand() {
        HelpCommand helpCommand = new HelpCommand("");
        CommandResult commandResult = helpCommand.execute();
        assertEquals(HELP_MESSAGE, commandResult.feedbackToUser);
    }
}
