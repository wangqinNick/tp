package seedu.duke.gui.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import seedu.duke.Executor;
import seedu.duke.PacApp;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.gui.component.DialogBox;
import seedu.duke.parser.Parser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private AnchorPane mainLayout;
    @FXML
    private Button sendButton;
    @FXML
    private MenuItem newTask;
    @FXML
    private MenuItem allCommands;
    @FXML
    private MenuItem aboutUs;



    private Image user = new Image(this.getClass().getResourceAsStream("/images/original.gif"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/tenor.gif"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Auto scroll-down
        scrollPane.vvalueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                mainLayout.setLayoutY(-new_val.doubleValue());
            }
        });
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //execute command
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        //menu actions
        //todo add meaningful operations here
        newTask.setOnAction((event) -> {
            handleUserInput();
        });
        allCommands.setOnAction((event) -> {
            handleUserInput();
        });
        aboutUs.setOnAction((event) -> {
            FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/aboutWindow.fxml"));
            Parent mainRoot = null;
            try {
                mainRoot = sceneLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene main = new Scene(mainRoot);
            Stage primaryStage = new Stage();
            primaryStage.setScene(main);
            primaryStage.show();
        });

    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke), PromptType.NONE));
        userInput.clear();
    }

    private String getResponse(String userInput) {
        Command parsedCommand = new Parser().parseCommand(userInput);
        //promptType = parsedCommand.getPromptType();
        CommandResult commandResult;
        commandResult = Executor.executeCommand(parsedCommand);
        //CommandManager.add(userInput);
        return commandResult.getFeedbackToUser();
    }
}
