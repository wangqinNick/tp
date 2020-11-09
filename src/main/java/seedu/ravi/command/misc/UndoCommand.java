package seedu.ravi.command.misc;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.StateManager;

import java.io.IOException;
import java.util.EmptyStackException;

import static seedu.ravi.util.Message.MESSAGE_UNDO_AT_BEGINNING;
import static seedu.ravi.util.Message.MESSAGE_UNDO_SUCCESS;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String HELP =   "Undo last action."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + COMMAND_WORD;

    public UndoCommand() {
        this.promptType = PromptType.EDIT;
    }

    @Override
    public CommandResult execute() {
        try {
            String lastCommand = StateManager.undo();
            return new CommandResult(String.format(MESSAGE_UNDO_SUCCESS, lastCommand));
        } catch (EmptyStackException | IOException e) {
            return new CommandResult(MESSAGE_UNDO_AT_BEGINNING, true);
        }
    }
}
