package seedu.ravi;

import org.fusesource.jansi.AnsiConsole;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.ui.TextUi;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_NUS_MODS_NOT_LOADED;
import static seedu.ravi.util.Message.MESSAGE_SHUTDOWN;

public class Ravi {
    private static final RaviLogger logger = new RaviLogger(Ravi.class.getName());

    /**
     * Entry-point for the java.ravi.Ravi application.
     *
     * @param args arguments passed to the program.
     * @throws FileNotFoundException exception is thrown if the file is not found.
     */
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.getLogger().info("PROGRAM TERMINATED SUCCESSFULLY");
            System.out.println();
            TextUi.outputToUser(MESSAGE_SHUTDOWN);
            AnsiConsole.systemUninstall();
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
        Executor.initialise();
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
            logger.getLogger().info("ENTERING COMMAND LOOP");
            Executor.startCommandLoop();
        } catch (NusModsNotLoadedException e) {
            // Show NUSMods not loaded error message if NUSMods not loaded and crash!
            TextUi.showResultToUser(new IncorrectCommand(MESSAGE_NUS_MODS_NOT_LOADED).execute());
        } catch (NoSuchElementException ignored) {
            // User has entered ctrl-c
        }
    }
}
