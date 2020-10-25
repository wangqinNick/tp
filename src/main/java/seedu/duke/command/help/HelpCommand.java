package seedu.duke.command.help;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.add.AddCommand;
import seedu.duke.command.add.AddModuleCommand;
import seedu.duke.command.add.AddTaskCommand;
import seedu.duke.command.delete.DeleteCommand;
import seedu.duke.command.delete.DeleteModuleCommand;
import seedu.duke.command.delete.DeleteTaskCommand;
import seedu.duke.command.done.DoneCommand;
import seedu.duke.command.edit.EditCommand;
import seedu.duke.command.edit.EditModuleCommand;
import seedu.duke.command.edit.EditTaskCommand;
import seedu.duke.command.list.ListCommand;
import seedu.duke.command.misc.UndoCommand;
import seedu.duke.command.summary.SummaryCommand;
import seedu.duke.ui.TextUi;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String FORMAT = COMMAND_WORD + " <command word>";
    public static final String HELP =   "View command information." +
                                        "\n\tFormat: " + FORMAT +
                                        "\n\tExample usage: help add\n";
    private String commandType;

    public HelpCommand(String commandType){
        this.commandType = commandType;
    }
    /**
     * Prints help message.
     *
     * @return CommandResult containing list of available commands
     */
    @Override
    public CommandResult execute() {
        String output = "";
        switch (commandType){
        case AddCommand.COMMAND_WORD:
            output = AddTaskCommand.HELP + AddModuleCommand.HELP;
            break;
        case DeleteCommand.COMMAND_WORD:
            output = DeleteTaskCommand.HELP + DeleteModuleCommand.HELP;
            break;
        case DoneCommand.COMMAND_WORD:
            output = DoneCommand.HELP;
            break;
        case EditCommand.COMMAND_WORD:
            output = EditTaskCommand.HELP + EditModuleCommand.HELP;
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
        case SummaryCommand.COMMAND_WORD:
            output = SummaryCommand.HELP;
            break;
        // TODO: Timetable commands
        default:
            output = TextUi.getHelpMessage();
            break;
        }
        return new CommandResult(output);
    }
}