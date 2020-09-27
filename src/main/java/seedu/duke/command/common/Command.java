package seedu.duke.command.common;

public abstract class Command {
    /**
     * Executes the command.
     *
     * @return
     *  The result of the execution
     */
    public abstract CommandResult execute();
}