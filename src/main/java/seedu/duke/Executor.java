package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.parser.Parser;

public class Executor {
    /**
     * Executes command.
     *
     * @param userInput The input from the user to be parsed and executed as a command
     * @return commandResult that contains the execute output information
     */
    public static CommandResult executeCommand(String userInput) {
        Command command = new Parser().parseCommand(userInput);
        return command.execute();
    }
}
