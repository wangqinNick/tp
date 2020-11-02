package seedu.duke.exception;

import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class InvalidMatchException extends Exception {

    public InvalidMatchException(String parameters, String format, String helpPrompt) {
        super(String.format("%s%s\n\n%s%s\n\n%s\n",
                MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, format,
                helpPrompt));
    }
}
