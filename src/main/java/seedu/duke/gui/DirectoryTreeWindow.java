package seedu.duke.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import seedu.duke.gui.util.DirectoryTree;

public class DirectoryTreeWindow {

    private MainStage parentController;

    @FXML
    private VBox displayBox;

    void setParentController(MainStage parentController) {
        this.parentController = parentController;
    }

    @FXML
    void initialize() {
        DirectoryTree directoryTree = new DirectoryTree();
        displayBox.getChildren().add(directoryTree);
        directoryTree.prefHeightProperty().bind(displayBox.heightProperty());
    }
}
