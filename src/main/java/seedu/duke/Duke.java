package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.PromptType;
import seedu.duke.data.StateManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.TimeTableInitialiseException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static seedu.duke.util.ExceptionMessage.TIMETABLE_NOT_INITIALISED;

public class Duke {
    private TextUi ui;
    private static final DukeLogger logger = new DukeLogger(Duke.class.getName());

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
        InputOutputManager.start();
        StateManager.initialise();
        while (!TimeTableManager.isInitialised()) {
            try {
                ui.showTimeTableInitialisationMessage();
                TimeTableManager.initialise();
            } catch (TimeTableInitialiseException e) {
                ui.outputToUser(TIMETABLE_NOT_INITIALISED);
            }
        }
        TextUi.showWelcomeMessage();
        logger.getLogger().info("Initialised scanner, UI, and IO");
    }

    /** Runs the program until termination.  */
    public void run(String[] args) throws FileNotFoundException {
        logger.getLogger().info("STARTING PROGRAM...");
        start(args);
        runCommandLoopUntilExitCommand();
        InputOutputManager.save();
        InputOutputManager.saveNusMods();
        logger.getLogger().info("PROGRAM TERMINATED SUCCESSFULLY");
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        logger.getLogger().info("ENTERING COMMAND LOOP");
        do {
            String userInput = TextUi.getUserCommand();
            command = new Parser().parseCommand(userInput);
            CommandResult result = command.execute();
            if (command.getPromptType() == PromptType.EDIT) {
                StateManager.saveState();
            }
            ui.showResultToUser(result);
        } while (!ExitCommand.isExit(command));
    }

    private CommandResult getResponse(String userInput) {
        return Executor.executeCommand(userInput);
    }
}
