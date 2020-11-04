package seedu.duke.command.edit;

import org.junit.jupiter.api.Test;
import seedu.duke.Executor;
import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.storage.Decoder;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.DuplicateModuleException;
import seedu.duke.exception.ModuleNotFoundException;
import seedu.duke.exception.ModuleNotProvidedException;
import seedu.duke.exception.NusModsNotLoadedException;

import java.io.IOException;
import java.util.HashMap;

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