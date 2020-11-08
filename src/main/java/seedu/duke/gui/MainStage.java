package seedu.duke.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.duke.Executor;
import seedu.duke.PacApp;
import seedu.duke.command.Command;
import seedu.duke.data.CommandManager;
import seedu.duke.gui.util.DailyTaskCounter;
import seedu.duke.parser.Parser;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.ResourceBundle;

public class MainStage implements Initializable {

    private Parent dailyTaskRoot;

    /* Console components */
    @FXML
    private TextField console;
    /* Screen components */
    @FXML
    private TextFlow consoleScreen;
    @FXML
    private ScrollPane consoleScreenScrollPane;

    private DailyTaskWindow dailyTask;

    @FXML
    private VBox dailyTaskPlaceHolder;

    /* Daily Task Counter Panel components */
    public VBox mondayBox;
    public Label mondayTaskCount;
    public Label mondayDate;
    public VBox tuesdayBox;
    public Label tuesdayTaskCount;
    public Label tuesdayDate;
    public VBox wednesdayBox;
    public Label wednesdayTaskCount;
    public Label wednesdayDate;
    public VBox thursdayBox;
    public Label thursdayTaskCount;
    public Label thursdayDate;
    public VBox fridayBox;
    public Label fridayTaskCount;
    public Label fridayDate;
    public VBox saturdayBox;
    public Label saturdayTaskCount;
    public Label saturdayDate;
    public VBox sundayBox;
    public Label sundayTaskCount;
    public Label sundayDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Auto scroll-down
        // 自动滚动
        consoleScreenScrollPane.vvalueProperty().bind(consoleScreen.heightProperty());
        // 设置滚动条字体大小
        consoleScreen.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt");

        // 执行用户命令
        console.textProperty().addListener((observable, oldValue, newValue) -> {
            String currentUserInput = console.getText();
        });

        console.setOnAction(this::onSubmitInput);
        console.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        // Set focus on console on start
        Platform.runLater(() -> console.requestFocus());
    }

    private void onSubmitInput(ActionEvent actionEvent) {
        String userInput = console.getText().trim();
        Command parsedCommand = Parser.parseCommand(userInput);
        new Executor(consoleScreen).executeCommand(parsedCommand);
        CommandManager.add(userInput);
        refreshScene();
    }

    @FXML
    public void showDirectoryTree() throws IOException {
        FXMLLoader loader = new FXMLLoader(PacApp.class.getResource("/directoryTree.fxml"));
        Parent root = loader.load();
        loader.<DirectoryTreeWindow>getController().setParentController(this);
        Stage stage = new Stage();
        stage.setTitle("Module View");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void showDailyTask() {
        FXMLLoader loader = new FXMLLoader(PacApp.class.getResource("/myDays.fxml"));
        try {
            dailyTaskRoot = loader.load();
            loader.<DailyTaskWindow>getController().setParentController(this);
            dailyTaskPlaceHolder.getChildren().add(dailyTaskRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void hideDailyTask() {
        dailyTaskPlaceHolder.getChildren().removeAll(dailyTaskRoot);
    }

    private void refreshScene() {
        console.clear();
        refreshTaskCounterPanel();
    }

    private void refreshTaskCounterPanel() {
        new DailyTaskCounter(mondayBox, mondayTaskCount, mondayDate, DayOfWeek.MONDAY).setDailyTaskCount();
        new DailyTaskCounter(tuesdayBox, tuesdayTaskCount, tuesdayDate, DayOfWeek.TUESDAY).setDailyTaskCount();
        new DailyTaskCounter(wednesdayBox, wednesdayTaskCount, wednesdayDate, DayOfWeek.WEDNESDAY).setDailyTaskCount();
        new DailyTaskCounter(thursdayBox, thursdayTaskCount, thursdayDate, DayOfWeek.THURSDAY).setDailyTaskCount();
        new DailyTaskCounter(fridayBox, fridayTaskCount, fridayDate, DayOfWeek.FRIDAY).setDailyTaskCount();
        new DailyTaskCounter(saturdayBox, saturdayTaskCount, saturdayDate, DayOfWeek.SATURDAY).setDailyTaskCount();
        new DailyTaskCounter(sundayBox, sundayTaskCount, sundayDate, DayOfWeek.SUNDAY).setDailyTaskCount();
    }

    /**
     * Handles key presses for `userInput`, displaying the command history on
     * {@code KeyCode.UP} and {@code KeyCode.DOWN}.
     *
     * @param keyCode the key that was pressed by the user.
     */
    private void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
        case UP:
            console.setText(CommandManager.traverseUpHistoryCommand());
            break;
        case DOWN:
            console.setText(CommandManager.traverseDownHistoryCommand());
            break;
        default:
            return;
        }
        // Set the caret position to the end of the string.
        console.positionCaret(console.getLength());
    }
}
