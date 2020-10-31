package seedu.duke.command.edit;

import org.junit.jupiter.api.Test;
import seedu.duke.Executor;
import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.storage.Decoder;
import seedu.duke.exception.DuplicateModuleException;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditModuleCommandTest {
    @Test
    void execute() throws DuplicateModuleException {
        HashMap<String, Module> modulesMap = Decoder.loadModules("data/" + "moduleList.json");
        //ModuleManager.add(new Module("CS1231", "AAA"));

        //base case
        CommandResult result1 = Executor.executeCommand("edit -m cs1231 CS3224");
        //assertEquals(MESSAGE_EDIT_TASK_SUCCESS, result1.feedbackToUser);
    }
}