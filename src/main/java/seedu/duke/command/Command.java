package seedu.duke.command;

import seedu.duke.data.TaskManager;
import seedu.duke.directory.Task;

import static seedu.duke.command.PromptType.NONE;
import static seedu.duke.util.Constant.INDEX_OFF_SET;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @return
     *  The result of the execution
     */
    public abstract CommandResult execute();

    protected PromptType promptType = NONE;
    private int targetIndex = -1;

    public Command() {
    }

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * get target index
     * @return targetIndex
     */
    public int getTargetIndex() {
        return targetIndex;
    }

    public PromptType getPromptType() {
        return promptType;
    }

    public void setPromptType(PromptType promptType) {
        this.promptType = promptType;
    }
}

