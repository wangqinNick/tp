package seedu.duke.ui;

import seedu.duke.command.CommandResult;
import seedu.duke.data.Lesson;
import seedu.duke.data.Module;
import seedu.duke.data.Task;
import seedu.duke.util.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static seedu.duke.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.duke.util.Message.MESSAGE_GENERAL_HELP;
import static seedu.duke.util.Message.MESSAGE_COMPLETED_TASKLIST;
import static seedu.duke.util.Message.MESSAGE_INCOMPLETE_DATED_TASKLIST;
import static seedu.duke.util.Message.MESSAGE_INCOMPLETE_UNDATED_TASKLIST;
import static seedu.duke.util.Message.MESSAGE_LOADING_FAILURE;
import static seedu.duke.util.Message.MESSAGE_LOADING_SKIPPED;
import static seedu.duke.util.Message.MESSAGE_LOADING_SUCCESS;
import static seedu.duke.util.Message.MESSAGE_NO_LESSONS;
import static seedu.duke.util.Message.MESSAGE_TIMETABLE_HEADER;
import static seedu.duke.util.Message.MESSAGE_TIMETABLE_INIT;
import static seedu.duke.util.Message.MESSAGE_TIMETABLE_FOOTER;
import static seedu.duke.util.Message.MESSAGE_TIMETABLE_MIDDLE;

public class TextUi {
    private static Scanner in;

    //Offset required to convert between 1-indexing and 0-indexing
    public static final int DISPLAY_INDEX_OFFSET = 1;

    public static final String DIV_LINE =
            "════════════════════════════════════════════════════════════════════════════════";
    public static final int MAX_WIDTH = DIV_LINE.length();

    //%1$ catches the furthest left arg, %2$ catches the 2nd arg
    private static final String MESSAGE_INDEX_LIST_FORMAT = "\n%1$d. %2$s";

    public TextUi() {
    }

    public TextUi(Scanner in) {
        TextUi.in = in;
    }

    /**
     * Prints welcome message, and shows file loading status according to the status parameter.
     * Enum not used because it's only used by InputOutputManager.start() and this function.
     * 0 - Files exist, loading success
     * 1 - Files do not exist, skipping
     * 2 - Files exist, error parsing JSON
     * @param status
     *  The status code as shown above
     */
    public static void showWelcomeMessage(int status) {
        String message;
        switch (status) {
        case 0:
            message = MESSAGE_LOADING_SUCCESS;
            break;
        case 1:
            message = MESSAGE_LOADING_SKIPPED;
            break;
        case 2:
            message = MESSAGE_LOADING_FAILURE;
            break;
        default:
            message = MESSAGE_LOADING_SUCCESS;
            break;
        }
        outputToUser(
                DIV_LINE,
                centerString(MAX_WIDTH, Message.MESSAGE_WELCOME),
                "",
                centerString(MAX_WIDTH, message),
                DIV_LINE);
    }

    public void showTimeTableInitialisationMessage() {
        outputToUser(
                DIV_LINE,
                MESSAGE_TIMETABLE_INIT,
                DIV_LINE);
    }

    /**
     * Shows the result of a command execution to the user.
     *
     * @param result the relevant message shown to user
     */
    public void showResultToUser(CommandResult result) {
        outputToUser(
                DIV_LINE,
                result.feedbackToUser,
                DIV_LINE);
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
        int displayIndex = DISPLAY_INDEX_OFFSET;
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
        int displayIndex = DISPLAY_INDEX_OFFSET;
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
    public static String getSummaryList(ArrayList<ArrayList<Task>> summaryLists) {
        final StringBuilder message = new StringBuilder();
        
        ArrayList<Task> incompleteDatedList = summaryLists.get(0);
        message.append(MESSAGE_INCOMPLETE_DATED_TASKLIST);
        if (incompleteDatedList.size() > 0) {
            message.append(TextUi.getIndexTaskList(incompleteDatedList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        ArrayList<Task> incompleteUndatedList = summaryLists.get(1);
        message.append(MESSAGE_INCOMPLETE_UNDATED_TASKLIST);
        if (incompleteUndatedList.size() > 0) {
            message.append(TextUi.getIndexTaskList(incompleteUndatedList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        ArrayList<Task> completeList = summaryLists.get(2);
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
        System.out.println("\n\nCommand: ");
        System.out.print("⋗\t");
        String userInput = in.nextLine();

        while (isEmptyCheck(userInput)) {
            userInput = in.nextLine();
        }

        return userInput;
    }

    public static int getCurrentWeekNum() {
        String userInput = in.nextLine().trim();
        return Integer.parseInt(userInput);
    }

    /**
     * Gets command list.
     *
     * @return the list of available commands
     */
    public static String getCommandList() {
        return MESSAGE_GENERAL_HELP;
    }

    /**
     * Gets Help Message prompt.
     *
     * @return the list of available commands
     */
    public static String getCommandHelpMessage(String commandWord) {
        return String.format("For more information on %s, type `help %s`", commandWord, commandWord);
    }

    /**
     * Prints day timetable.
     *
     * @return the String of the day's timetable
     */
    public static String printDayTimetable(LocalDate now, ArrayList<Lesson> lessonList) {
        StringBuilder output = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(", dd-MM-yy");
        if (lessonList.size() > 0) {
            output.append(System.lineSeparator())
                    .append(now.getDayOfWeek() + now.format(formatter) + ":")
                    .append(System.lineSeparator())
                    .append(MESSAGE_TIMETABLE_HEADER);
            for (Lesson lesson : lessonList) {
                int lessonNumber = lessonList.indexOf(lesson) + 1;
                output.append(printLessonBlock(lesson, lessonNumber));
                if (lessonNumber == lessonList.size()) {
                    output.append(MESSAGE_TIMETABLE_FOOTER);
                } else {
                    output.append(MESSAGE_TIMETABLE_MIDDLE);
                }
            }
        } else {
            output = new StringBuilder(MESSAGE_NO_LESSONS + now.getDayOfWeek() + now.format(formatter) + ".\n");
        }
        return output.toString();
    }

    /**
     * Prints a timetable block for a particular lesson.
     *
     * @return the timetable block containing lesson time, number and name
     */
    public static String printLessonBlock(Lesson lesson, int lessonIndex) {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmm");
        String startTime = lesson.getStartTime().format(time);
        String endTime = lesson.getEndTime().format(time);
        String lessonNumber = String.format("%02d", lessonIndex);
        String message = String.format(" │%s│%s│%s│",
                centerString(11, startTime + "-" + endTime),
                centerString(4, lessonNumber),
                centerString(20, lesson.getModuleCode() + " " + lesson.getLessonTypeString()));

        return message;
    }

    /**
     * Simple function that returns a string centered in 'width' number of characters.
     * Empty characters (i.e. left/right padding) are spaces.
     *
     * @param width
     *  The number of characters for width
     * @param s
     *  The string to be centered
     * @return
     *  The centered string
     */
    public static String centerString(int width, String s) {
        return String.format("%-" + width  + "s",
            String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }
}

