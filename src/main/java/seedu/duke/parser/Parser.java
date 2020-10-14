package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
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
import static seedu.duke.util.Message.MESSAGE_NO_ADD_MODULE;
import static seedu.duke.util.Message.MESSAGE_NO_ADD_TASK;
import static seedu.duke.util.Message.MESSAGE_NO_EDIT_TASK;

public class Parser {
    public enum TypeOfEntries {
        TASK, MODULE
    }

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)" + "((?<commandFlag>.*-\\S+)?)"  + "((?<parameters>.*)?)");

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
        Command command;
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        String commandWord = matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();

        try {
            if (commandWord.equals(COMMAND_WORD_BYE)) {
                return new ExitCommand();
            } else if (commandWord.equals(COMMAND_WORD_HELP)) {
                return new HelpCommand();
            } else {
                String commandFlag = matcher.group(COMMAND_FLAG_GROUP).toLowerCase().trim();
                String parameters = matcher.group(PARAMETERS_GROUP).trim();
                switch (commandWord) {
                case COMMAND_WORD_EDIT:
                    return getEditCommand(commandFlag, parameters);
                case COMMAND_WORD_ADD:
                    return getAddCommand(commandFlag, parameters);
                case COMMAND_WORD_DELETE:
                    return getDeleteCommand(commandFlag, parameters);
                case COMMAND_WORD_DONE:
                    return new DoneCommand(Integer.parseInt(parameters)-1); //parameters is the index
                case COMMAND_WORD_LIST:
                    return getListCommand(commandFlag); //command flag is the -t or -m
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

    private Command getListCommand(String commandFlag) {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return new ListCommand(TypeOfEntries.MODULE);
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new ListCommand(TypeOfEntries.TASK);
        } else {
            throw new InvalidParameterException();
        }
    }

    private DeleteCommand getDeleteCommand(String commandFlag, String parameters) throws NumberFormatException {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return new DeleteCommand(TypeOfEntries.MODULE, parameters); //parameter is module code
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return new DeleteCommand(TypeOfEntries.TASK, Integer.parseInt(parameters));//parameters is the index
        } else {
            throw new InvalidParameterException();
        }
    }

    private Command getAddCommand(String commandFlag, String parameters) {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return prepareAddCommand(parameters, TypeOfEntries.MODULE);
        } else if (commandFlag.equals(TASK_PREFIX)) {
            return prepareAddCommand(parameters, TypeOfEntries.TASK);
        } else {
            throw new InvalidParameterException();
        }
    }

    private Command getEditCommand(String commandFlag, String parameters) {
        if (commandFlag.equals(MODULE_PREFIX)) {
            return prepareEditModuleCommand(parameters);
        } else if (commandFlag.equals(TASK_PREFIX)) {
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
    protected Command prepareEditModuleCommand(String parameters) throws InvalidParameterException {
        Matcher matcher = TASK_DEADLINE_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    EditModuleCommand.FORMAT));
        }

        String oldModuleCode = matcher.group(TASK_NAME_GROUP).trim();
        String newModuleCode = "";
        String dashBy = matcher.group(DATE_IDENTIFIER_GROUP);

        if (dashBy != null) {
            newModuleCode = matcher.group(DUE_DATE).trim();
            if (newModuleCode.isEmpty()) { // -by is present but empty
                return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                        MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                        EditModuleCommand.FORMAT));
            }
        }

        return new EditModuleCommand(oldModuleCode, newModuleCode);
    }

    protected Command prepareEditTaskCommand(String parameters) throws InvalidParameterException,NumberFormatException {
        Matcher matcher = TASK_DEADLINE_FORMAT.matcher(parameters);
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format("%s%s\n\n%s%s\n",
                    MESSAGE_INVALID_COMMAND_FORMAT, parameters, MESSAGE_CHECK_COMMAND_FORMAT,
                    EditModuleCommand.FORMAT));
        }

        String stringTaskIndex = matcher.group(TASK_NAME_GROUP).trim();
        int taskIndex = Integer.parseInt(stringTaskIndex) - 1;
        String newTaskDescription = matcher.group(DUE_DATE).trim();

        if (isNothingToEdit(newTaskDescription)) {
            return new IncorrectCommand(MESSAGE_NO_EDIT_TASK);
        }

        return new EditTaskCommand(taskIndex,newTaskDescription);
    }


    protected Command prepareAddCommand(String parameters,TypeOfEntries typeOfTask) throws InvalidParameterException {
        Matcher matcher = TASK_DEADLINE_FORMAT.matcher(parameters);
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
            addedTask = parameters.trim();
        }

        // no task input by user
        if (isNothingToEdit(addedTask)) {
            return (typeOfTask == TypeOfEntries.MODULE)
                    ? new IncorrectCommand(MESSAGE_NO_ADD_MODULE)
                    : new IncorrectCommand(MESSAGE_NO_ADD_TASK);
        }

        return new AddCommand(typeOfTask, addedTask, taskDeadline);
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
