package seedu.duke.ui;

import seedu.duke.command.CommandResult;
import seedu.duke.util.Message;
import seedu.duke.data.Module;
import seedu.duke.data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static seedu.duke.util.Message.MESSAGE_HELP;

public class TextUi {
    private static Scanner in;

    //Offset required to convert between 1-indexing and 0-indexing
    public static final int DISPLAY_INDEX_OFFSET = 1;

    public static final String DIVIDER_PREFIX = "||";

    public static final String DIVIDER_LINE = "~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~*";

    //%1$ catches the furthest left arg, %2$ catches the 2nd arg
    private static final String MESSAGE_INDEX_LIST_FORMAT = "\n%1$d. %2$s";

    public TextUi() {
    }

    public TextUi(Scanner in) {
        this.in = in;
    }

    public static void showGoodByeMessage() {
        outputToUser(
                DIVIDER_LINE,
                Message.MESSAGE_GOODBYE,
                DIVIDER_LINE);
    }

    public static void showWelcomeMessage()     {
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

    public static void outputIndexTaskList(ArrayList<Task> taskList) {
        outputToUser(getIndexTaskList(taskList));
    }

    public static void outputIndexModuleList(HashMap<String, Module> modulesMap) {
        outputToUser(getIndexModuleList(modulesMap));
    }

    /**
     * Formats an Arraylist of type Task with their Index.
     *
     * @param taskList the list to be formatted
     */
    public static String getIndexTaskList(ArrayList<Task> taskList) {
        final StringBuilder stringFormat = new StringBuilder();
        int displayIndex = 0 + DISPLAY_INDEX_OFFSET;
        for (Task t : taskList) {
            stringFormat.append(getIndexListFormat(displayIndex, t.toString())).append("\n");
            displayIndex++;
        }
        return stringFormat.toString();
    }

    /**
     * Formats the HashMap to string with their index.
     *
     * @param modulesMap the HashMap to be formatted
     */
    public static String getIndexModuleList(HashMap<String, Module> modulesMap) {
        final StringBuilder stringFormat = new StringBuilder();
        int displayIndex = 0 + DISPLAY_INDEX_OFFSET;
        for (Module module : modulesMap.values()) {
            stringFormat.append(getIndexListFormat(displayIndex, module.toString()));
            stringFormat.append("\n");
            displayIndex++;
        }
        return stringFormat.toString();
    }

    /**
     *  Formats a string with its index in the list.
     *
     * @param listIndex task/module index
     * @param listItem task/module name or description
     */
    public static String getIndexListFormat(int listIndex, String listItem) {
        return String.format(MESSAGE_INDEX_LIST_FORMAT, listIndex, listItem);
    }


    /**
     * Trims spacing and checks if input is empty.
     *
     * @param rawInputLine full input from user
     * @return true if inputline is a legit command
     */
    private static boolean inputChecker(String rawInputLine) {
        boolean isEmpty = rawInputLine.trim().isEmpty();
        return isEmpty;
    }

    /**
     * gets the User's input command.
     *
     * @return the trimmed command input
     */
    public static String getUserCommand() {
        System.out.println("Enter Command: ");
        String userInput = in.nextLine();

        while (inputChecker(userInput)) {
            userInput = in.nextLine();
        }

        return userInput;
    }

    public void showResultToUser(CommandResult result) {
    }

    /**
     * gets Help Message.
     *
     * @return the list of available commands
     */
    public static String getHelpMessage() {
        return MESSAGE_HELP;
    }
}
