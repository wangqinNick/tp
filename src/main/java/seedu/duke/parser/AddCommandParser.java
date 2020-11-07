//@@author tobiasceg
package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.add.AddModuleCommand;
import seedu.duke.command.add.AddTaskCommand;
import seedu.duke.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN;
import static seedu.duke.util.ExceptionMessage.MESSAGE_NO_ADD_MODULE;
import static seedu.duke.util.ExceptionMessage.MESSAGE_NO_ADD_TASK;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class AddCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final String COMMAND_FLAG_GROUP = "commandFlag";
    protected static final String DESC_GROUP = "desc";
    protected static final String DEADLINE_GROUP = "deadline";
    protected static final String BY_GROUP = "by";
    //@@author aseanseen
    protected static final Pattern ADD_FORMAT =
            Pattern.compile("(?<commandFlag>-\\S+)" + "(?<desc>(?:(?!-by).)*)"
                    + "((?<by>-by)?)" + "((?<deadline>.*)?)");

    //@@author tobiasceg
    /**
     * Takes the user's input and parses it into the respective arguments for AddCommand.
     *
     * @param parameters
     * the user's input without the command word
     * @return
     * AddCommand with the relevant parameters
     * @throws InvalidParameterException
     * When an invalid parameter is given by the user
     * @throws IllegalStateException
     * When a method has been invoked at an illegal time
     * @throws InvalidMatchException
     * When the user input doesn't match the REGEX format for the AddCommand
     */
    protected static Command prepareAddCommand(String parameters)
            throws InvalidParameterException, IllegalStateException, InvalidMatchException {
        Matcher matcher = ADD_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, AddCommand.FORMAT, AddCommand.PROMPT_HELP);

        String commandFlag = Parser.isMatcherNull(matcher.group(COMMAND_FLAG_GROUP))
                ? null : matcher.group(COMMAND_FLAG_GROUP).toLowerCase().trim();

        String addedTask = matcher.group(DESC_GROUP).trim();
        String taskDeadline = null;
        // Checks for presence of -by
        String dashBy = matcher.group(BY_GROUP);
        if (dashBy != null) {
            taskDeadline = matcher.group(DEADLINE_GROUP).trim();
            if (taskDeadline.isEmpty()) { // -by is present but empty deadline
                return new IncorrectCommand(String.format("%s%s\n\n%s%s\n\n%s\n",
                        MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                        AddTaskCommand.FORMAT, AddCommand.PROMPT_HELP));
            }
        }
        // no task input by user
        if (Parser.isEmptyParse(addedTask)) {
            return (commandFlag.equals(MODULE_PREFIX))
                    ? new IncorrectCommand(MESSAGE_NO_ADD_MODULE)
                    : new IncorrectCommand(MESSAGE_NO_ADD_TASK);
        }
        try {
            return getAddCommand(commandFlag, addedTask, taskDeadline);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN);
        }
    }

    /**
     * Checks the prefix of the user's input and calls the respective Add command according to it.
     *
     * @param commandFlag
     * The command flag which is either '-t' for task or '-m' for module
     * @param addedTask
     * Task or module to be added
     * @param taskDeadline
     * The deadline, if given , assigned to the task
     * @return
     * the respective Add command for either task or module
     */
    private static AddCommand getAddCommand(String commandFlag, String addedTask, String taskDeadline) {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return new AddModuleCommand(addedTask.toUpperCase());
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new AddTaskCommand(addedTask, taskDeadline);
        } else {
            throw new InvalidParameterException();
        }
    }
}
