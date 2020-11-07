//@@author tobiasceg

package seedu.ravi.parser;

import seedu.ravi.RaviLogger;
import seedu.ravi.command.Command;
import seedu.ravi.command.ExitCommand;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.command.add.AddCommand;
import seedu.ravi.command.cap.CapCommand;
import seedu.ravi.command.delete.DeleteCommand;
import seedu.ravi.command.done.DoneCommand;
import seedu.ravi.command.edit.EditCommand;
import seedu.ravi.command.grade.GradeCommand;
import seedu.ravi.command.help.HelpCommand;
import seedu.ravi.command.list.ListCommand;
import seedu.ravi.command.misc.UndoCommand;
import seedu.ravi.command.summary.SummaryCommand;
import seedu.ravi.command.timetable.TimeTableCommand;
import seedu.ravi.exception.InvalidCapException;
import seedu.ravi.exception.InvalidMatchException;
import seedu.ravi.exception.InvalidModuleCreditException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_CAP;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_COMMAND_WORD;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_MC;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_PARAMETERS;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_STRING_IN_NUMBER;
import static seedu.ravi.util.Message.MESSAGE_EMPTY_INPUT;
import static seedu.ravi.util.Message.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {
    private static final RaviLogger logger = new RaviLogger(Parser.class.getName());

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


    /**
     * Takes the user's full input and parses it, checking for the command word and assigning it to the relevant
     * prepare command.
     *
     * @param input
     * User's full input
     * @return
     * Relevant prepare command from the respective commands' parsers
     */
    public Command parseCommand(String input) {
        logger.getLogger().info("Received input: " + input);
        if (input.isBlank()) {
            return new IncorrectCommand(MESSAGE_EMPTY_INPUT);
        }

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {
            logger.getLogger().warning("Invalid command format");
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_FORMAT);
        }

        String commandWord = "";
        String parameters = "";
        try {
            // Checks if the matched groups are null
            commandWord = isMatcherNull(matcher.group(COMMAND_WORD_GROUP))
                    ? null : matcher.group(COMMAND_WORD_GROUP).toLowerCase().trim();
            parameters = isMatcherNull(matcher.group(PARAMETERS_GROUP))
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
                logger.getLogger().info("Unrecognised or help command");
                return HelpCommandParser.prepareHelpCommand(parameters);
            }
        } catch (NumberFormatException e) {
            logger.getLogger().warning("Found a string where a number should be");
            return new IncorrectCommand(String.format(MESSAGE_STRING_IN_NUMBER, commandWord));
        } catch (IllegalStateException | IllegalArgumentException e) {
            logger.getLogger().warning("Invalid parameters for the command");
            return new IncorrectCommand(String.format(MESSAGE_INVALID_PARAMETERS, commandWord));
        } catch (NullPointerException e) {
            logger.getLogger().warning("Invalid command word");
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND_WORD);
        } catch (InvalidMatchException e) {
            logger.getLogger().warning(e.getMessage());
            return new IncorrectCommand(e.getMessage());
        } catch (InvalidModuleCreditException e) {
            logger.getLogger().warning("Invalid Module Credit input");
            return new IncorrectCommand(MESSAGE_INVALID_MC);
        } catch (InvalidCapException e) {
            logger.getLogger().warning("Invalid Cap detected");
            return new IncorrectCommand(MESSAGE_INVALID_CAP);
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
     *  true if the input is empty, or false otherwise
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
            throw new InvalidMatchException(parameters, format, helpPrompt);
        }
    }
}
