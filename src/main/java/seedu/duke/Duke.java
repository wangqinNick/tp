package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.PromptType;
import seedu.duke.data.StateManager;
import seedu.duke.data.TimeTableManager;
import seedu.duke.data.storage.InputOutputManager;
import seedu.duke.exception.NusModsNotLoadedException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static seedu.duke.util.ExceptionMessage.MESSAGE_NUS_MODS_NOT_LOADED;

public class Duke {
    private TextUi ui;
    private static final DukeLogger logger = new DukeLogger(Duke.class.getName());

    /**
     * Entry-point for the java.duke.Duke application.
     *
     * @param args arguments passed to the program.
     * @throws FileNotFoundException exception is thrown if the file is not found.
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.getLogger().info("Shutdown hook - Saving all data...");
            InputOutputManager.save();
            InputOutputManager.saveNusMods();
            logger.getLogger().info("PROGRAM TERMINATED SUCCESSFULLY");
        }));

        new Duke().run(args);
    }

    /**
     * Sets up the storage, loads up the data from the storage file and prints the welcome message.
     *
     * @param args
     *  From the command line
     * @throws NusModsNotLoadedException
     *  When no NUSMods data can be loaded
     */
    private void start(String[] args) throws NusModsNotLoadedException {
        Scanner in = new Scanner(System.in);
        this.ui = new TextUi(in);
        int loadStatus = InputOutputManager.start();
        StateManager.initialise();
        ui.showWelcomeMessage(loadStatus);
        while (!TimeTableManager.isInitialised()) {
            TimeTableManager.initialiseTimetable();
        }
        logger.getLogger().info("Initialised scanner, UI, and IO");
    }

    /**
     * Runs the program until termination.
     *
     * @param args arguments passed to the program.
     */
    public void run(String[] args) {
        logger.getLogger().info("STARTING PROGRAM...");
        try {
            start(args);
        } catch (NusModsNotLoadedException e) {
            // Show long error message if NUSMods not loaded and crash!
            this.ui.showResultToUser(new IncorrectCommand(MESSAGE_NUS_MODS_NOT_LOADED).execute());
            return;
        }
        runCommandLoopUntilExitCommand();
        // Will save in shutdown hook
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
