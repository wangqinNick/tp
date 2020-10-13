package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.ExitCommand;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private TextUi ui;

    /**
     * Entry-point for the java.duke.Duke application.
     *
     * @param args arguments passed to the program.
     * @throws FileNotFoundException exception is thrown if the file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run(args);
    }

    /** Sets up the storage, loads up the data from the storage file and prints the welcome message.  */
    private void start(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        this.ui = new TextUi(in);
        // todo add code to the following functions in InputOutputManager, TextUi
        InputOutputManager.start();
        ui.showWelcomeMessage();
    }

    /** Runs the program until termination.  */
    public void run(String[] args) throws FileNotFoundException {
        start(args);
        runCommandLoopUntilExitCommand();
        ui.showGoodByeMessage();
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userInput = ui.getUserCommand();
            command = new Parser().parseCommand(userInput);
            CommandResult result = command.execute();
            // todo add code to the following function in TextUi
            ui.showResultToUser(result);
        // todo add Exit to the Parser
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult getResponse(String userInput) {
        return Executor.executeCommand(userInput);
    }
}
