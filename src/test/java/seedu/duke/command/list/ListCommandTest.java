package seedu.duke.command.list;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import seedu.duke.util.ExceptionMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    void listCommand_MessageListEmpty_isShown() {
        TaskManager.clear();
        ListCommand taskListCommand = new ListCommand(Parser.TypeOfEntries.TASK);
        CommandResult commandResult1 = taskListCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LIST_EMPTY, commandResult1.feedbackToUser);

        ModuleManager.clearModules();
        ListCommand moduleListCommand = new ListCommand(Parser.TypeOfEntries.MODULE);
        CommandResult commandResult2 = moduleListCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LIST_EMPTY, commandResult2.feedbackToUser);
    }

}
