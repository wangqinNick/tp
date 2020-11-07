package seedu.ravi.command.cap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.add.AddCommand;
import seedu.ravi.command.add.AddModuleCommand;
import seedu.ravi.command.grade.GradeCommand;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.exception.InvalidCapException;
import seedu.ravi.exception.InvalidModuleCreditException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ravi.util.Message.MESSAGE_CAP_DISPLAY;

public class CapCommandTest {
    static final String MODULE_CODE = "CS1231";
    static final double CAP = 4.37;

    @BeforeEach
    void setupModManager() throws InvalidModuleCreditException {
        ModuleManager.clearModules();
        AddCommand addModule = new AddModuleCommand(MODULE_CODE);
        addModule.execute();
        GradeCommand gradeCommand = new GradeCommand(MODULE_CODE, 4, "A+");
        gradeCommand.execute();
    }

    @Test
    void capCommand_MessageCapDisplayWithCap_isShown() throws InvalidCapException, InvalidModuleCreditException {
        CapCommand capCommand = new CapCommand(20, 4.24);
        CommandResult commandResult = capCommand.execute();
        assertEquals(String.format("%s%.2f\n", MESSAGE_CAP_DISPLAY, CAP), commandResult.feedbackToUser);
    }
}
