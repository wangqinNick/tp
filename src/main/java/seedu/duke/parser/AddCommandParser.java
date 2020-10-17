package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;

import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_TASK;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_MODULE;

public class AddCommandParser {

    protected static Command getAddCommand(String parameters) {
        Matcher matcher = Parser.PARAMETERS_FORMAT.matcher(parameters);

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
        }

        String commandFlag = Parser.isMatcherNull(matcher.group(Parser.COMMAND_FLAG_GROUP))
                ? null : matcher.group(Parser.COMMAND_FLAG_GROUP).toLowerCase().trim();

        if (commandFlag.equals(Parser.MODULE_PREFIX)) {
            return prepareAddCommand(parameters, Parser.TypeOfEntries.MODULE);
        } else if (commandFlag.equals(Parser.TASK_PREFIX)) {
            return prepareAddCommand(parameters, Parser.TypeOfEntries.TASK);
        } else {
            throw new InvalidParameterException();
        }
    }

    protected static Command prepareAddCommand(String parameters, Parser.TypeOfEntries typeOfTask) throws InvalidParameterException {
        Matcher matcher = Parser.PARAMETERS_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
        }

        String addedTask = matcher.group(Parser.TASK_NAME_GROUP).trim();
        String taskDeadline = null;
        // Checks for presence of -by
        String dashBy = matcher.group(Parser.DATE_IDENTIFIER_GROUP);
        if (dashBy != null) {
            taskDeadline = matcher.group(Parser.DUE_DATE_GROUP).trim();
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
        if (Parser.isEmptyParse(addedTask)) {
            return (typeOfTask == Parser.TypeOfEntries.MODULE)
                    ? new IncorrectCommand(MESSAGE_NO_ADD_MODULE)
                    : new IncorrectCommand(MESSAGE_NO_ADD_TASK);
        }

        return new AddCommand(typeOfTask, addedTask, taskDeadline);
    }
}
