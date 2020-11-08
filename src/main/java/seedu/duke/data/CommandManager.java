package seedu.duke.data;

import java.util.ArrayList;

public class CommandManager {
    private static ArrayList<String> commandList = new ArrayList<>();
    private static int pointer;
    public static void initialise() {
        commandList = new ArrayList<>();
        pointer = -1;
    }

    public static void add(String toAdd){
        pointer = commandList.size();
        commandList.add(pointer, toAdd);
    }

    public static String traverseUpHistoryCommand() {
        if (pointer <= 0){
            //
        }
        pointer --;
        return commandList.get(pointer);
    }
    public static String traverseDownHistoryCommand(){
        if (pointer >= commandList.size()-1){
            //
        }
        pointer ++;
        return commandList.get(pointer);
    }


    public static ArrayList<String> getCommandList() {
        return commandList;
    }
}