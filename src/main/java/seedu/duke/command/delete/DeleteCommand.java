package seedu.duke.command.delete;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.directory.Task;

public class DeleteCommand extends Command {
    Task.typeOfTasks typeOfTask;
    int toBeDeletedNum;

    public DeleteCommand(Task.typeOfTasks typeOfTask, int toBeDeletedNum) {
        this.typeOfTask = typeOfTask;
        this.toBeDeletedNum = toBeDeletedNum;
    }

    private void deleteTask(int num) {
        // Access Task List and delete
    }

    private void deleteModule(int num) {
        // Access Module List and delete
    }

    /**
     * Deletes the task from their respective lists
     *
     * @return CommandResult containing acknowledgement of the delete.
     */
    @Override
    public CommandResult execute() {
        switch (typeOfTask) {
        case TASK:
            deleteTask(toBeDeletedNum);
            break;
        case MODULE:
            deleteModule(toBeDeletedNum);
            break;
        }
//        return new CommandResult();
    }
}
