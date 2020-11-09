package seedu.ravi.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class StateManager {
    private static Stack<State> undoStack = new Stack<>();
    private static Stack<State> redoStack = new Stack<>();
    private static ArrayList<String> editTypeCommandArrayList = new ArrayList<>();

    /**
     * Initialises the screen shot manager with its first screen shot of the starting list.
     */
    public static void initialise() {
        //for Junit Test only
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        State screenShot = toJson();
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
        editTypeCommandArrayList = currentState.getEditTypeCommandArrayList();
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
     * @return The last edit type command entered by the user.
     * @throws IOException exception is thrown when error occurred during IO operation.
     * @throws EmptyStackException exception is thrown when user trying to undo at the initial state.
     */
    public static String undo() throws IOException, EmptyStackException {
        var previousState = popPreviousScreenShot();

        var encodedSavedList = previousState.getEncodedSavedList();
        var encodedSavedMap = previousState.getEncodedSavedMap();
        var encodedTimeTable = previousState.getEncodedTimeTable();

        var stream1 = new ByteArrayInputStream(encodedSavedList.getBytes());
        var bufferedReader1 = new BufferedReader(new InputStreamReader(stream1));

        var stream2 = new ByteArrayInputStream(encodedSavedMap.getBytes());
        var bufferedReader2 = new BufferedReader(new InputStreamReader(stream2));

        var stream3 = new ByteArrayInputStream(encodedTimeTable.getBytes());
        var bufferedReader3 = new BufferedReader(new InputStreamReader(stream3));

        Task[] readList1 = new Gson().fromJson(bufferedReader1, Task[].class);
        TaskManager.loadTasks(getDecodedTaskList(readList1));

        Type type = new TypeToken<HashMap<String, Module>>(){}.getType();
        HashMap<String, Module> map = new Gson().fromJson(bufferedReader2, type);
        ModuleManager.loadMods(map);

        Type type2 = new TypeToken<HashMap<Integer, LessonManager>>(){}.getType();
        HashMap<Integer, LessonManager> map2 = new Gson().fromJson(bufferedReader3, type2);
        TimeTableManager.getTimeTable().setSemesterMap(map2);

        bufferedReader1.close();
        bufferedReader2.close();

        String lastCommand = editTypeCommandArrayList.remove(editTypeCommandArrayList.size() - 1);
        String[] arrOfLastCommand = lastCommand.split("\\s+");
        String out;
        // Special case to find tasks
        if (arrOfLastCommand[1].equals("-t")) {
            out = String.join(" ", arrOfLastCommand[0], arrOfLastCommand[1]);
            String substring = lastCommand.substring(lastCommand.indexOf(arrOfLastCommand[2]));
            out += " " + substring;
        } else {
            out = String.join(" ", arrOfLastCommand);
        }
        return out;
    }

    /**
     * Saves the moduleList as a string if it was changed.
     *
     * @param editTypeCommand The edit type command entered by the user.
     */
    public static void saveState(String editTypeCommand) {
        editTypeCommandArrayList.add(editTypeCommand);
        State screenShot = toJson();
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

    private static State toJson() {
        var gson = new GsonBuilder().create();
        var encodedSavedList = gson.toJson(TaskManager.getTaskList());
        var encodedSavedMap = gson.toJson(ModuleManager.getModulesMap());
        var encodedSavedTimeTable = gson.toJson(TimeTableManager.getTimeTable().getSemesterMap());
        return new State(encodedSavedList, encodedSavedMap, editTypeCommandArrayList, encodedSavedTimeTable);
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
            tempTaskList.add(new Task(task.getName(), task.retrieveDeadline(), task.getStatus()));
        }
        return tempTaskList;
    }

    /*


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
