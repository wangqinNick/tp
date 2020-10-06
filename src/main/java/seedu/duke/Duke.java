package seedu.duke;

import seedu.duke.command.CommandResult;
import seedu.duke.data.storage.IOManager;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class Duke {
    private HashMap<String, String> modulesMap;
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public Duke() throws FileNotFoundException {
        //todo set root
        //todo load moduleManager with modulesMap
        modulesMap = IOManager.load("moduleList.json");
        System.out.println(modulesMap);
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
