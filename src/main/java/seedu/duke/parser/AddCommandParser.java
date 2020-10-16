package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;

import static seedu.duke.util.Message.*;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_TASK;

public class AddCommandParser {

    protected static Command getAddCommand(String commandFlag, String parameters) {
        if (commandFlag.equals(Parser.MODULE_PREFIX)) {
            return prepareAddCommand(parameters, Parser.TypeOfEntries.MODULE);
        } else if (commandFlag.equals(Parser.TASK_PREFIX)) {
            return prepareAddCommand(parameters, Parser.TypeOfEntries.TASK);
        } else {
            throw new InvalidParameterException();
        }
    }

    protected static Command prepareAddCommand(String parameters, Parser.TypeOfEntries typeOfTask) throws InvalidParameterException {
        Matcher matcher = Parser.TASK_DEADLINE_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
        }

        String addedTask = matcher.group(TASK_NAME_GROUP).trim();
        String taskDeadline = null;
        // Checks for presence of -by
        String dashBy = matcher.group(DATE_IDENTIFIER_GROUP);
        if (dashBy != null) {
            taskDeadline = matcher.group(DUE_DATE).trim();
            if (taskDeadline.isEmpty()) { // -by is present but empty deadline
                return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                        MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
            }
        }
        // without -by means its fully a task
        if (dashBy == null) {
            addedTask = matcher.group(0).trim();
        }

        // no task input by user
        if (isEmptyParse(addedTask)) {
            return (typeOfTask == Parser.TypeOfEntries.MODULE)
                    ? new IncorrectCommand(MESSAGE_NO_ADD_MODULE)
                    : new IncorrectCommand(MESSAGE_NO_ADD_TASK);
        }

        return new AddCommand(typeOfTask, addedTask, taskDeadline);
    }
}
