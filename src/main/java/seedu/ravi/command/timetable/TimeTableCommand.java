package seedu.ravi.command.timetable;

import seedu.ravi.command.Command;
import seedu.ravi.ui.TextUi;

public abstract class TimeTableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String TIMETABLE_DAY_VIEW_FORMAT = COMMAND_WORD + " -day";
    public static final String TIMETABLE_WEEK_VIEW_FORMAT = COMMAND_WORD + " -week";
    public static final String TIMETABLE_LESSON_ADD_USER_FORMAT  = COMMAND_WORD
            + " -add <module_code> <day> <start_time>... "
            + "\n\t\t...<end_time> <lesson_type> <repeat>";
    public static final String TIMETABLE_LESSON_DELETE_USER_FORMAT  = COMMAND_WORD + " -del <day> <lesson_index>";
    public static final String TIMETABLE_LESSON_FILTER_USER_FORMAT  = COMMAND_WORD
            + " -filter <module_code> <day> <start_time>... "
            + "\n\t\t... <end_time> <lesson_type>";
    public static final String FORMAT = TIMETABLE_LESSON_ADD_USER_FORMAT
            + "\n" + TIMETABLE_LESSON_DELETE_USER_FORMAT
            + "\n" + TIMETABLE_DAY_VIEW_FORMAT
            + "\n" + TIMETABLE_WEEK_VIEW_FORMAT;
    public static final String HELP =
            "View day's timetable"
            + "\n\tFormat: " + TIMETABLE_DAY_VIEW_FORMAT
            + "\n\nView week's timetable"
            + "\n\tFormat: " + TIMETABLE_WEEK_VIEW_FORMAT
            + "\n\nAdd a lesson to the timetable."
            + "\n\tFormat: " + TIMETABLE_LESSON_ADD_USER_FORMAT
            + "\n\t<repeat> 0: Once"
            + "\n\t         1: Once a week"
            + "\n\t         2: Even weeks"
            + "\n\t         3: Odd weeks"
            + "\n\tExample usage: timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1"
            + "\n\nRemove a lesson from the timetable."
            + "\n\tFormat: " + TIMETABLE_LESSON_DELETE_USER_FORMAT
            + "\n\tExample usage: timetable -del MONDAY 2"
            + "\n\nFilter out lessons from the timetable based on certain criteria."
            + "\n\tUse '-' to signify no criteria for that property."
            + "\n\tFormat: " + TIMETABLE_LESSON_FILTER_USER_FORMAT
            + "\n\tExample usage:"
            + "\n\t\t- Any CS1010 lesson after 1000 on any day:"
            + "\n\t\t- timetable -filter CS1010 - 1000 - -"
            + "\n\t\t- Any lecture on Monday before 1200:"
            + "\n\t\t- timetable -filter - MONDAY - 1200 LECTURE";

    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
}
