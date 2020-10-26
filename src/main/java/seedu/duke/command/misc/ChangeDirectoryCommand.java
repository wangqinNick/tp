package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ModuleManager;
import seedu.duke.directory.Directory;
import seedu.duke.directory.DirectoryLevel;
import seedu.duke.directory.DirectoryTraverser;
import seedu.duke.directory.Module;
import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DirectoryTraversalOutOfBoundsException;
import seedu.duke.ui.TextUi;

import java.util.ArrayList;
import static seedu.duke.util.ExceptionMessage.MESSAGE_DIRECTORY_NOT_FOUND;
import static seedu.duke.util.ExceptionMessage.MESSAGE_FAILED_DIRECTORY_TRAVERSAL;

public class ChangeDirectoryCommand extends Command {
    public static final String COMMAND_WORD = "cd";
    public static final String FORMAT = COMMAND_WORD + " <next directory name>";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Traverse up and down the directories; '..' represents parent directory\n"
                    + "Format: %s\n"
                    + "Example: cd Tutorial;\tcd ..\n",
            COMMAND_WORD, FORMAT);

    private String nextDirectoryName;
    private Directory directory;

    /**
     * Constructs the command to traverse down into the next directory with the given name.
     *
     * @param nextDirectoryName
     *  The name of the next directory
     */
    public ChangeDirectoryCommand(String nextDirectoryName) {
        this.nextDirectoryName = nextDirectoryName;
        this.directory = null;
    }

    /**
     * Constructs the command to traverse up from the current directory.
     */
    public ChangeDirectoryCommand() {
        nextDirectoryName = null;
        directory = null;
    }

    /**
     * Constructs the command to traverse to a specified directory.
     *
     * @param directory
     *  The directory to traverse to
     */
    public ChangeDirectoryCommand(Directory directory) {
        nextDirectoryName = null;
        this.directory = directory;
    }

    /**
     * Executes the Change Directory Command to traverse up or down the current Directory, or to a specified Directory.
     *
     * @return
     *  The Command result of the execution
     */
    @Override
    public CommandResult execute() {
        // Traverse to specified directory
        if (directory != null) {
            DirectoryTraverser.traverseTo(directory);
            return new CommandResult(null);
        }

        // Traverse up or down current directory
        try {
            if ((nextDirectoryName != null)) {
                Directory nextDirectory = DirectoryTraverser.findNextDirectory(nextDirectoryName);
                DirectoryTraverser.traverseDown(nextDirectory);
            } else {
                DirectoryTraverser.traverseUp();
                if(DirectoryTraverser.getCurrentDirectoryLevel() == DirectoryLevel.ROOT){
                    ArrayList<Module> filteredModuleList = ModuleManager.getModuleList();
                    var listMessage = TextUi.getAppendedModules(filteredModuleList);
                    return new CommandResult(listMessage);
                }
            }
            // No feedback for successful traversal
            return new CommandResult(null);
        } catch (DirectoryTraversalOutOfBoundsException e) {
            return new CommandResult(MESSAGE_FAILED_DIRECTORY_TRAVERSAL);
        } catch (DataNotFoundException e) {
            return new CommandResult(MESSAGE_DIRECTORY_NOT_FOUND);
        }
    }
}
