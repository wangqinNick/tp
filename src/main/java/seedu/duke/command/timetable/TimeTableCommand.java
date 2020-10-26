package seedu.duke.command.timetable;

import seedu.duke.command.Command;
import seedu.duke.ui.TextUi;

public abstract class TimeTableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String TIMETABLE_DAY_VIEW_FORMAT = COMMAND_WORD + " -day";
    public static final String TIMETABLE_WEEK_VIEW_FORMAT = COMMAND_WORD + " -week";
    public static final String TIMETABLE_LESSON_PARAMETER_USER_FORMAT  = COMMAND_WORD
            + " -add <module_code> <day> <start_time> "
            + "<end_time> <lesson_type> <repeat>";
    public static final String TIMETABLE_LESSON_DELETE_USER_FORMAT  = COMMAND_WORD + " -del <day> <lesson index>";
    public static final String FORMAT = TIMETABLE_LESSON_PARAMETER_USER_FORMAT
            + "\n" + TIMETABLE_LESSON_DELETE_USER_FORMAT;
    public static final String HELP =   "View day's timetable"
           + "\n\tFormat: " + TIMETABLE_DAY_VIEW_FORMAT
           + "\n\nView week's timetable"
           + "\n\tFormat: " + TIMETABLE_WEEK_VIEW_FORMAT
           + "\n\nAdd a lesson to the timetable."
           + "\n\tFormat: " + TIMETABLE_LESSON_PARAMETER_USER_FORMAT
           + "\n\tExample usage: timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1"
           + "\n\nRemove a lesson from the timetable."
           + "\n\tFormat: " + TIMETABLE_LESSON_DELETE_USER_FORMAT
           + "\n\tExample usage: timetable -del MONDAY 2";

    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
}
