package seedu.duke.command.timetable;

import seedu.duke.command.Command;

public abstract class TimeTableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String FORMAT = COMMAND_WORD + " <opt>";
    public static final String TIMETABLE_LESSON_PARAMETER_USER_FORMAT  = FORMAT + " <module> <day> <start> "
            + "<end> <type> <repeat>";
    public static final String TIMETABLE_LESSON_DELETE_USER_FORMAT  = FORMAT + " <week> <day> <index>";
    public static final String HELP =   "Undo last action." +
                                        "\nFormat: " + COMMAND_WORD;
}
