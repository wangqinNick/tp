package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.list.ListCommand;

import java.security.InvalidParameterException;

public class ListCommandParser {

    protected static Command getListCommand(String commandFlag) {
        if (commandFlag.equals(Parser.MODULE_PREFIX)) {
            return new ListCommand(Parser.TypeOfEntries.MODULE);
        } else if (commandFlag.equals(Parser.TASK_PREFIX)) {
            return new ListCommand(Parser.TypeOfEntries.TASK);
        } else {
            throw new InvalidParameterException();
        }
    }
}
