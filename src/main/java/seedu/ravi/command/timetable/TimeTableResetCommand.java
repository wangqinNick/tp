package seedu.ravi.command.timetable;

import seedu.ravi.command.CommandResult;
import seedu.ravi.data.TimeTableManager;

import static seedu.ravi.util.Message.MESSAGE_TIMETABLE_RESET;

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
