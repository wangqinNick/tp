package seedu.duke.ui;

import seedu.duke.data.Module;
import seedu.duke.data.Task;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.util.Message.MESSAGE_HELP;

public class TextUi {
    public static void getTaskListMessage(ArrayList<Task> taskList){
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i+1) + "." + task);
        }
    }

    public static void getModuleListMessage(HashMap<String, Module> modulesMap) {
        int i = 1;
        for (Module module : modulesMap.values()){
            System.out.println(i + "." + module.getCode() + "\n" + module.getTitle());
            i++;
        }
    }

    public static String getHelpMessage(){
        return MESSAGE_HELP;
    }
}
