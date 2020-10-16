package seedu.duke.command;

import static seedu.duke.command.PromptType.NONE;

public abstract class Command {
    protected PromptType promptType = NONE;

    public PromptType getPromptType() {
        return promptType;
    }

    /**
     * Executes the command.
     *
     * @return
     *  The result of the execution
     */
    public abstract CommandResult execute();
}