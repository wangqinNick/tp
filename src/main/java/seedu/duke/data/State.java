package seedu.duke.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class State {
    private ArrayList<Task> tasks;
    private HashMap<String, Module> modulesMap;

    public State(ArrayList<Task> tasks, HashMap<String, Module> modulesMap) {
        this.tasks = tasks;
        this.modulesMap = modulesMap;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public HashMap<String, Module> getModulesMap() {
        return modulesMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;
        State state = (State) o;
        return getTasks().equals(state.getTasks()) &&
                getModulesMap().equals(state.getModulesMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTasks(), getModulesMap());
    }
}
