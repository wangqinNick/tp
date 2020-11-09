package seedu.ravi.command.misc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.Executor;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.data.StateManager;
import seedu.ravi.data.TaskManager;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.exception.TimeTableInitialiseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndoCommandTest {

    @BeforeEach
    void setupModObjects() throws NusModsNotLoadedException, TimeTableInitialiseException {
        ModuleManager.clearModules();
        TaskManager.clear();
        TimeTableManager.clearTimeTable();
        TimeTableManager.initialise(1);
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
        CommandResult result1 = Executor.executeCommand("add -m cs3235");
        assertEquals(1, ModuleManager.getModCodeList().length);
        CommandResult result2 = Executor.executeCommand("add -m cs3230");
        assertEquals(2, ModuleManager.getModCodeList().length);
        new UndoCommand().execute();
        assertEquals(1, ModuleManager.getModCodeList().length);
        CommandResult result3 = Executor.executeCommand("add -m cs1010");
        assertEquals(2, ModuleManager.getModCodeList().length);
        //CommandResult result4 = Executor.executeCommand("del -m cs1010");
        //assertEquals(1, ModuleManager.getModCodeList().length);
        //new UndoCommand().execute();
        //assertEquals(2, ModuleManager.getModCodeList().length);
    }

    @Test
    void undo_addLesson_Success() {
        CommandResult result1 = Executor.executeCommand("add -m cs3235");
        CommandResult result2 = Executor.executeCommand("timetable -add cs3235 MONDAY 1200 1400 LECTURE 0");
        assertEquals(1, TimeTableManager.getTimetableLessonCount());
        new UndoCommand().execute();
        assertEquals(0, TimeTableManager.getTimetableLessonCount());
    }

    @Test
    void undo_delLesson_Success() {
        CommandResult result1 = Executor.executeCommand("add -m cs3235");
        CommandResult result2 = Executor.executeCommand("timetable -add cs3235 MONDAY 1200 1400 LECTURE 0");
        assertEquals(1, TimeTableManager.getTimetableLessonCount());
        CommandResult result3 = Executor.executeCommand("timetable -del MONDAY 1");
        assertEquals(0, TimeTableManager.getTimetableLessonCount());
        new UndoCommand().execute();
        assertEquals(1, TimeTableManager.getTimetableLessonCount());
    }
}