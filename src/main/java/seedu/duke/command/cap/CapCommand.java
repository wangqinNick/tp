package seedu.duke.command.cap;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;

import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.ui.TextUi;

import java.util.HashMap;

import static seedu.duke.util.Message.MESSAGE_CAP_DISPLAY;

public class CapCommand extends Command {
    private int totalMcTaken;
    private double currentCap;
    public static final String COMMAND_WORD = "cap";
    public static final String FORMAT = COMMAND_WORD + " <total_mc> <current_cap>";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP =   "Calculate your CAP."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: cap 20 4.5";

    public static double Cap = 0.0;

    public CapCommand(int totalMcTaken,double currentCap) {
        this.totalMcTaken = totalMcTaken;
        this.currentCap = currentCap;
    }

    private enum GradeSchematic {
        A_PLUS("A+",5.0),
        A("A",5.0),
        A_MINUS("A-",4.5),
        B_PLUS("B+",4.0),
        B("B",3.5),
        B_MINUS("B-",3.0),
        C_PLUS("C+",2.5),
        C("C",2.0),
        D_PLUS("D+",1.5),
        D("D",1.0),
        F("F",0.0);

        private String symbol;
        private final double value;

        GradeSchematic(String symbol, double value) {
            this.symbol = symbol;
            this.value = value;
        }
    }

    private double gradeConvert(String grade) {
        double score = 0.0;
        for (GradeSchematic g : GradeSchematic.values()) {
            if (grade.equals(g.symbol)) {
                score = g.value;
            }
        }
        return score;
    }

    private double calculateCap() {
        HashMap<String, Module> moduleList = ModuleManager.getModulesMap();
        double mcGrade;
        double sumMcGrade = 0;
        double sumMc = 0;
        for (String i : moduleList.keySet()) {
            sumMc += moduleList.get(i).getModuleCredit();
            mcGrade = moduleList.get(i).getModuleCredit()
                    * gradeConvert(moduleList.get(i).getModuleGrade());
            sumMcGrade += mcGrade;
        }
        Cap = ((currentCap * totalMcTaken) + sumMcGrade) / (sumMc + totalMcTaken);
        return Cap;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format("%s%.2f\n",MESSAGE_CAP_DISPLAY,calculateCap()));
    }
}
