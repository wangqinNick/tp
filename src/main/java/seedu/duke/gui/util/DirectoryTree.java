package seedu.duke.gui.util;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import seedu.duke.data.ModuleManager;
import seedu.duke.directory.Module;
import seedu.duke.directory.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class DirectoryTree extends TreeView<Label> {

    /**
     * Constructs the Directory Tree class.
     */
    public DirectoryTree() {
        super();

        setStyle("-fx-font-family: Consolas; -fx-font-size: 11pt; -fx-font-weight: bold; "
                + "-fx-focus-color: transparent; -fx-faint-focus-color: transparent; "
                + "-fx-border-color: lightgrey; -fx-border-radius: 3;");
        setShowRoot(false);

        populateTree();
    }

    private void populateTree() {
        TreeItem<Label> root = new TreeItem<>(new Label("Root"));

        // Get and sort Module List
        ArrayList<Module> moduleList = ModuleManager.getModuleList();
        moduleList.sort(Comparator.comparing(Module::getModuleCode));

        for (Module module : moduleList) {
            // Add module to tree
            TreeItem<Label> moduleItem = new TreeItem<>();
            styleTreeItem(moduleItem, root, module.getModuleCode(), Color.SANDYBROWN);
            // Get and sort Task List
            ArrayList<Task> taskList = module.getTasks().getTaskList();
            taskList.sort(Comparator.comparing(Task::getDescription));

            for (Task task : taskList) {
                // Add task to tree
                TreeItem<Label> taskItem = new TreeItem<>();
                if (task.isDone()) {
                    styleTreeItem(taskItem, moduleItem, task.getDescription(), Color.LIGHTGRAY);
                } else if (!task.getDeadline().isPresent()) {
                    // Task is done but not deleted yet
                    styleTreeItem(taskItem, moduleItem, task.getDescription(), Color.CORNFLOWERBLUE);
                } else if (task.getDeadline().isDue()) {
                    // Highlight if task is due
                    styleTreeItem(taskItem, moduleItem, task.getDescription(), Color.CRIMSON);
                    taskItem.getValue().setStyle("-fx-background-color: PeachPuff");
                } else if (task.getDeadline().isBefore(LocalDate.now().plusDays(3))) {
                    // Highlight if task is near deadline (within 2 days)
                    styleTreeItem(taskItem, moduleItem, task.getDescription(), Color.CORAL);
                    taskItem.getValue().setStyle("-fx-background-color: Moccasin");
                } else {
                    styleTreeItem(taskItem, moduleItem, task.getDescription(), Color.CORNFLOWERBLUE);
                }
            }
        }

        this.setRoot(root);
    }

    private void styleTreeItem(TreeItem<Label> treeItem, TreeItem<Label> parentItem, String name, Color color) {
        Label treeLabel = new Label(name);
        treeLabel.setTextFill(color);
        treeLabel.setPadding(new Insets(3));
        treeLabel.setCursor(Cursor.HAND);
        treeItem.setValue(treeLabel);
        treeItem.setExpanded(true);
        parentItem.getChildren().add(treeItem);
    }

    public void refresh() {
        populateTree();
    }
}
