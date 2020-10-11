package seedu.duke.ui;

import seedu.duke.data.Module;
import seedu.duke.data.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TextUi {
    private static final String HELP_MESSAGE =
              "Command                   Function\n"
            + "help -------------------- View command list\n"
            + "add -t [-by] ------------ Add task\n"
            + "add -m ------------------ Add module\n"
            + "edit -t ----------------- Edit a task's description\n"
            + "edit -m ----------------- Edit a module\n"
            + "del -t <task index>------ Delete a task\n"
            + "del -m <module index>---- Delete a module\n"
            + "list -t ----------------- List all tasks\n"
            + "list -m ----------------- List all modules\n"
            + "done <task index> ------- Mark task as done\n"
            + "exit -------------------- Exit RaVi\n";

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
        return HELP_MESSAGE;
    }
}
