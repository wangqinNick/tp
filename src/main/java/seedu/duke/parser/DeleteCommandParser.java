package seedu.duke.parser;

import seedu.duke.command.delete.DeleteCommand;

import java.security.InvalidParameterException;

public class DeleteCommandParser {

    protected static DeleteCommand getDeleteCommand(String commandFlag, String parameters) throws NumberFormatException {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return new DeleteCommand(Parser.TypeOfEntries.MODULE, parameters); //parameter is module code
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new DeleteCommand(Parser.TypeOfEntries.TASK, Integer.parseInt(parameters));//parameters is the index
        } else {
            throw new InvalidParameterException();
        }
    }
}
