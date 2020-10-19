package seedu.duke.command.timetable;

import seedu.duke.command.Command;

public abstract class TimeTableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String FORMAT = COMMAND_WORD + " <opt>";
}
