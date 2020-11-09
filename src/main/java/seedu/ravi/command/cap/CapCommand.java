//@@author tobiasceg

package seedu.ravi.command.cap;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.exception.InvalidCapCalculatedException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.ui.TextUi;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_CAP_ATTAINED;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.ravi.util.Message.MESSAGE_CAP_DISPLAY;

public class CapCommand extends Command {
    public static final String COMMAND_WORD = "cap";
    public static final String FORMAT = COMMAND_WORD + " <total_mc> <current_cap>";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP =   "Calculate your CAP."
                                        + "\n\t@|bold,blue,BG_BLACK Format:|@ " + FORMAT
                                        + "\n\t@|bold,blue,BG_BLACK Example usage:|@ cap 20 4.5";

    private static double cap = 0.0;
    private static final double SUMMCGRADE = 20;
    private static final double SUMMC = 4;

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
        double mcGrade;
        double sumMcGrade = 0;
        double sumMc = 0;
        double gradeValue;
        int numberOfCsCuModules = 0;
        int numberOfModules = 0;

        for (String i : moduleList) {
            numberOfModules++;
            gradeValue = gradeConvert(ModuleManager.getModule(i).getModuleGrade());

            if (gradeValue != -1.0) {
                if (gradeValue >= 0) {
                    sumMc += ModuleManager.getModule(i).getModuleCredit();
                    mcGrade = ModuleManager.getModule(i).getModuleCredit()
                            * gradeValue;
                    sumMcGrade += mcGrade;
                } else {
                    numberOfCsCuModules++;
                }
            }
        }
        if (numberOfCsCuModules == numberOfModules) {
            sumMcGrade = SUMMCGRADE;
            sumMc = SUMMC;
        }

        cap = sumMcGrade / sumMc;

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
