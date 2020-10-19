package seedu.duke.command.add;

import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.util.ExceptionMessage;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    static final String MOD_CODE_1 = "CS2113T";
    static final String TASK = "test task";
    static final String BAD_DEADLINE = "bad_deadline";
    static final String EMPTY_DEADLINE = "";
    static final String SPACES_DEADLINE = "        ";

    @Test
    void addModule_duplicateModuleMessage_isShown() {
        AddCommand addMod = new AddModuleCommand(MOD_CODE_1);
        AddCommand addDupMod = new AddModuleCommand(MOD_CODE_1);
        addMod.execute();
        CommandResult commandResult = addDupMod.execute();
        assertEquals(ExceptionMessage.MESSAGE_DUPLICATE_MODULE, commandResult.feedbackToUser);
    }

    @Test
    void addTask_badDeadline_DateTimeParseException_isThrown() throws DateTimeParseException {
        assertThrows(DateTimeParseException.class,
            () -> new AddTaskCommand(TASK, BAD_DEADLINE));
    }

    @Test
    void addTask_emptyDeadline_DateTimeParseException_isThrown() throws DateTimeParseException {
        assertThrows(DateTimeParseException.class,
            () -> new AddTaskCommand(TASK, EMPTY_DEADLINE));
    }

    @Test
    void addTask_blankDeadline_DateTimeParseException_isThrown() throws DateTimeParseException {
        assertThrows(DateTimeParseException.class,
            () -> new AddTaskCommand(TASK, SPACES_DEADLINE));
    }
}
