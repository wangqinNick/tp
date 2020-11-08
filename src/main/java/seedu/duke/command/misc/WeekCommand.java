package seedu.duke.command.misc;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.duke.PacApp;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.gui.DailyTaskWindow;

import java.io.IOException;

public class WeekCommand extends Command {
    public static final String COMMAND_WORD = "week";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = String.format(
            "%s - show upcoming week tasks\n"
                    + "Format: %s\n",
            COMMAND_WORD, FORMAT);


    public WeekCommand() {
    }

    @Override
    public CommandResult execute() {
        FXMLLoader loader = new FXMLLoader(PacApp.class.getResource("/myDays.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Week");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CommandResult("week");
    }
}
