package seedu.duke.command.help;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelpCommandTest {
    static final String HELP_MESSAGE =
            "Command                                       Function\n"
            + "help ---------------------------------------- View command list\n"
            + "add -t <task desc.> [-by] ------------------- Add task\n"
            + "add -m <module> ----------------------------- Add module\n"
            + "edit -t <task index> <new task desc.> ------- Edit a task's description\n"
            + "edit -m <module code> <new module code> ----- Edit a module\n"
            + "del -t <task index> ------------------------- Delete a task\n"
            + "del -m <module index> ----------------------- Delete a module\n"
            + "list -t ------------------------------------- List all tasks\n"
            + "list -m ------------------------------------- List all modules\n"
            + "done <task index> --------------------------- Mark task as done\n"
            + "bye ----------------------------------------- Exit RaVi\n";

    @Test
    void testHelpCommand(){
        HelpCommand helpCommand = new HelpCommand();
        CommandResult commandResult = helpCommand.execute();
        assertEquals(HELP_MESSAGE, commandResult.feedbackToUser);
    }
}
