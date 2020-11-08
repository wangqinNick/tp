package seedu.duke.data;

import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.exception.CorruptedFileException;
import seedu.duke.util.DateTime;
import seedu.duke.util.DateTimeFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Decoder {
    private static final String BEGIN_MODULE_INDICATOR = "--- MODULE ---";
    private static final String END_MODULE_INDICATOR = "--- END MODULE ---";
    private static final String BEGIN_CATEGORY_INDICATOR = "--- CATEGORY ---";
    private static final String END_CATEGORY_INDICATOR = "--- END CATEGORY ---";
    private static final String BEGIN_TASK_INDICATOR = "--- TASK ---";
    private static final String END_TASK_INDICATOR = "--- END TASK ---";
    private static final String BEGIN_FILE_INDICATOR = "--- FILE ---";
    private static final String END_FILE_INDICATOR = "--- END FILE ---";
    private static final String BEGIN_TAG_INDICATOR = "--- TAG ---";
    private static final String END_TAG_INDICATOR = "--- END TAG ---";
    private static final String DELIMITER = " -|||- ";
    private static final String LINE_BREAK = System.lineSeparator();

    private BufferedReader reader;

    public Decoder(BufferedReader reader) {
        this.reader = reader;
    }

    public ArrayList<Module> decode() throws CorruptedFileException, IOException {
        return decodeModuleList();
    }

    private ArrayList<Module> decodeModuleList()
            throws CorruptedFileException, IOException {
        ArrayList<Module> decodedModuleList = new ArrayList<>();
        if (!reader.readLine().equals(BEGIN_MODULE_INDICATOR)) {
            throw new CorruptedFileException();
        }

        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.equals(END_MODULE_INDICATOR)) {
                break;
            }
            Module decodedModule = decodeModule(currentLine.split(Pattern.quote(DELIMITER)));

            decodedModuleList.add(decodedModule);
        }
        return decodedModuleList;
    }

    private ArrayList<Task> decodeTaskList(Module decodedModule)
            throws CorruptedFileException, IOException {
        ArrayList<Task> decodedTaskList = new ArrayList<>();
        if (!reader.readLine().equals(BEGIN_TASK_INDICATOR)) {
            throw new CorruptedFileException();
        }
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.equals(END_TASK_INDICATOR)) {
                break;
            }
            try {
                Task decodedTask = decodeTask(decodedModule, currentLine.split(Pattern.quote(DELIMITER)));
                decodedTaskList.add(decodedTask);
            } catch (NumberFormatException | DateTimeFormat.InvalidDateTimeException e) {
                throw new CorruptedFileException();
            }
        }
        return decodedTaskList;
    }

    private Module decodeModule(String[] moduleInformation)
            throws CorruptedFileException, IOException {
        String moduleCode = moduleInformation[0];
        String title = moduleInformation[1];
        String description = moduleInformation[2];
        Module decodedModule = new Module(moduleCode, title, description);
        decodedModule.getTasks().setTaskList(decodeTaskList(decodedModule));
        return decodedModule;
    }

    private Task decodeTask(Module decodedModule, String[] taskInformation)
            throws CorruptedFileException, DateTimeFormat.InvalidDateTimeException, IOException {
        String taskDescription = taskInformation[0];
        boolean doneStatus;
        if (taskInformation[1].equals("Y")) {
            doneStatus = true;
        } else if (taskInformation[1].equals("N")) {
            doneStatus = false;
        } else {
            throw new CorruptedFileException();
        }
        DateTime deadline = (taskInformation[2].isEmpty()) ? new DateTime() :
                DateTimeFormat.stringToDateTime(taskInformation[2]);
        Task decodedTask = new Task(decodedModule, taskDescription, deadline);
        decodedTask.setDone(doneStatus);

        return decodedTask;
    }
}
