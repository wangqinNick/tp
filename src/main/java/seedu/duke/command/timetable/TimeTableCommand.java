package seedu.duke.command.timetable;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static seedu.duke.ui.TextUi.DIVIDER_LINE;

public class TimeTableCommand extends Command {
    public static final String COMMAND_WORD = "timetable";
    public static final String FORMAT = COMMAND_WORD + " <opt>";
    // todo add code into ModuleManager to get list of Module objects
    private static ArrayList<Module> modCodeList = ModuleManager.getModCodeListAsModule();
    public enum TypeOfTimetables {
        DAY, WEEK
    }
    private final TypeOfTimetables flag;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
    private LocalDateTime now = LocalDateTime.now();

    /**
     * Option for timetable for the day or for the week
     *
     * @param flag Determines if the day or week timetable is shown, -d or -m
     */
    public TimeTableCommand (String flag) {
        this.flag = TypeOfTimetables.valueOf(flag);
    }

    // Get a ArrayList of Module Objects for the day
    public ArrayList<Module> getDayModCodeList() {
        ArrayList<Module> dayModList = new ArrayList<>();
        // Get the module object
        for (Module modCode : modCodeList) {
            // Same day as today
            if (isSameDayAsToday(modCode)) {
                dayModList.add(modCode);
            }
        }
        return dayModList;
    }

    // Get a ArrayList of Module Objects for the day
    public ArrayList<Module> getWeekModCodeList() {
        ArrayList<Module> weekModList = new ArrayList<>();
        // Get the module object
        for (Module modCode : modCodeList) {
            // Same day as today
            if (isSameWeek(modCode)) {
                weekModList.add(modCode);
            }
        }
        return weekModList;
    }

    private boolean isSameDayAsToday(Module modCode) {
        // todo Module toLocalDate function with a LocalDateTime
        return now.toLocalDate().isEqual(modCode.toLocalDate());
    }

    private boolean isSameWeek(Module modCode) {
        // todo Module toLocalDate function with a LocalDateTime
        return (now.isBefore(modCode.toLocalDateTime()) && now.plusDays(6).isAfter(modCode.toLocalDateTime()));
    }

    public String dayTimeTable() {
        ArrayList<Module> dayModList = getDayModCodeList();
        String out = now.getDayOfWeek() + System.lineSeparator();
        for (Module module : dayModList) {
            // todo a function in module/modulemanager that gets the duration of the class
            ArrayList<LocalDateTime> periods = module.getPeriods();
            for (LocalDateTime period : periods) {
                out += module.getModuleCode() + ": " + period.format(timeFormatter) + System.lineSeparator();
            }
        }
        return out;
    }

    public String weekTimeTable() {
        ArrayList<Module> weekModList = getWeekModCodeList();
        String out = "";
        for (Module module : weekModList) {
            // todo a function in module/modulemanager that gets the duration of the class
            ArrayList<LocalDateTime> periods = module.getPeriods();
            out += now.getDayOfWeek() + System.lineSeparator();
            out += DIVIDER_LINE + System.lineSeparator();
            for (LocalDateTime period : periods) {
                out += module.getModuleCode() + ": " + period.format(timeFormatter) + System.lineSeparator();
            }
        }
        return out;
    }

    @Override
    public CommandResult execute() {
        if (flag.equals(TypeOfTimetables.DAY)) {
            return new CommandResult(dayTimeTable());
        } else if (flag.equals(TypeOfTimetables.WEEK)) {
            return new CommandResult(weekTimeTable());
        }
    }
}
