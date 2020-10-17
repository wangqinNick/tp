package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.edit.EditCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;

import static seedu.duke.util.Message.*;

public class EditCommandParser {

    protected static Command getEditCommand(String parameters) {
        Matcher matcher = Parser.PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, EditCommand.FORMAT));
        }

        String commandFlag = Parser.isMatcherNull(matcher.group(Parser.COMMAND_FLAG_GROUP))
                ? null : matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();

        if (commandFlag.equals(Parser.MODULE_PREFIX)) {
            return prepareEditModuleCommand(parameters);
        } else if (commandFlag.equals(Parser.TASK_PREFIX)) {
            return prepareEditTaskCommand(parameters);
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
        Matcher matcher = PARAMETERS_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    EditModuleCommand.FORMAT));
        }

        String oldModuleCode = matcher.group(TASK_NAME_GROUP).trim();
        String newModuleCode = matcher.group(DUE_DATE_GROUP).trim();

        return new EditModuleCommand(oldModuleCode, newModuleCode);
    }

    protected static Command prepareEditTaskCommand(String parameters) throws InvalidParameterException,NumberFormatException {
        Matcher matcher = PARAMETERS_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    EditModuleCommand.FORMAT));
        }

        String stringTaskIndex = matcher.group(TASK_NAME_GROUP).trim();
        int taskIndex = Integer.parseInt(stringTaskIndex) - 1;
        String newTaskDescription = matcher.group(DUE_DATE_GROUP).trim();

        if (isNothingToEdit(newTaskDescription)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_TASK);
        }

        return new EditTaskCommand(taskIndex,newTaskDescription);
    }
}
