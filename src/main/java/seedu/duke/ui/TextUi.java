package seedu.duke.ui;

import seedu.duke.data.Task;

import java.util.ArrayList;

public class TextUi {
    private static StringBuilder taskMessage;

    private static void getTaskListMessage(ArrayList<Task> taskList){
        taskMessage = new StringBuilder();
        for (int i = 0; i <= taskList.size(); i++){
            Task task = taskList.get(i);
        }
    }

    private static void appendAllTaskMessage(int index)
}
