package seedu.duke.data;

import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Root;
import seedu.duke.exception.CorruptedFileException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * A screen shot manager that stores the previous states of the list and performs operations to move about the states;
 * i.e. undo and redo.
 */
public class ScreenShotManager {
    private static Stack<ScreenShot> undoStack = new Stack<>();
    private static Stack<ScreenShot> redoStack = new Stack<>();

    /**
     * Initialises the screen shot manager with its first screen shot of the starting list.
     */
    public static void initialise() {
        String encodedSavedList = new Encoder(ModuleManager.getModuleList()).encode();
        ScreenShot screenShot = new ScreenShot(encodedSavedList);
        assert undoStack.isEmpty() : "Undo stack should be empty!";
        assert redoStack.isEmpty() : "Redo stack should be empty!";
        undoStack.push(screenShot);
    }

    private static ScreenShot popPreviousScreenShot() throws EmptyStackException {
        // There should be at least 2 screen shots to allow undo
        if (undoStack.size() < 2) {
            throw new EmptyStackException();
        }
        ScreenShot currentState = undoStack.pop();
        redoStack.push(currentState);
        return undoStack.peek();
    }

    private static ScreenShot peekPreviousScreenShot() {
        return undoStack.peek();
    }

    /**
     * Revert to the previous changed state of the list.
     *
     * @throws java.io.IOException exception is thrown when error occurred during IO operation.
     * @throws CorruptedFileException exception is thrown when converting a corrupted string to moduleList.
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static void undo() throws IOException, CorruptedFileException, EmptyStackException {
        ScreenShot previousState = popPreviousScreenShot();
        String encodedSavedList = previousState.getEncodedSavedList();

        InputStream stream = new ByteArrayInputStream(encodedSavedList.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        ArrayList<Module> moduleList = new Decoder(bufferedReader).decode();
        ModuleManager.setModuleList(moduleList);
        bufferedReader.close();

        // Move back to Root for now to avoid "unusual" situations
        DirectoryTraverser.traverseTo(new Root());
    }

    /**
     * Revert back to the next changed state which had been undone by the user.
     *
     * @throws IOException exception is thrown when error occurred during IO operation.
     * @throws CorruptedFileException exception is thrown when converting a corrupted string to moduleList.
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static void redo() throws IOException, CorruptedFileException, EmptyStackException {
        ScreenShot redoScreenShot = popRedoScreenShot();
        String encodedSavedList = redoScreenShot.getEncodedSavedList();

        InputStream stream = new ByteArrayInputStream(encodedSavedList.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        ArrayList<Module> moduleList = new Decoder(bufferedReader).decode();
        ModuleManager.setModuleList(moduleList);
        bufferedReader.close();

        // Move back to Root for now to avoid "unusual" situations
        DirectoryTraverser.traverseTo(new Root());
    }

    private static ScreenShot popRedoScreenShot() throws EmptyStackException {
        ScreenShot nextState = redoStack.pop();
        undoStack.push(nextState);
        return nextState;
    }

    /**
     * Save the moduleList as a string if it was changed.
     */
    public static void saveScreenShot() {
        String encodedSavedList = new Encoder(ModuleManager.getModuleList()).encode();
        ScreenShot screenShot = new ScreenShot(encodedSavedList);
        if (getUndoStackSize() == 0) {
            undoStack.push(screenShot);
            return;
        }
        ScreenShot previousScreenShot = peekPreviousScreenShot();
        String previousEncodedSavedList = previousScreenShot.getEncodedSavedList();
        if (!previousEncodedSavedList.equals(encodedSavedList)) {
            undoStack.push(screenShot);
            if (!redoStack.isEmpty()) {
                redoStack.clear();
            }
            assert redoStack.isEmpty() : "Didn't clear the redo stack!!";
        }
    }

    private static int getUndoStackSize() {
        return undoStack.size();
    }
}
