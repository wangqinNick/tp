package seedu.duke.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

public class StateManager {
    private static Stack<State> undoStack = new Stack<>();
    private static Stack<State> redoStack = new Stack<>();

    /**
     * Initialises the screen shot manager with its first screen shot of the starting list.
     */
    public static void initialise() {
        var savedTaskList = TaskManager.getTaskList();
        var savedModuleMap = ModuleManager.getModulesMap();
        var screenShot = new State(savedTaskList, savedModuleMap);
        assert undoStack.isEmpty() : "Undo stack should be empty!";
        assert redoStack.isEmpty() : "Redo stack should be empty!";
        undoStack.push(screenShot);
    }

    /**
     * Returns and pops the top state.
     *
     * @return the last state.
     * @throws EmptyStackException stack is empty.
     */
    private static State popPreviousScreenShot() throws EmptyStackException {
        // There should be at least 2 screen shots to allow undo
        if (undoStack.size() < 2) {
            throw new EmptyStackException();
        }
        var currentState = undoStack.pop();
        redoStack.push(currentState);
        return undoStack.peek();
    }

    /**
     * Returns the last state without pop it.
     *
     * @return the last state
     */
    private static State peekPreviousScreenShot() {
        return undoStack.peek();
    }

    /**
     * Reverts to the previous changed state of the list.
     *
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static void undo() throws EmptyStackException {
        var previousScreenShot = popPreviousScreenShot();
        var savedTaskList = previousScreenShot.getTasks();
        var savedModuleMap = previousScreenShot.getModulesMap();
        TaskManager.loadTasks(savedTaskList);
        ModuleManager.loadMods(savedModuleMap);
    }

    /**
     * Saves the moduleList as a string if it was changed.
     */
    public static void saveState() {
        var savedTaskList = TaskManager.getTaskList();
        var savedModuleMap = ModuleManager.getModulesMap();
        var screenShot = new State(savedTaskList, savedModuleMap);
        undoStack.push(screenShot);
//        if (getUndoStackSize() == 0) {
//            undoStack.push(screenShot);
//            return;
//        }
//        var previousScreenShot = peekPreviousScreenShot();
//        if (!previousScreenShot.equals(screenShot)) {
//            undoStack.push(screenShot);
//            if (!redoStack.isEmpty()) {
//                redoStack.clear();
//            }
//        }
    }

    /**
     * Return the number of states store in th stack.
     *
     * @return the stack size.
     */
    private static int getUndoStackSize() {
        return undoStack.size();
    }

    /**
     * Return the task list read from Json file.
     *
     * @param readList the task array read from Json file.
     * @return the task list parsed from readList array.
     */
    public static ArrayList<Task> getDecodedTaskList(Task[] readList) {
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : readList) {
            tempTaskList.add(new Task(task.getName(), task.getDeadline(), task.getStatus()));
        }
        return tempTaskList;
    }

    /**
     * Return the module map read from Json file.
     *
     * @param readList the task array read from Json file.
     * @return the task list parsed from readList array.
     */
    public static HashMap<String, Module> getDecodedModuleMap(Module[] readList) {
        ArrayList<Module> tempModuleList = new ArrayList<>();
        for (Module module : readList) {
            tempModuleList.add(new Module(module.getModuleCode()));
        }
        HashMap<String, Module> modulesMap = new HashMap<>();
        for (Module eachModule : tempModuleList) {
            modulesMap.put(eachModule.getModuleCode(), eachModule);
        }
        return modulesMap;
    }
}
