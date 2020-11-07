//@@author tobiasceg
package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.edit.EditCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;
import seedu.duke.exception.InvalidMatchException;

import java.security.InvalidParameterException;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN;
import static seedu.duke.util.ExceptionMessage.MESSAGE_NO_EDIT_TASK;
import static seedu.duke.util.ExceptionMessage.MESSAGE_NO_EDIT_MODULE;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class EditCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final String COMMAND_FLAG_GROUP = "commandFlag";
    protected static final String ARGUMENT_IDENTIFIER_GROUP = "argument";
    protected static final String TASK_INDEX_IDENTIFIER_GROUP = "taskIndex";
    protected static final String TASK_DESC_IDENTIFIER_GROUP = "desc";
    protected static final String BY_FLAG_IDENTIFIER_GROUP = "by";
    protected static final String DEADLINE_IDENTIFIER_GROUP = "deadline";

    protected static final Pattern EDIT_PREFIX_FORMAT =
            Pattern.compile("(?<commandFlag>-\\S+)" + "(?<argument>.*)");

    protected static final Pattern EDIT_FORMAT =
            Pattern.compile("(?<taskIndex>\\S+)" + "(?<desc>(?:(?!-by).)*)" + "((?<by>-by)?)" + "((?<deadline>.*)?)");

    /**
     * Splits the user's input, based on the prefix and parse it into the respective prepare methods.
     *
     * @param parameters
     *  The parameters given by the user, but unknown if its module or task yet
     * @return
     *  The command to prepare the respective module or task command for edit
     */
    protected static Command getEditCommand(String parameters) throws InvalidMatchException {
        Matcher matcher = EDIT_PREFIX_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, EditModuleCommand.FORMAT + "\n" + EditTaskCommand.FORMAT,
                EditCommand.PROMPT_HELP);

        String commandFlag = Parser.isMatcherNull(matcher.group(COMMAND_FLAG_GROUP))
                ? null : matcher.group(COMMAND_FLAG_GROUP).toLowerCase().trim();
        String argument = Parser.isMatcherNull(matcher.group(ARGUMENT_IDENTIFIER_GROUP))
                ? null : matcher.group(ARGUMENT_IDENTIFIER_GROUP).toLowerCase().trim();

        if (commandFlag.equals(MODULE_PREFIX)) {
            return prepareEditModuleCommand(argument.trim());
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return prepareEditTaskCommand(argument.trim());
        } else {
            throw new InvalidParameterException();
        }
    }

    /**
     * Prepares the command to edit a module.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to edit a module
     */
    protected static Command prepareEditModuleCommand(String parameters)
            throws InvalidParameterException, InvalidMatchException {
        Matcher matcher = EDIT_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, EditModuleCommand.FORMAT, EditCommand.PROMPT_HELP);

        String oldModuleCode = matcher.group(TASK_INDEX_IDENTIFIER_GROUP).trim();
        String newModuleCode = matcher.group(TASK_DESC_IDENTIFIER_GROUP).trim();

        if (Parser.isEmptyParse(oldModuleCode) || Parser.isEmptyParse(newModuleCode)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_MODULE);
        } else {
            return new EditModuleCommand(oldModuleCode, newModuleCode);
        }
    }

    /**
     * Prepares the command to edit a task.
     *
     * @param parameters
     *  The parameters given by user
     * @return
     *  The command to edit a task
     */
    protected static Command prepareEditTaskCommand(String parameters)
            throws InvalidParameterException, NumberFormatException, InvalidMatchException {
        Matcher matcher = EDIT_FORMAT.matcher(parameters);

        Parser.matcherMatches(matcher, parameters, EditTaskCommand.FORMAT, EditCommand.PROMPT_HELP);

        String stringTaskIndex = matcher.group(TASK_INDEX_IDENTIFIER_GROUP).trim();
        int taskIndex = Integer.parseInt(stringTaskIndex) - 1;
        String newTaskDescription = matcher.group(TASK_DESC_IDENTIFIER_GROUP).trim();

        // Checks for presence of -by
        String taskDeadline = null;
        String dashBy = matcher.group(BY_FLAG_IDENTIFIER_GROUP);
        if (dashBy != null) {
            taskDeadline = matcher.group(DEADLINE_IDENTIFIER_GROUP).trim();
            if (taskDeadline.isEmpty()) { // -by is present but empty deadline
                return new IncorrectCommand(String.format("%s%s\n\n%s%s\n\n%s\n",
                        MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                        EditTaskCommand.FORMAT, EditCommand.PROMPT_HELP));
            }
        }

        if (Parser.isEmptyParse(newTaskDescription)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_TASK);
        } else if (dashBy == null) {
            return new EditTaskCommand(taskIndex, newTaskDescription);
        }

        try {
            return new EditTaskCommand(taskIndex, newTaskDescription, taskDeadline);
        } catch (DateTimeParseException e) {
            return new IncorrectCommand(MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN);
        }

    }
}
