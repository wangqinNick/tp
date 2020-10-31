package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.cap.CapCommand;
import seedu.duke.command.delete.DeleteCommand;
import seedu.duke.command.done.DoneCommand;
import seedu.duke.command.edit.EditCommand;
import seedu.duke.command.grade.GradeCommand;
import seedu.duke.command.list.ListCommand;
import seedu.duke.command.misc.UndoCommand;
import seedu.duke.command.summary.SummaryCommand;
import seedu.duke.command.timetable.TimeTableCommand;
import seedu.duke.ui.TextUi;

public class HelpCommand extends Command {
    private final String commandType;
    public static final String COMMAND_WORD = "help";
    public static final String FORMAT = COMMAND_WORD + " [<command_word>]";
    public static final String HELP =   "View command information."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: help"
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
        String output;
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
            output = TextUi.getCommandList();
            break;
        }
        return new CommandResult(output);
    }
}