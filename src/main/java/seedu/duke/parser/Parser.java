package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.edit.EditModuleCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_NO_EDIT_MODULE;

public class Parser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<parameters>.*)");

    private static final String COMMAND_WORD_GROUP = "commandWord";
    private static final String PARAMETERS_GROUP = "parameters";
    private static final String IDENTIFIER_GROUP = "identifier";
    private static final String MODULE_GROUP = "moduleCode";

    private static final String NONE = "";
    private static final String INVALID_GROUP = "invalid";
    public static final String MODULE_PREFIX = "-m";

    //(?<identifier>(?:\s+\w\S*)*)+ -m+ (?<moduleCode>(?:\\s+" + "(?:\\s+\\w\\S*)+)?)(?<invalid>.*)

    /*
    public static String parseCommand(String userInput){
        switch (userInput){
        case "add":
            return addCommand(userInput);

        case "delete":
            return deleteCommand(userInput);

        case "list":
            return listCommand(userInput);

        case "edit":
            return editCommand(userInput);

        case "done":
            return doneCommand(userInput);

        case "bye":
            return byeCommand(userInput);

        default:
            return HelpCommand();
     */

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
    public Command parseCommand(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
        String parameters = matcher.group(PARAMETERS_GROUP);

        try {
            switch (commandWord){
            case EditModuleCommand.COMMAND_WORD:
                return prepareEditModuleCommand(parameters);
            default:
                return null;
            }
        } catch (InvalidParameterException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
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
