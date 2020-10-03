package seedu.duke.command.add;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.directory.Task;

import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    private Task.typeOfTasks typeOfTask;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");

    public AddCommand(Task.typeOfTasks typeOfTask, String desc, String deadline) {
        this.typeOfTask = typeOfTask;
        switch (typeOfTask) {
        case TASK:
            checkTask(desc, deadline);
            break;
        case MODULE:
            checkModule(desc);
            break;
        }
    }

    private void checkTask(String desc, String deadline) {
        // Check if the deadline follows the DateTime format
    }

    private void checkModule(String desc) {
        // Check if the module exists by accessing the ModuleList
    }

    private Task addTask(String desc, String deadline) {
        Task task = new Task();
        // Add task to the TaskList
    }

    private Module addModule(String desc) {
        // Add module to the ModuleList
    }

    /**
     * Adds the task to their respective lists
     *
     * @return CommandResult containing acknowledgement of the add.
     */
    @Override
    public CommandResult execute() {
        switch (typeOfTask) {
        case TASK:
            String desc = Task.getName();
            String deadline = Task.getDeadline();
            Task task = addTask(desc, deadline);
            break;
        case MODULE:
            String mod = Module.getCode();
            Module module = addModule(mod);
            break;
        }
//        return new CommandResult();
    }
}