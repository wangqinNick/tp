package seedu.duke.ui;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.Task;
import seedu.duke.util.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.duke.util.Message.MESSAGE_COMPLETED_TASKLIST;
import static seedu.duke.util.Message.MESSAGE_HELP;
import static seedu.duke.util.Message.MESSAGE_INCOMPLETE_DATED_TASKLIST;
import static seedu.duke.util.Message.MESSAGE_INCOMPLETE_UNDATED_TASKLIST;

public class TextUi {
    private static Scanner in;

    //Offset required to convert between 1-indexing and 0-indexing
    public static final int DISPLAY_INDEX_OFFSET = 1;

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
            System.out.println(o);
        }
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
            stringFormat.append(getIndexListFormat(displayIndex, t.toString()));
            displayIndex++;
        }
        stringFormat.append("\n");
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
            displayIndex++;
        }
        return stringFormat.toString();
    }

    /**
     * Formats the lists in summaryLists to string with their index.
     *
     * @param summaryLists the list of ArrayLists to be formatted
     */
    public static String getSummaryList(ArrayList<ArrayList<Task>> summaryLists){
        final StringBuilder message = new StringBuilder();
        ArrayList<Task> incompleteDatedList = summaryLists.get(0);
        ArrayList<Task> incompleteUndatedList = summaryLists.get(1);
        ArrayList<Task> completeList = summaryLists.get(2);

        message.append(MESSAGE_INCOMPLETE_DATED_TASKLIST);
        if (incompleteDatedList.size() > 0) {
            message.append(TextUi.getIndexTaskList(incompleteDatedList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        message.append(MESSAGE_INCOMPLETE_UNDATED_TASKLIST);
        if (incompleteUndatedList.size() > 0) {
            message.append(TextUi.getIndexTaskList(incompleteUndatedList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        message.append(MESSAGE_COMPLETED_TASKLIST);
        if (completeList.size() > 0) {
            message.append(TextUi.getIndexTaskList(completeList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        return message.toString();
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
    private static boolean isEmptyCheck(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * gets the User's input command.
     *
     * @return the trimmed command input
     */
    public static String getUserCommand() {
        System.out.println("Enter Command: ");
        String userInput = in.nextLine();

        while (isEmptyCheck(userInput)) {
            userInput = in.nextLine();
        }

        return userInput;
    }

    /**
     * Shows the result of a command execution to the user.
     *
     * @param result the relevant message shown to user
     */
    public void showResultToUser(CommandResult result) {
        outputToUser(
                DIVIDER_LINE,
                result.feedbackToUser,
                DIVIDER_LINE);
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

