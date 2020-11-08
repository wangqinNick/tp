package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.StorageManager;
import seedu.duke.data.StoragePath;

import java.io.IOException;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = String.format(
            "%s - Exit from Nuke application\n"
                    + "Format: %s\n",
            COMMAND_WORD, FORMAT);


    public ExitCommand() {

    }

    /**
     * Executes the <b>Exit Command</b> to exit the <b>Nuke</b> program.
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        exitProgram();
        return null;
    }

    /**
     * Exits the runtime.
     */
    private static void exitProgram() {
        try {
            new StorageManager(StoragePath.SAVE_PATH).cleanUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
