package seedu.duke.command.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.add.AddTaskCommand;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.TaskManager;
import seedu.duke.parser.Parser;
import seedu.duke.util.ExceptionMessage;
import seedu.duke.util.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    @BeforeEach
    void setupLists(){
        TaskManager.clear();
        ModuleManager.clearModules();
    }

    @Test
    void listCommand_MessageListEmpty_isShown() {
        ListCommand taskListCommand = new ListCommand(Parser.TypeOfEntries.TASK);
        CommandResult commandResult1 = taskListCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LIST_EMPTY, commandResult1.feedbackToUser);

        ListCommand moduleListCommand = new ListCommand(Parser.TypeOfEntries.MODULE);
        CommandResult commandResult2 = moduleListCommand.execute();
        assertEquals(ExceptionMessage.MESSAGE_LIST_EMPTY, commandResult2.feedbackToUser);
    }

    @Test
    void listCommand_MessageListPrinted_isShown() {
        AddCommand addTask = new AddTaskCommand("read a book");
        addTask.execute();

        ListCommand taskListCommand = new ListCommand(Parser.TypeOfEntries.TASK);
        CommandResult commandResult1 = taskListCommand.execute();
        String expectedOutput1 = Message.MESSAGE_LIST_PRINTED
                + TaskManager.list();
        assertEquals(expectedOutput1, commandResult1.feedbackToUser);
    }
}
