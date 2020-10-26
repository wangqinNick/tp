package seedu.duke.gui.component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import seedu.duke.command.PromptType;

import static javafx.scene.layout.BorderStroke.MEDIUM;
import static seedu.duke.util.Constant.DEFAULT_DIALOG_BORDER_COLOR;
import static seedu.duke.util.Constant.DEFAULT_DIALOG_COLOR;
import static seedu.duke.util.Constant.DEFAULT_DIALOG_FONT;
import static seedu.duke.util.Constant.DEFAULT_DIALOG_SIZE;
import static seedu.duke.util.Constant.INFORMATIVE_DIALOG_COLOR;
import static seedu.duke.util.Constant.USER_DIALOG_COLOR;
import static seedu.duke.util.Constant.WARNING_DIALOG_COLOR;

public class DialogBox extends HBox {

    /**
     * Constructs the dialog box with a label and a imageView
     *
     * @param l the label
     * @param iv the imageView
     */
    public DialogBox(Label l, ImageView iv) {
        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns the user dialog box after config its label and imageView with certain properties
     *
     * @param l the label
     * @param iv the imageView
     * @return the user dialog box contains the label and imageView
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        customLabelAndImageView(l, iv, Color.web(USER_DIALOG_COLOR));
        return new DialogBox(l, iv);
    }

    /**
     * Returns the duck response dialog box with the configured label and imageView
     *
     * @param l the label
     * @param iv the imageView
     * @param promptType the user command's type
     * @return the duck dialog box with the label and imageView
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv, PromptType promptType) {
        switch (promptType){
        case WARNING:
            customLabelAndImageView(l, iv, Color.web(WARNING_DIALOG_COLOR));
            break;
        case INFORMATIVE:
            customLabelAndImageView(l, iv, Color.web(INFORMATIVE_DIALOG_COLOR));
            break;
        case NONE:
        case EDIT:
        default:
            customLabelAndImageView(l, iv, Color.web(DEFAULT_DIALOG_COLOR));
        }
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }

    /**
     * Configs the label and imageView
     *
     * @param l the label
     * @param iv the imageView
     * @param paint the color code
     */
    private static void customLabelAndImageView(Label l, ImageView iv, Paint paint) {
        Font font = new Font(DEFAULT_DIALOG_FONT, DEFAULT_DIALOG_SIZE);
        l.setFont(font);
        l.setMaxWidth(375);
        l.setMinHeight(50);
        l.setBorder(new Border(new BorderStroke(Color.web(DEFAULT_DIALOG_BORDER_COLOR), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, MEDIUM)));
        l.setBackground(new Background(new BackgroundFill(paint, CornerRadii.EMPTY, Insets.EMPTY)));
        l.setOpacity(0.8);
        iv.setPreserveRatio(true);
    }
}