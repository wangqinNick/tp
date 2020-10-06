package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.data.Module;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_NO_EDIT_MODULE;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_MODULE;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_TASK;

public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)" + "(?<commandFlag>.*-\\S+)"  + "(?<parameters>.*)");

    private static final String COMMAND_WORD_GROUP = "commandWord";
    private static final String COMMAND_FLAG_GROUP = "commandFlag";
    private static final String PARAMETERS_GROUP = "parameters";
    private static final String IDENTIFIER_GROUP = "identifier";
    private static final String TASK_NAME_GROUP = "taskName";
    private static final String MODULE_GROUP = "moduleCode";
    private static final String DATE_IDENTIFIER_GROUP = "by";

    private static final String DUE_DATE = "dueDate";

    private static final String NONE = "";
    private static final String INVALID_GROUP = "invalid";
    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    public static final String BY_PREFIX = "-by";

    private static final Pattern TASK_DEADLINE_FORMAT =
            Pattern.compile("(?<taskName>\\S+)" + "((?<by>.*-\\S+)?)"  + "(?<dueDate>.*)");

    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_LIST = "list";


    //(?<identifier>(?:\s+\w\S*)*)+ -m+ (?<moduleCode>(?:\\s+" + "(?:\\s+\\w\\S*)+)?)(?<invalid>.*)


    /**
     * Parses the input string read by the <b>UI</b> and converts the string into a specific <b>Command</b>, which is
     * to be executed by the <b>Nuke</b> program.
     * <p></p>
     * <b>Note</b>: The user input has to start with a certain keyword (i.e. <i>command word</i>), otherwise an
     * <i>Invalid Command Exception</i> will be thrown.
     *
     * @param input The user input read by the <b>UI</b>
     * @return The <b>corresponding</b> command to be executed
     * @see Command
     */
    public static Command parseCommand(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
        String commandFlag = matcher.group(Command).toLowerCase();
        String parameters = matcher.group(PARAMETERS_GROUP);

        try {
            switch (commandWord){
            case COMMAND_WORD_EDIT:
                if (commandFlag.equals(MODULE_PREFIX)) {
                    return prepareEditModuleCommand(parameters);
                } else if (commandFlag.equals(TASK_PREFIX)){
                    return prepareEditTaskModuleCommand(parameters);
                } else {
                    throw new InvalidParameterException();
                }
            case COMMAND_WORD_ADD:
                if (commandFlag.equals(MODULE_PREFIX)) {
                    return prepareAddCommand(parameters, MODULE);
                } else if (commandFlag.equals(TASK_PREFIX)){
                    return prepareAddCommand(parameters, TASK);
                } else {
                    throw new InvalidParameterException();
                }
            case COMMAND_WORD_DELETE:
                if (commandFlag.equals(MODULE_PREFIX)) {
                    return prepareDeleteCommand(parameters, MODULE);
                } else if (commandFlag.equals(TASK_PREFIX)){
                    return prepareDeleteCommand(parameters, TASK);
                } else {
                    throw new InvalidParameterException();
                }
            case COMMAND_WORD_DONE:
                return prepareDoneCommand(parameters);
            case COMMAND_WORD_LIST:
                return prepareListCommand(matcher.group(2));
            default:
                return null;
            }
        } catch (InvalidParameterException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        }
        System.out.println(commandWord + System.lineSeparator() + commandFlag + System.lineSeparator() + parameters);
        return null;
    }

    /**
     * Prepares the command to edit a module.
     *
     * @param parameters
     *  The parameters given by the user
     * @return
     *  The command to edit a module
     */
    protected Command prepareEditModuleCommand(String parameters)
            throws InvalidParameterException {
        Matcher matcher = EditModuleCommand.REGEX_FORMAT.matcher(parameters);
        validateParameters(parameters, matcher, MODULE_PREFIX);

        String oldModuleCode = matcher.group(IDENTIFIER_GROUP).trim();
        String newModuleCode = matcher.group(MODULE_GROUP).replace(MODULE_PREFIX, NONE).trim();

        String invalid = matcher.group(INVALID_GROUP).trim();
        if (!invalid.isEmpty()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, invalid, MESSAGE_CHECK_COMMAND_FORMAT, EditModuleCommand.FORMAT));
        }

        if (isNothingToEdit(newModuleCode)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_MODULE);
        }

        return new EditModuleCommand(oldModuleCode, newModuleCode);
    }


    protected Command prepareAddCommand(String parameters, Task.typeOfTask typeOfTask) throws InvalidParameterException { //enum of type , string name ,deadline
        Matcher matcher = TASK_DEADLINE_FORMAT.matcher(parameters);

        String AddedTask = matcher.group(TASK_NAME_GROUP).trim();
        String TaskDeadline = null;

        if (matcher.group(DATE_IDENTIFIER_GROUP).trim().equals(BY_PREFIX)) { // and its of the correct format
            TaskDeadline = matcher.group(DUE_DATE).trim();
        } else {
            throw new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, TaskDeadline, MESSAGE_CHECK_COMMAND_FORMAT, AddCommand.FORMAT));
        }

        if (isNothingToEdit(AddedTask)){
            return (typeOfTask == MODULE) ? new IncorrectCommand(MESSAGE_NO_ADD_MODULE) : new IncorrectCommand(MESSAGE_NO_ADD_TASK);
        }

        return new AddCommand(typeOfTask ,AddedTask, TaskDeadline);
    }


    /**
     * Validate the parameters given by the user for the respective command.
     *
     * @param parameters
     *  The parameters given by the user
     * @param matcher
     *  The matcher to match for attributes and check for validity
     * @param parameterPrefixes
     *  The prefixes used for the respective command
     * @throws InvalidParameterException
     *  If an invalid parameter is found in the parameters or the parameters do not match the expected format
     */
    private void validateParameters(String parameters, Matcher matcher, String... parameterPrefixes)
            throws InvalidParameterException {

        if (!matcher.matches()) {
            throw new InvalidParameterException();
        }
    }

    /**
     * Checks if there is anything to edit from the input given by the user.
     *
     * @param attributes
     *  The attributes to be edited
     * @return
     *  <code>TRUE</code> if there is nothing to edit, or <code>FALSE</code> otherwise
     */
    protected boolean isNothingToEdit(String... attributes) {
        for (String attribute : attributes) {
            if (!attribute.isEmpty()) {
                return false;
            }
        }
        return true;

    }
}
