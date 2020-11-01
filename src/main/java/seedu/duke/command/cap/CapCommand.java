package seedu.duke.command.cap;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.exception.InvalidCapAttained;
import seedu.duke.ui.TextUi;

import java.util.HashMap;

import static seedu.duke.ui.TextUi.MAX_WIDTH;
import static seedu.duke.ui.TextUi.centerString;
import static seedu.duke.util.ExceptionMessage.EXCEPTION_HEADER;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_CAP_ATTAINED;
import static seedu.duke.util.Message.MESSAGE_CAP_DISPLAY;

public class CapCommand extends Command {
    private final int totalMcTaken;
    private final double currentCap;
    public static final String COMMAND_WORD = "cap";
    public static final String FORMAT = COMMAND_WORD + " <total_mc> <current_cap>";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP =   "Calculate your CAP."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: cap 20 4.5";

    public static double cap = 0.0;

    /**
     * Constructor for CapCommand which checks if the totalMcTaken and currentCap is valid also.
     *
     * @param totalMcTaken
     * users total module credit attained
     * @param currentCap
     * users current cap score
     */
    public CapCommand(int totalMcTaken,double currentCap) {
        this.totalMcTaken = totalMcTaken;
        this.currentCap = currentCap;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Enum for the grading schematic of NUS.
     */
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

        private final String symbol;
        private final double value;

        GradeSchematic(String symbol, double value) {
            this.symbol = symbol;
            this.value = value;
        }
    }

    /**
     * Converts the grade assigned to a module the value by using the enum.
     *
     * @param grade
     * grade assigned to module
     * @return
     * the value of the grade
     */
    private double gradeConvert(String grade) {
        double score = 0.0;
        if (grade.equals("No grade yet")) {
            return score;
        }
        for (GradeSchematic g : GradeSchematic.values()) {
            if (grade.equals(g.symbol)) {
                score = g.value;
            }
        }
        return score;
    }

    /**
     * Calculates the cap of the user.
     *
     * @return
     * cap of the user
     * @throws InvalidCapAttained
     * When the attained cap is unimaginable
     */
    private double calculateCap() throws InvalidCapAttained {
        HashMap<String, Module> moduleList = ModuleManager.getModulesMap();
        double mcGrade;
        double sumMcGrade = 0;
        double sumMc = 0;
        double gradeValue = 0;
        for (String i : moduleList.keySet()) {
            gradeValue = gradeConvert(moduleList.get(i).getModuleGrade());
            if (gradeValue == 0) {
                throw new InvalidCapAttained();
            }
            sumMc += moduleList.get(i).getModuleCredit();
            mcGrade = moduleList.get(i).getModuleCredit()
                    * gradeValue;
            sumMcGrade += mcGrade;
        }
        cap = ((currentCap * totalMcTaken) + sumMcGrade) / (sumMc + totalMcTaken);
        if (cap < 0 || cap > 5.0 || isNan(cap)) {
            throw new InvalidCapAttained();
        }
        return cap;
    }

    private boolean isNan(double cap) {
        return cap != cap;
    }

    @Override
    public CommandResult execute() {
        try {
            return new CommandResult(String.format("%s%.2f\n", MESSAGE_CAP_DISPLAY, calculateCap()));
        } catch (InvalidCapAttained e) {
            return new CommandResult(centerString(MAX_WIDTH, EXCEPTION_HEADER) + "\n" + MESSAGE_INVALID_CAP_ATTAINED);
        }
    }
}
