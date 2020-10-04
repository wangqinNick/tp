package seedu.duke.command;

/**
 * <h3>Invalid Command</h3>
 * A <b>Command</b> that is determined to be invalid.
 * @see Command
 */
public class IncorrectCommand extends Command {
    private final String feedbackToUser; // Message to be shown to the user

    public IncorrectCommand(String message) {
        this.feedbackToUser = message;
    }

    /**
     * Executes the <b>Invalid Command</b> to show the <code>message</code> to the user due to an <i>invalid
     * command</i>.
     *
     * @return The <b>Command Result</b> of the execution
     * @see CommandResult
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }
}
