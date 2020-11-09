package seedu.ravi.command.help;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.add.AddCommand;
import seedu.ravi.command.cap.CapCommand;
import seedu.ravi.command.delete.DeleteCommand;
import seedu.ravi.command.done.DoneCommand;
import seedu.ravi.command.edit.EditCommand;
import seedu.ravi.command.grade.GradeCommand;
import seedu.ravi.command.list.ListCommand;
import seedu.ravi.command.misc.UndoCommand;
import seedu.ravi.command.summary.SummaryCommand;
import seedu.ravi.command.timetable.TimeTableCommand;
import seedu.ravi.ui.TextUi;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_HELP_COMMAND;

//@@author amalinasani
public class HelpCommand extends Command {
    private String commandType;
    public static final String COMMAND_WORD = "help";
    public static final String FORMAT = COMMAND_WORD + " [<command_word>]";
    public static final String HELP =   "View command information."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + FORMAT
                                        + "\n\t@|bold,blue,BG_BLACK Example usage:|@ help"
                                        + "\n\t               help add";

    public HelpCommand(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Prints help message.
     *
     * @return CommandResult containing help message for that command
     */
    @Override
    public CommandResult execute() {
        String output = TextUi.getCommandList();

        if (commandType == null) {
            return new CommandResult(output);
        }

        switch (commandType) {
        case AddCommand.COMMAND_WORD:
            output = AddCommand.HELP;
            break;
        case CapCommand.COMMAND_WORD:
            output = CapCommand.HELP;
            break;
        case DeleteCommand.COMMAND_WORD:
            output = DeleteCommand.HELP;
            break;
        case DoneCommand.COMMAND_WORD:
            output = DoneCommand.HELP;
            break;
        case EditCommand.COMMAND_WORD:
            output = EditCommand.HELP;
            break;
        case GradeCommand.COMMAND_WORD:
            output = GradeCommand.HELP;
            break;
        case HelpCommand.COMMAND_WORD:
            output = HelpCommand.HELP;
            break;
        case ListCommand.COMMAND_WORD:
            output = ListCommand.HELP;
            break;
        case UndoCommand.COMMAND_WORD:
            output = UndoCommand.HELP;
            break;
        case TimeTableCommand.COMMAND_WORD:
            output = TimeTableCommand.HELP;
            break;
        case SummaryCommand.COMMAND_WORD:
            output = SummaryCommand.HELP;
            break;
        default:
            output = MESSAGE_INVALID_HELP_COMMAND;
            return new CommandResult(output,true);
        }
        return new CommandResult(output);
    }
}