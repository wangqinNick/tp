package seedu.duke.command.timetable;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

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
    private Calendar now = Calendar.getInstance();

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
        // todo Module getter function with a Calender
        return now.get(Calendar.YEAR) == modCode.get(Calendar.YEAR)
                && now.get(Calendar.MONTH) == modCode.get(Calendar.MONTH)
                && now.get(Calendar.DATE) == modCode.get(Calendar.DATE);
    }

    private boolean isSameWeek(Module modCode) {
        // todo Module getter function with a Calender
        return now.get(Calendar.YEAR) == modCode.get(Calendar.YEAR)
                && now.get(Calendar.WEEK_OF_YEAR) == modCode.get(Calendar.WEEK_OF_YEAR);
    }

    public String dayTimeTable() {
        ArrayList<Module> dayModList = getDayModCodeList();
        String out = "Current Date and Time: " + now.getTime() + System.lineSeparator();
        for (Module module : dayModList) {
            // todo a function in module/modulemanager that gets the duration of the class
            out += module.getModuleCode() + ": " + module.getDuration() + System.lineSeparator();
        }
        return out;
    }

    public String weekTimeTable() {
        ArrayList<Module> weekModList = getWeekModCodeList();
        String out = "Current Date and Time: " + now.getTime() + System.lineSeparator();
        for (Module module : weekModList) {
            out += now.get(Calendar.DATE) + System.lineSeparator();
            // todo a function in module/modulemanager that gets the duration of the class
            out += module.getModuleCode() + ": " + module.getDuration() + System.lineSeparator();
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
