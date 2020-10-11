package seedu.duke.ui;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TextUi {
    private static Scanner in = new Scanner(System.in);

    public static void getTaskListMessage(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + "." + task);
        }
    }

    public static void getModuleListMessage(HashMap<String, Module> modulesMap) {
        int i = 1;
        for (Module module : modulesMap.values()) {
            System.out.println(i + "." + module.getCode() + "\n" + module.getTitle());
            i++;
        }
    }

    public void showResultToUser(CommandResult result) {
    }

    public void greetUser() {
    }
}
