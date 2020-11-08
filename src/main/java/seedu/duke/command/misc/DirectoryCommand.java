package seedu.duke.command.misc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.duke.PacApp;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;

import java.io.IOException;

public class DirectoryCommand extends Command {
    public static final String COMMAND_WORD = "dir";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = String.format(
            "%s - show directory list\n"
                    + "Format: %s\n",
            COMMAND_WORD, FORMAT);


    public DirectoryCommand() {
    }

    @Override
    public CommandResult execute() {
        FXMLLoader loader = new FXMLLoader(PacApp.class.getResource("/directoryTree.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Directory");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommandResult("week");
    }
}
