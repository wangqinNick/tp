package seedu.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.duke.data.SystemSetting;

import java.io.IOException;

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
    public void init() throws Exception {
        super.init();
        //todo initialize
        //SystemSetting.initialise();
        //TaskManager.initialise();
        SystemSetting.initialise();
        //IOManager.loadList();
        //StateManager.initialise();
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Pac");
        stage.getIcons().add(new Image("images/venus_icon.png"));
        stage.setMinWidth(600);
        stage.setMinHeight(400);

        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Parent mainRoot = sceneLoader.load();
        Scene main = new Scene(mainRoot);

        stage.setScene(main);
        stage.show();
    }
}
