package seedu.duke.command.timetable;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.TimeTableManager;

import static seedu.duke.util.Message.MESSAGE_TIMETABLE_RESET;

//@@author amalinasani
public class TimeTableResetCommand extends Command {
    @Override
    public CommandResult execute() {
        if (TimeTableManager.isInitialised()) {
            TimeTableManager.clearTimeTable();
        }
        TimeTableManager.initialiseTimetable();
        return new CommandResult(MESSAGE_TIMETABLE_RESET);
    }
}
