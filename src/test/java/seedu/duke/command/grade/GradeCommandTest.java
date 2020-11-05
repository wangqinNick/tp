package seedu.duke.command.grade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.command.CommandResult;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.add.AddModuleCommand;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.InvalidModuleCreditException;
import seedu.duke.exception.NusModsNotLoadedException;
import seedu.duke.util.ExceptionMessage;
import seedu.duke.util.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GradeCommandTest {
    static final String MODULE_CODE = "CG2271";
    static final String NON_EXISTENT_MODULE_CODE = "CS2101";

    @BeforeAll
    static void setup() throws NusModsNotLoadedException {
        InputOutputManager.tryLoadNusMods();
    }

    @BeforeEach
    void setupModObjects() {
        ModuleManager.clearModules();
        AddCommand addModule = new AddModuleCommand(MODULE_CODE);
        addModule.execute();
    }

    @Test
    void gradeCommand_MessageGradeSuccessful_isShown() throws InvalidModuleCreditException {
        GradeCommand gradeCommand = new GradeCommand(MODULE_CODE, 4, "A+");
        CommandResult commandResult = gradeCommand.execute();
        assertEquals(Message.MESSAGE_GRADE_MODULE_SUCCESS, commandResult.feedbackToUser);
    }

    @Test
    void gradeCommand_MessageModuleNotFound_isShown() throws InvalidModuleCreditException {
        GradeCommand gradeCommand = new GradeCommand(NON_EXISTENT_MODULE_CODE, 4, "A+");
        CommandResult commandResult = gradeCommand.execute();
        CommandResult expectedResult = new IncorrectCommand(ExceptionMessage.MESSAGE_MODULE_NOT_FOUND).execute();
        assertEquals(expectedResult.feedbackToUser, commandResult.feedbackToUser);
    }

    @Test
    void gradeCommand_MessageInvalidGrade_isShown() throws InvalidModuleCreditException {
        GradeCommand gradeCommand = new GradeCommand(MODULE_CODE, 4, "SU");
        CommandResult commandResult = gradeCommand.execute();
        CommandResult expectedResult = new IncorrectCommand(ExceptionMessage.MESSAGE_INVALID_GRADE).execute();
        assertEquals(expectedResult.feedbackToUser, commandResult.feedbackToUser);
    }
}
