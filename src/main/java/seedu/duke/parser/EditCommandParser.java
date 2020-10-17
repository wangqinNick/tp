package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_NO_EDIT_TASK;

public class EditCommandParser {
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    protected static final String COMMAND_FLAG_GROUP = "commandFlag";
    protected static final String ARGUMENT_IDENTIFIER_GROUP = "argument";
    protected static final String FIRST_ARGUMENT_IDENTIFIER_GROUP = "firstArg";
    protected static final String SECOND_ARGUMENT_IDENTIFIER_GROUP = "secondArg";
    protected static final String INVALID_GROUP = "invalid";

    protected static final Pattern EDIT_PREFIX_FORMAT =
            Pattern.compile("(?<commandFlag>-\\S+)" + "(?<argument>.*)");

    protected static final Pattern EDIT_FORMAT =
            Pattern.compile("(?<firstArg>\\S+)" + "(?<secondArg>.*)"); 

    protected static Command getEditCommand(String parameters) {
        Matcher matcher = EDIT_PREFIX_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n%s",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, EditTaskCommand.FORMAT,
                    EditModuleCommand.FORMAT));
        }

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
    protected static Command prepareEditModuleCommand(String parameters) throws InvalidParameterException {
        Matcher matcher = EDIT_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    EditModuleCommand.FORMAT));
        }

        String oldModuleCode = matcher.group(FIRST_ARGUMENT_IDENTIFIER_GROUP).trim();
        String newModuleCode = matcher.group(SECOND_ARGUMENT_IDENTIFIER_GROUP).trim();


        return new EditModuleCommand(oldModuleCode, newModuleCode);
    }

    protected static Command prepareEditTaskCommand(String parameters)
            throws InvalidParameterException,NumberFormatException {
        Matcher matcher = EDIT_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    EditModuleCommand.FORMAT));
        }

        String stringTaskIndex = matcher.group(FIRST_ARGUMENT_IDENTIFIER_GROUP).trim();
        int taskIndex = Integer.parseInt(stringTaskIndex) - 1;
        String newTaskDescription = matcher.group(SECOND_ARGUMENT_IDENTIFIER_GROUP).trim();

        if (Parser.isEmptyParse(newTaskDescription)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_TASK);
        }


        return new EditTaskCommand(taskIndex,newTaskDescription);
    }
}
