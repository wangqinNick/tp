package seedu.duke;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.duke.command.Command;
import seedu.duke.command.PromptType;
import seedu.duke.data.ScreenShotManager;
import seedu.duke.util.TextUtil;

public class Executor {
    public static final String DIVIDER = "-".repeat(50);
    private static TextFlow consoleScreen;

    public Executor(TextFlow consoleScreen) {
        Executor.consoleScreen = consoleScreen;
    }

    /**
     * Returns the command result after the command is executed
     *
     * @param parsedCommand The input from the user to be executed as a command
     */
    public void executeCommand(Command parsedCommand) {
        var commandResult = parsedCommand.execute();
        if (parsedCommand.getPromptType() == PromptType.EDIT){
            ScreenShotManager.saveScreenShot();
        }
        Text response = TextUtil.createText(String.format("%s", commandResult.getFeedbackToUser()), Color.MIDNIGHTBLUE);
        consoleScreen.getChildren().add(response);
        Text divider = TextUtil.createText(String.format("\n%s\n\n", DIVIDER), Color.DARKKHAKI);
        consoleScreen.getChildren().add(divider);
    }
}
