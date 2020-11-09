package seedu.ravi.command.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.Executor;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.StateManager;
import seedu.ravi.data.TaskManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.NusModsNotLoadedException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ravi.util.Message.MESSAGE_UNDO_AT_BEGINNING;

class UndoCommandTest {

    @BeforeEach
    void setupModObjects() throws NusModsNotLoadedException {
        ModuleManager.clearModules();
        TaskManager.clear();
        InputOutputManager.tryLoadNusMods();
        StateManager.initialise();
    }

    @Test
    void undo_addModule_Success() {
        CommandResult result1 = Executor.executeCommand("add -m cs3235");
        assertEquals(1, ModuleManager.getModCodeList().length);
        CommandResult result2 = Executor.executeCommand("add -m cs3230");
        assertEquals(2, ModuleManager.getModCodeList().length);
        new UndoCommand().execute();
        assertEquals(1, ModuleManager.getModCodeList().length);
        CommandResult result3 = Executor.executeCommand("add -m cs1010");
        assertEquals(2, ModuleManager.getModCodeList().length);
    }

    @Test
    void undo_deleteModule_Success() {
        CommandResult result0 = new UndoCommand().execute();
        assertEquals(MESSAGE_UNDO_AT_BEGINNING, result0.feedbackToUser);
        CommandResult result1 = Executor.executeCommand("add -m cs3235");
        assertEquals(1, ModuleManager.getModCodeList().length);
        CommandResult result2 = Executor.executeCommand("add -m cs3230");
        assertEquals(2, ModuleManager.getModCodeList().length);
        new UndoCommand().execute();
        assertEquals(1, ModuleManager.getModCodeList().length);
        CommandResult result3 = Executor.executeCommand("add -m cs1010");
        assertEquals(2, ModuleManager.getModCodeList().length);
        CommandResult result4 = Executor.executeCommand("del -m cs1010");
        assertEquals(1, ModuleManager.getModCodeList().length);
        new UndoCommand().execute();
        assertEquals(2, ModuleManager.getModCodeList().length);
    }
}