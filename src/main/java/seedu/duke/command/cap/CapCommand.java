package seedu.duke.command.cap;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;

import seedu.duke.data.ModuleManager;

import static seedu.duke.util.Message.MESSAGE_CAP_DISPLAY;

public class CapCommand extends Command {
    public static final String COMMAND_WORD = "cap";
    public static final String FORMAT = COMMAND_WORD;

    public static double Cap = 0.0;

    private double gradeConvert(String grade) {
        switch (grade) {
        case "A+":
        case "A":
            return 5.0;
        case "A-":
            return 4.5;
        case "B+":
            return 4.0;
        case "B":
            return 3.5;
        case "B-":
            return 3.0;
        case "C+":
            return 2.5;
        case "C":
            return 2.0;
        case "D+":
            return 1.5;
        case "D":
            return 1.0;
        case "F":
        default:
            return 0.0;
        }
    }

    private double calculateCap() {
        double mcGrade;
        double sumMcGrade = 0;
        double sumMc = 0;
        for (String i : ModuleManager.getModulesMap().keySet()) {
            sumMc += ModuleManager.getModulesMap().get(i).getModuleCredit();
            mcGrade = ModuleManager.getModulesMap().get(i).getModuleCredit()
                    * gradeConvert(ModuleManager.getModulesMap().get(i).getModuleGrade());
            sumMcGrade += mcGrade;
        }
        Cap = sumMcGrade / sumMc;
        return Cap;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format("%s%.2f\n",MESSAGE_CAP_DISPLAY,calculateCap()));
    }
}
