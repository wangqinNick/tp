package seedu.duke.command.filtercommand.listcommand;

import seedu.duke.command.CommandResult;
import seedu.duke.command.filtercommand.FilterCommand;
import seedu.duke.data.ModuleManager;
import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;
import seedu.duke.ui.TextUi;
import seedu.duke.util.Message;

import java.util.ArrayList;

import static seedu.duke.ui.TextUi.getAppendedTasks;

public class ListCommand extends FilterCommand {
    public static final String COMMAND_WORD = "ls";
    public static final String FORMAT = COMMAND_WORD ;

    public ListCommand() {
    }

    /**
     * Executes the command
     * Returns the command result
     *
     * @return the command result
     */
    @Override
    public CommandResult execute() {
        switch (DirectoryTraverser.getCurrentDirectoryLevel()){
        case MODULE:
            Module module = (Module) DirectoryTraverser.getCurrentDirectory();
            ArrayList<Task> tasks = module.getTasks().filter("");
            if (tasks.size() > 0) {
                var listMessage = getAppendedTasks(tasks);
                return new CommandResult((listMessage));
            } else {
                return new CommandResult(Message.MESSAGE_NO_TASKS_TO_SHOW);
            }
        case ROOT:
            ArrayList<Module> modules = ModuleManager.getModuleList();
            if (modules.size() > 0) {
                var listMessage = TextUi.getAppendedModules(modules);
                return new CommandResult((listMessage));
            } else {
                return new CommandResult(Message.MESSAGE_NO_TASKS_TO_SHOW);
            }
        case NONE:
        case TASK:
        default:
            return new CommandResult(Message.MESSAGE_INCORRECT_DIRECTORY_LEVEL_GENERIC);
        }
    }
}
