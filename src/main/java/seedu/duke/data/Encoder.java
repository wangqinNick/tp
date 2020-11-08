package seedu.duke.data;

import seedu.duke.directory.Module;
import seedu.duke.directory.Task;

import java.util.ArrayList;

public class Encoder {
    private static final String BEGIN_MODULE_INDICATOR = "--- MODULE ---\n";
    private static final String END_MODULE_INDICATOR = "--- END MODULE ---\n";
    private static final String BEGIN_CATEGORY_INDICATOR = "--- CATEGORY ---\n";
    private static final String END_CATEGORY_INDICATOR = "--- END CATEGORY ---\n";
    private static final String BEGIN_TASK_INDICATOR = "--- TASK ---\n";
    private static final String END_TASK_INDICATOR = "--- END TASK ---\n";
    private static final String BEGIN_FILE_INDICATOR = "--- FILE ---\n";
    private static final String END_FILE_INDICATOR = "--- END FILE ---\n";
    private static final String BEGIN_TAG_INDICATOR = "--- TAG ---\n";
    private static final String END_TAG_INDICATOR = "--- END TAG ---\n";
    private static final String DELIMITER = " -|||- ";
    private static final String LINE_BREAK = System.lineSeparator();

    private ArrayList<Module> moduleList;

    public Encoder(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * Encodes the Directory List to be saved into a file.
     *
     * @return
     *  The encoded Directory List
     */
    public String encode() {
        return encodeModuleList();
    }

    private String encodeModuleList() {
        StringBuilder encodedList = new StringBuilder(BEGIN_MODULE_INDICATOR);
        for (Module module : moduleList) {
            encodedList.append(getModuleInformation(module)).append(LINE_BREAK);
        }
        encodedList.append(END_MODULE_INDICATOR);
        return encodedList.toString();
    }

    private String encodeTaskList(ArrayList<Task> tasks) {
        StringBuilder encodedList = new StringBuilder(BEGIN_TASK_INDICATOR);
        for (Task task : tasks) {
            encodedList.append(getTaskInformation(task)).append(LINE_BREAK);
        }
        encodedList.append(END_TASK_INDICATOR);
        return encodedList.toString();
    }


    private String getModuleInformation(Module module) {
        return String.join(DELIMITER, module.getModuleCode(), module.getTitle(), module.getDescription());
    }

    private String getTaskInformation(Task task) {
        String doneStatus = task.isDone() ? "Y" : "N";
        String deadline = task.getDeadline().isPresent() ? task.getDeadline().toString() : "";
        return String.join(DELIMITER, task.getDescription(), doneStatus, deadline);
    }
}
