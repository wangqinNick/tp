package seedu.duke.command;

public class CommandResult {
    public final String feedbackToUser;
    public final boolean isError;
    public final boolean isExit;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        this.isError = false;
        this.isExit = false;
    }

    public CommandResult(String feedbackToUser, boolean isError) {
        this.feedbackToUser = feedbackToUser;
        this.isError = isError;
        this.isExit = false;
    }

    public CommandResult(String feedbackToUser, boolean isError, boolean isExit) {
        this.feedbackToUser = feedbackToUser;
        this.isError = isError;
        this.isExit = isExit;
    }
}
