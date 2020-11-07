//@@author tobiasceg
package seedu.duke.command.cap;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.ModuleManager;
import seedu.duke.exception.InvalidCapCalculatedException;
import seedu.duke.exception.ModuleNotFoundException;
import seedu.duke.ui.TextUi;

import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_CAP_ATTAINED;
import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
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
    private static final double SUMMCGRADE = 20;
    private static final double SUMMC = 4;


    /**
     * Constructor for CapCommand which checks if the totalMcTaken and currentCap is valid also.
     *
     * @param totalMcTaken
     * users total module credit attained
     * @param currentCap
     * users current cap score
     */
    public CapCommand(int totalMcTaken, double currentCap) {
        this.totalMcTaken = totalMcTaken;
        this.currentCap = currentCap;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Enum for the grading schematic of NUS.
     */
    private enum GradeSchematic {
        A_PLUS("A+", 5.0),
        A("A", 5.0),
        A_MINUS("A-", 4.5),
        B_PLUS("B+", 4.0),
        B("B", 3.5),
        B_MINUS("B-", 3.0),
        C_PLUS("C+", 2.5),
        C("C", 2.0),
        D_PLUS("D+", 1.5),
        D("D", 1.0),
        F("F", 0.0),
        CS("CS", -2.0),
        CU("CU", -3.0);

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
            score = -1.0;
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
     * @throws InvalidCapCalculatedException
     * When the attained cap is unimaginable
     * @throws ModuleNotFoundException
     * When the module called isn't found
     */
    private double calculateCap() throws InvalidCapCalculatedException, ModuleNotFoundException {
        String[] moduleList = ModuleManager.getModCodeList();
        double mcGrade = 0;
        double sumMcGrade = 0;
        double sumMc = 0;
        double gradeValue;
        int numberOfCsCuModules = 0;
        int numberOfModules = 0;
        for (String i : moduleList) {
            numberOfModules++;
            gradeValue = gradeConvert(ModuleManager.getModule(i).getModuleGrade());
            if (gradeValue == -1.0) {
                throw new InvalidCapCalculatedException();
            } else if (gradeValue >= 0) {
                sumMc += ModuleManager.getModule(i).getModuleCredit();
                mcGrade = ModuleManager.getModule(i).getModuleCredit()
                        * gradeValue;
                sumMcGrade += mcGrade;
            } else {
                numberOfCsCuModules++;
            }
        }
        if (numberOfCsCuModules == numberOfModules) {
            sumMcGrade = SUMMCGRADE;
            sumMc = SUMMC;
        }

        cap = ((currentCap * totalMcTaken) + sumMcGrade) / (sumMc + totalMcTaken);

        if (cap < 0 || cap > 5.0 || isNan(cap)) {
            throw new InvalidCapCalculatedException();
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
        } catch (InvalidCapCalculatedException e) {
            return new CommandResult(MESSAGE_INVALID_CAP_ATTAINED, true);
        } catch (ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND, true);
        }
    }
}
