package seedu.ravi;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.StateManager;
import seedu.ravi.data.TimeTableManager;
import seedu.ravi.data.storage.InputOutputManager;
import seedu.ravi.exception.NusModsNotLoadedException;
import seedu.ravi.parser.Parser;
import seedu.ravi.ui.TextUi;

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
            InputOutputManager.save();
        }

        return result;
    }

    /**
     * Initialises all classes that need to be initialised.
     *
     * @throws NusModsNotLoadedException
     *  If the InputOutputManager cannot load any NUSMods data
     */
    public static void initialise() throws NusModsNotLoadedException {
        int loadStatus = InputOutputManager.start();
        StateManager.initialise();
        TextUi.showWelcomeMessage(loadStatus);
        InputOutputManager.saveNusMods();
        while (!TimeTableManager.isInitialised()) {
            TimeTableManager.initialiseTimetable();
        }
    }

    /**
     * Carries out the command loop and exits when result.isExit is true.
     */
    public static void startCommandLoop() {
        CommandResult result;
        String userInput;
        do {
            userInput = TextUi.getUserCommand();
            result = Executor.executeCommand(userInput); // Saves state too
            TextUi.showResultToUser(result);
        } while (!result.isExit);
    }
}
