package seedu.duke.command.filtercommand.listcommand;

import seedu.duke.command.CommandResult;
import seedu.duke.data.TaskManager;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.util.Message;

import java.util.ArrayList;

import static seedu.duke.ui.TextUi.getAppendedTasks;

public class ListTaskCommand extends ListCommand {
    public static final String COMMAND_WORD = "lst";
    public static final String FORMAT = COMMAND_WORD ;
    public static final String MESSAGE_USAGE = String.format(
            "%s -show task(s)\n"
                    + "Format: %s\n"
                    + "Example: lst\n",
            COMMAND_WORD, FORMAT);

    public ListTaskCommand() {

    }

    /**
     * Executes the command
     * Returns the command result
     *
     * @return the command result
     */
    @Override
    public CommandResult execute() {
        ArrayList<Task> tasks;
        switch (DirectoryTraverser.getCurrentDirectoryLevel()){
        case MODULE:
            Module module = (Module) DirectoryTraverser.getCurrentDirectory();
            tasks = module.getTasks().filter("");
            if (tasks.size() > 0){
                var listMessage = getAppendedTasks(tasks);
                return new CommandResult((listMessage));
            } else {
                return new CommandResult(Message.MESSAGE_NO_TASKS_TO_SHOW);
            }
        case ROOT:
        case NONE:
        case TASK:
        default:
            return new CommandResult(Message.MESSAGE_INCORRECT_DIRECTORY_LEVEL_GENERIC);
        }
    }
}
