package seedu.ravi.command.edit;

import org.junit.jupiter.api.Test;
import seedu.ravi.Executor;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.DuplicateModuleException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;
import seedu.ravi.exception.NusModsNotLoadedException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditModuleCommandTest {
    @Test
    void execute() throws
            NusModsNotLoadedException, DuplicateModuleException, ModuleNotProvidedException, ModuleNotFoundException {
        InputOutputManager.tryLoadNusMods();
        ModuleManager.add("cs1231");
        CommandResult result1 = Executor.executeCommand("edit -m cs1231 CS1010");
        ModuleManager.getModule("CS1010"); // should not throw exception
    }
}