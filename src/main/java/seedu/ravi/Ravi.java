package seedu.ravi;

import seedu.ravi.command.CommandResult;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.data.StateManager;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.ui.TextUi;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_NUS_MODS_NOT_LOADED;

public class Ravi {
    private static final RaviLogger logger = new RaviLogger(Ravi.class.getName());

    /**
     * Entry-point for the java.ravi.Ravi application.
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

        run(args);
    }

    /**
     * Sets up the storage, loads up the data from the storage file and prints the welcome message.
     *
     * @param args
     *  From the command line
     * @throws NusModsNotLoadedException
     *  When no NUSMods data can be loaded
     */
    private static void start(String[] args) throws NusModsNotLoadedException {
        TextUi.initialiseTextUi(new Scanner(System.in));
        int loadStatus = InputOutputManager.start();
        StateManager.initialise();
        TextUi.showWelcomeMessage(loadStatus);
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
    public static void run(String[] args) {
        logger.getLogger().info("STARTING PROGRAM...");
        try {
            start(args);
        } catch (NusModsNotLoadedException e) {
            // Show NUSMods not loaded error message if NUSMods not loaded and crash!
            TextUi.showResultToUser(new IncorrectCommand(MESSAGE_NUS_MODS_NOT_LOADED).execute());
            return;
        }
        runCommandLoopUntilExitCommand();
        // Will save in shutdown hook
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private static void runCommandLoopUntilExitCommand() {
        logger.getLogger().info("ENTERING COMMAND LOOP");
        CommandResult result;
        String userInput;
        do {
            userInput = TextUi.getUserCommand();
            result = Executor.executeCommand(userInput); // Saves state too
            TextUi.showResultToUser(result);
        } while (!result.isExit);
    }
}
