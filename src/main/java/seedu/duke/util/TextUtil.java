package seedu.duke.util;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TextUtil {

    /**
     * Converts a string into a Text.
     *
     * @param input
     *  The input string
     * @return
     *  The corresponding Text
     */
    public static Text createText(String input) {
        return new Text(input);
    }

    /**
     * Converts a string into a colored Text.
     *
     * @param input
     *  The input string to be colored
     * @param color
     *  The color of the Text
     * @return
     *  The corresponding colored Text
     */
    public static Text createText(String input, Color color) {
        Text text = new Text(input);
        text.setFill(color);
        return text;
    }
}
