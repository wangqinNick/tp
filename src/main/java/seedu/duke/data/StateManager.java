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
import java.util.Iterator;
import java.util.Stack;

public class StateManager {
    private static Stack<State> undoStack = new Stack<>();
    private static Stack<State> redoStack = new Stack<>();

    /**
     * Initialises the screen shot manager with its first screen shot of the starting list.
     */
    public static void initialise() {
        var gson = new GsonBuilder().create();

        var encodedSavedList = gson.toJson(TaskManager.getTaskList());
        var savedMapList = parseModuleList();
        var encodedSavedMapList = gson.toJson(savedMapList);

        var screenShot = new State(encodedSavedList, encodedSavedMapList);
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
     * @return the last state.
     */
    private static State peekPreviousScreenShot() {
        return undoStack.peek();
    }

    /**
     * Reverts to the previous changed state of the list.
     *
     * @throws IOException exception is thrown when error occurred during IO operation.
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static void undo() throws IOException, EmptyStackException {
        var previousState = popPreviousScreenShot();

        var encodedSavedList = previousState.getEncodedSavedList();
        var encodedSavedMap = previousState.getEncodedSavedMapList();

        var stream1 = new ByteArrayInputStream(encodedSavedList.getBytes());
        var bufferedReader1 = new BufferedReader(new InputStreamReader(stream1));

        var stream2 = new ByteArrayInputStream(encodedSavedMap.getBytes());
        var bufferedReader2 = new BufferedReader(new InputStreamReader(stream2));

        Task[] readList1 = new Gson().fromJson(bufferedReader1, Task[].class);
        TaskManager.loadTasks(getDecodedTaskList(readList1));
        bufferedReader1.close();

        Module[] readList2 = new Gson().fromJson(bufferedReader2, Module[].class);
        ModuleManager.loadMods(parseModuleMap(getDecodedModuleList(readList2)));
        bufferedReader2.close();
    }

    /**
     * Saves the moduleList as a string if it was changed.
     */
    public static void saveState() {
        var gson = new GsonBuilder().create();
        var encodedSavedList = gson.toJson(TaskManager.getTaskList());
        var encodedSavedMap = gson.toJson(ModuleManager.getModulesMap());
        var screenShot = new State(encodedSavedList, encodedSavedMap);
        undoStack.push(screenShot);
        /*
        if (getUndoStackSize() == 0) {
            undoStack.push(screenShot);
            return;
        }
        var previousScreenShot = peekPreviousScreenShot();
        var previousEncodedSavedList = previousScreenShot.getEncodedSavedList();
        if (!previousEncodedSavedList.equals(encodedSavedList)) {
            undoStack.push(screenShot);
            if (!redoStack.isEmpty()) {
                redoStack.clear();
            }
        }

         */
    }

    /**
     * Return the number of states store in th stack.
     *
     * @return the stack size
     */
    private static int getUndoStackSize() {
        return undoStack.size();
    }

    /**
     * Return the task list read from Json file.
     *
     * @param readList the task array read from Json file
     * @return the task list parsed from readList array
     */
    public static ArrayList<Task> getDecodedTaskList(Task[] readList) {
        ArrayList<Task> tempTaskList = new ArrayList<>();
        for (Task task : readList) {
            tempTaskList.add(new Task(task.getName(), task.getDeadline(), task.getStatus()));
        }
        return tempTaskList;
    }

    /**
     * Return the task list read from Json file.
     *
     * @param readList the module map read from Json file
     * @return the task list parsed from readList array
     */
    public static ArrayList<Module> getDecodedModuleList(Module[] readList) {
        ArrayList<Module> temp = new ArrayList<>();
        for (Module module : readList) {
            temp.add(new Module(module.getModuleCode()));
        }
        return temp;
    }

    /**
     * Parse map to list.
     *
     * @return module list
     */
    public static ArrayList<Module> parseModuleList() {
        ArrayList<Module> moduleArrayList = new ArrayList<>();
        Iterator<String> it = ModuleManager.getModulesMap().keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            moduleArrayList.add(ModuleManager.getModulesMap().get(key));
        }
        return moduleArrayList;
    }

    /**
     * Parse list to map.
     * @param moduleArrayList module list
     * @return module map
     */
    public static HashMap<String, Module> parseModuleMap(ArrayList<Module> moduleArrayList) {
        HashMap<String, Module> map = new HashMap<>();
        for (Module module: moduleArrayList
        ) {
            map.put(module.getModuleCode(), module);
        }
        return map;
    }
}
