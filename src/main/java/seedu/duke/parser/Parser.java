package seedu.duke.parser;

import seedu.duke.command.cap.CapCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.delete.DeleteCommand;
import seedu.duke.command.done.DoneCommand;
import seedu.duke.command.edit.EditCommand;
import seedu.duke.command.grade.GradeCommand;
import seedu.duke.command.help.HelpCommand;
import seedu.duke.command.list.ListCommand;
import seedu.duke.command.misc.UndoCommand;
import seedu.duke.command.summary.SummaryCommand;
import seedu.duke.command.timetable.TimeTableCommand;
import seedu.duke.exception.InvalidMatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_COMMAND_WORD;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.duke.util.ExceptionMessage.MESSAGE_STRING_IN_NUMBER;
import static seedu.duke.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.duke.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {
    public enum TypeOfEntries {
        TASK, MODULE
    }

    /**
     * Used for initial separation of command word and args.
     */

    private static final String COMMAND_WORD_GROUP = "commandWord";
    protected static final String COMMAND_FLAG_GROUP = "commandFlag";
    private static final String PARAMETERS_GROUP = "parameters";

    private static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)" + "((?<parameters>.*)?)");

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
            String parameters = isMatcherNull(matcher.group(PARAMETERS_GROUP))
                    ? null : matcher.group(PARAMETERS_GROUP).trim();

            switch (commandWord) {
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case CapCommand.COMMAND_WORD:
                return CapCommandParser.prepareCapCommand(parameters);
            case GradeCommand.COMMAND_WORD:
                return GradeCommandParser.prepareGradeCommand(parameters);
            case UndoCommand.COMMAND_WORD:
                return new UndoCommand();
            case EditCommand.COMMAND_WORD:
                return EditCommandParser.getEditCommand(parameters);
            case AddCommand.COMMAND_WORD:
                return AddCommandParser.prepareAddCommand(parameters);
            case DeleteCommand.COMMAND_WORD:
                return DeleteCommandParser.getDeleteCommand(parameters);
            case DoneCommand.COMMAND_WORD:
                return DoneCommandParser.prepareDoneCommand(parameters);
            case ListCommand.COMMAND_WORD:
                return ListCommandParser.getListCommand(parameters); //command flag is the -t or -m
            case SummaryCommand.COMMAND_WORD:
                return new SummaryCommand();
            case TimeTableCommand.COMMAND_WORD:
                return TimeTableCommandParser.parseTimeTableCommand(parameters);
            case HelpCommand.COMMAND_WORD:
            default:
                return HelpCommandParser.prepareHelpCommand(parameters);
            }
        } catch (NumberFormatException e) {
            return new IncorrectCommand(MESSAGE_STRING_IN_NUMBER);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return new IncorrectCommand(MESSAGE_INVALID_PARAMETERS);
        } catch (NullPointerException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_WORD);
        } catch (InvalidMatchException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    protected static boolean isMatcherNull(String matcherTest) {
        return (matcherTest == null);
    }

    /**
     * Checks the input given by the user is empty.
     *
     * @param attributes
     *  The input from the user
     * @return
     *  <code>TRUE</code> if the input is empty, or <code>FALSE</code> otherwise
     */
    protected static boolean isEmptyParse(String... attributes) {
        for (String attribute : attributes) {
            if (!attribute.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the user input matches the REGEX format of the parser.
     *
     * @param matcher
     * the format to follow
     * @param parameters
     * the user input
     * @param format
     * the actual correct format if user input doesn't match
     */
    protected static void matcherMatches(Matcher matcher, String parameters, String format, String helpPrompt)
            throws InvalidMatchException {
        if (!matcher.matches()) {
            throw new InvalidMatchException(parameters,format,helpPrompt);
        }
    }
}
