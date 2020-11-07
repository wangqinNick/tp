package seedu.ravi;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.StateManager;
import seedu.ravi.parser.Parser;

public class Executor {
    /**
     * Executes command.
     *
     * @param userInput The input from the user to be parsed and executed as a command
     * @return commandResult that contains the execute output information
     */
    public static CommandResult executeCommand(String userInput) {
        Command command = new Parser().parseCommand(userInput);
        CommandResult result = command.execute();

        if (command.getPromptType() == PromptType.EDIT) {
            StateManager.saveState(userInput);
        }

        return result;
    }
}
