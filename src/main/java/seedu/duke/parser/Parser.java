package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.delete.DeleteCommand;
import seedu.duke.command.done.DoneCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;
import seedu.duke.command.help.HelpCommand;
import seedu.duke.command.list.ListCommand;

import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_COMMAND_WORD;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.Message.MESSAGE_CHECK_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.duke.util.Message.MESSAGE_NO_EDIT_TASK;

public class Parser {
    public enum TypeOfEntries {
        TASK, MODULE
    }

    /**
     * Used for initial separation of command word and args.
     */
//    private static final Pattern BASIC_COMMAND_FORMAT =
//            Pattern.compile("(?<commandWord>\\S+)" + "((?<digit>\\s+\\d+)?)"
//                    + "((?<commandFlag>.*-\\S+)?)"  + "((?<parameters>.*)?)");



    private static final String COMMAND_WORD_GROUP = "commandWord";
    private static final String COMMAND_FLAG_GROUP = "commandFlag";
    private static final String PARAMETERS_GROUP = "parameters";
    private static final String NUMBER_GROUP = "digit";
    private static final String TASK_NAME_GROUP = "taskName";
    private static final String MODULE_GROUP = "moduleCode";
    private static final String DATE_IDENTIFIER_GROUP = "by";
    private static final String DUE_DATE_GROUP = "dueDate";

    public static final String MODULE_PREFIX = "-m";
    public static final String TASK_PREFIX = "-t";
    public static final String BY_PREFIX = "-by";

    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)" + "((?<parameters>.*)?)");

    private static final Pattern PARAMETERS_FORMAT =
            Pattern.compile("(?<taskName>\\S+)((?<by>.*" + BY_PREFIX + ")?)((?<dueDate>.*)?)");

    public static final String COMMAND_WORD_EDIT = "edit";
    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_WORD_LIST = "list";
    public static final String COMMAND_WORD_DONE = "done";
    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_WORD_BYE = "bye";

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
    public Command parseCommand(String input) {
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        try {
            // Checks if the matched groups are null
            String commandWord = isMatcherNull(matcher.group(COMMAND_WORD_GROUP))
                    ? null : matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
            String digit = isMatcherNull(matcher.group(NUMBER_GROUP))
                    ? null : matcher.group(NUMBER_GROUP).trim();
            String commandFlag = isMatcherNull(matcher.group(COMMAND_FLAG_GROUP))
                    ? null : matcher.group(COMMAND_FLAG_GROUP).toLowerCase().trim();
            String parameters = isMatcherNull(matcher.group(PARAMETERS_GROUP))
                    ? null : matcher.group(PARAMETERS_GROUP).trim();

            if (commandWord.equals(COMMAND_WORD_BYE)) {
                return new ExitCommand();
            } else if (commandWord.equals(COMMAND_WORD_HELP)) {
                return new HelpCommand();
            } else {
                switch (commandWord) {
                case COMMAND_WORD_EDIT:
                    return EditCommandParser.getEditCommand(commandFlag, parameters);
                case COMMAND_WORD_ADD:
                    return AddCommandParser.getAddCommand(commandFlag, parameters);
                case COMMAND_WORD_DELETE:
                    return DeleteCommandParser.getDeleteCommand(commandFlag, parameters);
                case COMMAND_WORD_DONE:
                    return new DoneCommand(Integer.parseInt(digit));
                case COMMAND_WORD_LIST:
                    return ListCommandParser.getListCommand(commandFlag); //command flag is the -t or -m
                default:
                    return new HelpCommand();
                }
            }
        } catch (InvalidParameterException | NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        } catch (NullPointerException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_WORD);
        }
    }

    private boolean isMatcherNull(String matcherTest) {
        return (matcherTest == null);
    }

    /**
     * Checks if there is anything to edit from the input given by the user.
     *
     * @param attributes
     *  The attributes to be edited
     * @return
     *  <code>TRUE</code> if there is nothing to edit, or <code>FALSE</code> otherwise
     */
    protected boolean isEmptyParse(String... attributes) {
        for (String attribute : attributes) {
            if (!attribute.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
