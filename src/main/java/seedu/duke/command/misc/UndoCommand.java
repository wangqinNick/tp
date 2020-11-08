package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.ScreenShotManager;
import seedu.duke.exception.CorruptedFileException;

import java.io.IOException;
import java.util.EmptyStackException;

import static seedu.duke.util.Message.MESSAGE_UNDO_SUCCESS;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = String.format(
            "%s - Undo the recent change made to the list\n"
                    + "Format: %s\n",
            COMMAND_WORD, FORMAT);
    public static final String MESSAGE_UNDO_UNSUCCESSFUL = "Sorry, there was an IO error when undoing the state.\n";
    public static final String MESSAGE_UNDO_AT_BEGINNING = "You are already at the initial state!\n";


    @Override
    public CommandResult execute() {
        try {
            ScreenShotManager.undo();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } catch (IOException | CorruptedFileException e) {
            return new CommandResult(MESSAGE_UNDO_UNSUCCESSFUL);
        } catch (EmptyStackException e) {
            return new CommandResult(MESSAGE_UNDO_AT_BEGINNING);
        }
    }
}