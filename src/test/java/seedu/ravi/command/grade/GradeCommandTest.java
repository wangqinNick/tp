//@@author tobiasceg

package seedu.ravi.command.grade;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.command.add.AddCommand;
import seedu.ravi.command.add.AddModuleCommand;
import seedu.ravi.data.Module;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.InvalidModuleCreditException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.util.ExceptionMessage;
import seedu.ravi.util.Message;

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
    void gradeCommand_MessageGradeSuccessful_isShown() throws InvalidModuleCreditException, ModuleNotFoundException {
        GradeCommand gradeCommand = new GradeCommand(MODULE_CODE, 4, "A+");
        Module moduleGraded = ModuleManager.getModule(MODULE_CODE);
        CommandResult commandResult = gradeCommand.execute();
        assertEquals(String.format(
                Message.MESSAGE_GRADE_MODULE_SUCCESS, moduleGraded), commandResult.feedbackToUser);
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
