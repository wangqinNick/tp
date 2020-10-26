package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.StateManager;

import java.io.IOException;
import java.util.EmptyStackException;

import static seedu.duke.util.Message.MESSAGE_UNDO_AT_BEGINNING;
import static seedu.duke.util.Message.MESSAGE_UNDO_SUCCESS;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String HELP =   "Undo last action."
                                        + "\n\tFormat: " + COMMAND_WORD;

    public UndoCommand() {
        this.promptType = PromptType.INFORMATIVE;
    }

    @Override
    public CommandResult execute() {
        try {
            StateManager.undo();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } catch (EmptyStackException | IOException e) {
            return new CommandResult(MESSAGE_UNDO_AT_BEGINNING);
        }
    }
}
