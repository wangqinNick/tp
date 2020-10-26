package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.util.Message;

import java.io.IOException;

public class Executor {

    /**
     * Returns the command result after the command is executed
     *
     * @param parsedCommand The input from the user to be executed as a command
     * @return commandResult that contains the execute output information
     */
    public static CommandResult executeCommand(Command parsedCommand) {
        var commandResult = parsedCommand.execute();
        if (parsedCommand.getPromptType() == PromptType.EDIT){
            //StateManager.saveState();
        }
        //IOManager.saveAsJson();
        return commandResult;
    }

}
