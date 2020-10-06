package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.io.FileNotFoundException;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public Duke() throws FileNotFoundException {
        //todo set root

        //todo load moduleManager with modulesMap
    }

    /**
     * ScreenShot entry-point for the java.duke.Duke application.
     *
     * @param args arguments passed to the programme.
     * @throws FileNotFoundException exception is thrown if the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }

    /**
     * run method for Duke class.
     */
    public void run() {

    }

    private CommandResult getResponse(String userInput){
        return Executor.executeCommand(userInput);
    }
}
