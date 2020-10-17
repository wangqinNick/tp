package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.StateManager;

import java.io.IOException;
import java.util.EmptyStackException;

import static seedu.duke.util.Message.MESSAGE_UNDO_AT_BEGINNING;
import static seedu.duke.util.Message.MESSAGE_UNDO_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_UNDO_UNSUCCESSFUL;

public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public UndoCommand() {
        this.promptType = PromptType.INFORMATIVE;
    }

    @Override
    public CommandResult execute() {
        try {
            StateManager.undo();
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } catch (EmptyStackException e) {
            return new CommandResult(MESSAGE_UNDO_AT_BEGINNING);
        }
    }
}
