package seedu.duke.command.timetable;

import seedu.duke.command.CommandResult;
import seedu.duke.data.TimeTableManager;

import static seedu.duke.util.Message.MESSAGE_TIMETABLE_RESET;

public class TimeTableResetCommand extends TimeTableCommand {
    @Override
    public CommandResult execute() {
        if (TimeTableManager.isInitialised()) {
            TimeTableManager.clearTimeTable();
        }
        TimeTableManager.initialiseTimetable();
        return new CommandResult(MESSAGE_TIMETABLE_RESET);
    }
}
