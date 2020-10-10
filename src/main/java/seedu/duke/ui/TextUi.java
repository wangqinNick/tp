package seedu.duke.ui;

import seedu.duke.util.Message;

import java.util.List;
import java.util.Scanner;


public class TextUi {
    private static Scanner in;

    //Offset required to convert between 1-indexing and 0-indexing
    public static final int DISPLAY_INDEX_OFFSET = 1;

    private static final String LS = System.lineSeparator();

    private static final String DIVIDER_PREFIX = "||";

    private static final String DIVIDER_LINE = "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*";

    private static final String MESSAGE_INDEX_LIST_FORMAT = "\n%1$d. %2$s"; //%1$ catches the furthest left arg, %2$ catches the 2nd arg

    public TextUi(Scanner in) {
        this.in = in;
    }

    public static void showGoodByeMessage() {
        outputToUser(
                DIVIDER_LINE,
                Message.MESSAGE_GOODBYE,
                DIVIDER_LINE);
    }

    public static void showWelcomeMessage(){
        outputToUser(
                DIVIDER_LINE,
                Message.MESSAGE_WELCOME,
                DIVIDER_LINE);
    }

    public static void outputToUser(String... output) {
        for (String o : output) {
            System.out.println(DIVIDER_PREFIX + o);
        }
    }

    private void outputIndexList(List<String> list) {
        outputToUser(getIndexList(list));
    }

    /**
     * Formats a list of type String with their Index
     *
     * @param listItems the list to be formatted
     */
    public static String getIndexList(List<String> listItems) {
        final StringBuilder stringFormat = new StringBuilder();
        int displayIndex = 0 + DISPLAY_INDEX_OFFSET;
        for (String listItem : listItems) {
            stringFormat.append(getIndexListFormat(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return stringFormat.toString();
    }

    /**
     *  Formats a string with its index in the list
     *
     * @param listIndex task/module index
     * @param listItem task/module name or description
     */
    public static String getIndexListFormat(int listIndex, String listItem) {
        return String.format(MESSAGE_INDEX_LIST_FORMAT, listIndex, listItem);
    }


    /**
     * Trims spacing and checks if input is empty
     *
     * @param rawInputLine full input from user
     * @return true if inputline is a legit command
     */
    private static boolean inputChecker(String rawInputLine){
        return rawInputLine.trim().isEmpty();
    }

    /**
     * gets the User's input command
     *
     * @return the trimmed command input
     */
    public static String getUserCommand(){
        System.out.println("Enter Command: ");
        String userInput = in.nextLine();

        while (inputChecker(userInput)){
            userInput = in.nextLine();
        }

        return userInput;
    }

}
