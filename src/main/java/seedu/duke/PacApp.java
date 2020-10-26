package seedu.duke;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.duke.data.IoManager;
import seedu.duke.data.ModuleLoader;
import seedu.duke.data.ModuleManager;
import seedu.duke.data.StoragePath;
import seedu.duke.data.SystemSetting;

import java.io.IOException;
import java.util.HashMap;

/**
 * Runs the application.
 */
public class PacApp extends Application {

    public PacApp() {

    }

    /**
     * init the program:
     * initialize the task manager, io manager, and the state manager
     */
    @Override
    public void init() {
        //todo initialize
        //SystemSetting.initialise();
        //TaskManager.initialise();
        SystemSetting.initialise();
        //IOManager.loadList();
        //StateManager.initialise();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("PAC");
        primaryStage.getIcons().add(new Image("images/venus_icon.png"));
        //todo save data when closing

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent mainRoot = sceneLoader.load();
        Scene main = new Scene(mainRoot);
        primaryStage.setScene(main);
        primaryStage.show();

    }
}
