package seedu.ravi.ui;

import seedu.ravi.command.CommandResult;
import seedu.ravi.data.Lesson;
import seedu.ravi.data.Module;
import seedu.ravi.data.Task;
import seedu.ravi.util.Message;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;
import static seedu.ravi.util.ExceptionMessage.EXCEPTION_HEADER;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_LIST_EMPTY;
import static seedu.ravi.util.Message.MESSAGE_COMPLETED_TASKLIST;
import static seedu.ravi.util.Message.MESSAGE_GENERAL_HELP;
import static seedu.ravi.util.Message.MESSAGE_INCOMPLETE_DATED_TASKLIST;
import static seedu.ravi.util.Message.MESSAGE_INCOMPLETE_UNDATED_TASKLIST;
import static seedu.ravi.util.Message.MESSAGE_NO_LESSONS;
import static seedu.ravi.util.Message.MESSAGE_TIMETABLE_INIT;

public class TextUi {
    private static Scanner in;

    public static void initialiseTextUi(Scanner in) {
        TextUi.in = in;
    }

    /**
     * Prints welcome message, and shows file loading status according to the status parameter.
     * Enum not used because it's only used by InputOutputManager.start() and this function.
     *
     * @param status
     *  The status code as shown above
     */
    public static void showWelcomeMessage(int status) {
        outputToUser(
                TextHelper.centerString(Message.MESSAGE_WELCOME),
                "",
                TextHelper.parseStatusCode(status));
    }

    public static void showTimeTableInitialisationMessage() {
        outputToUser(MESSAGE_TIMETABLE_INIT);
    }

    /**
     * Shows the result of a command execution to the user.
     *
     * @param result
     *  The relevant message shown to user
     */
    public static void showResultToUser(CommandResult result) {
        if (!result.isError) {
            outputToUser(result.feedbackToUser);
        } else {
            outputToUser(TextHelper.centerString(EXCEPTION_HEADER), result.feedbackToUser);
        }
    }

    /**
     * Print the given Strings to the user, capped at top and bottom by the DIV_LINE.
     *
     * @param output
     *  The strings to be printed to the user
     */
    public static void outputToUser(String... output) {
        System.out.println(TextHelper.DIV_LINE);
        for (String o : output) {
            System.out.println(ansi().render(o));
        }
        System.out.println(TextHelper.DIV_LINE);
    }

    /**
     * Formats the lists in summaryLists to string with their index.
     *
     * @param summaryLists the list of ArrayLists to be formatted
     * @return message as a string, containing the summary list
     */
    public static String getSummaryList(ArrayList<ArrayList<Task>> summaryLists) {
        final StringBuilder message = new StringBuilder();
        
        ArrayList<Task> incompleteDatedList = summaryLists.get(0);
        message.append(MESSAGE_INCOMPLETE_DATED_TASKLIST);
        if (incompleteDatedList.size() > 0) {
            message.append(getIndexTaskList(incompleteDatedList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        ArrayList<Task> incompleteUndatedList = summaryLists.get(1);
        message.append(MESSAGE_INCOMPLETE_UNDATED_TASKLIST);
        if (incompleteUndatedList.size() > 0) {
            message.append(getIndexTaskList(incompleteUndatedList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        ArrayList<Task> completeList = summaryLists.get(2);
        message.append(MESSAGE_COMPLETED_TASKLIST);
        if (completeList.size() > 0) {
            message.append(getIndexTaskList(completeList));
        } else {
            message.append(MESSAGE_LIST_EMPTY);
        }

        return message.toString();
    }

    /**
     * Prints day timetable.
     *
     * @param now the LocalDate object for the current day
     * @param lessonList the lesson list for the day
     * @return the String of the day's timetable
     */
    public static String printDayTimetable(LocalDate now, ArrayList<Lesson> lessonList) {
        StringBuilder output = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(", dd-MM-yy");
        if (lessonList.size() > 0) {
            output.append(System.lineSeparator())
                    .append(now.getDayOfWeek() + now.format(formatter) + ":")
                    .append(System.lineSeparator())
                    .append(TextHelper.MESSAGE_TIMETABLE_HEADER);
            for (Lesson lesson : lessonList) {
                int lessonNumber = lessonList.indexOf(lesson) + 1;
                output.append(printLessonBlock(lesson, lessonNumber));
                if (lessonNumber == lessonList.size()) {
                    output.append(TextHelper.MESSAGE_TIMETABLE_FOOTER);
                } else {
                    output.append(TextHelper.MESSAGE_TIMETABLE_MIDDLE);
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
     * @param lesson the Lesson to be printed
     * @param lessonIndex the index of the Lesson to be printed
     * @return the timetable block containing lesson time, number and name
     */
    public static String printLessonBlock(Lesson lesson, int lessonIndex) {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HHmm");
        String startTime = lesson.getStartTime().format(time);
        String endTime = lesson.getEndTime().format(time);
        String lessonNumber = String.format("%02d", lessonIndex);
        return String.format(" │%s│%s│%s│",
                TextHelper.centerString(11, startTime + "-" + endTime),
                TextHelper.centerString(4, lessonNumber),
                TextHelper.centerString(20, lesson.getModuleCode() + " " + lesson.getLessonTypeString()));
    }

    /**
     * Formats an Arraylist of type Task with their Index.
     *
     * @param taskList the list to be formatted
     * @return stringFormat as a string, containing the index task list
     */
    public static String getIndexTaskList(ArrayList<Task> taskList) {
        final StringBuilder stringFormat = new StringBuilder();
        int displayIndex = TextHelper.DISPLAY_INDEX_OFFSET;
        for (Task t : taskList) {
            stringFormat.append(TextHelper.getIndexListFormat(displayIndex, t.toString()));
            displayIndex++;
        }
        stringFormat.append("\n");
        return stringFormat.toString();
    }

    /**
     * Formats the HashMap to string with their index.
     *
     * @param modulesMap
     *  The HashMap to be formatted
     * @return
     *  Formatted string containing the index module list
     */
    public static String getIndexModuleList(HashMap<String, Module> modulesMap) {
        final StringBuilder stringFormat = new StringBuilder();
        int displayIndex = TextHelper.DISPLAY_INDEX_OFFSET;
        for (Module module : modulesMap.values()) {
            stringFormat.append(TextHelper.getIndexListFormat(displayIndex, module.toString()));
            displayIndex++;
        }
        return stringFormat.toString();
    }

    /**
     * Gets the User's input command.
     *
     * @return
     *  The trimmed user input command
     */
    public static String getUserCommand() {
        System.out.println("\n\nCommand: ");
        System.out.print("»\t");
        String userInput = in.nextLine();

        while (TextHelper.isEmptyCheck(userInput)) {
            userInput = in.nextLine();
        }

        return userInput;
    }

    /**
     * The current week of year.
     *
     * @return
     *  The current week of year.
     */
    public static int getCurrentWeekNum() {
        String userInput = in.nextLine().trim();
        return Integer.parseInt(userInput);
    }

    /**
     * Gets command list.
     *
     * @return
     *  The list of available commands
     */
    public static String getCommandList() {
        return MESSAGE_GENERAL_HELP;
    }

    /**
     * Gets Help Message prompt.
     *
     * @param commandWord
     *  The command word entered by the user
     * @return
     *  The list of available commands
     */
    public static String getCommandHelpMessage(String commandWord) {
        return String.format("For more information on %s, type `help %s`", commandWord, commandWord);
    }
}

