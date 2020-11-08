//@@author tobiasceg

package seedu.ravi.command.grade;

import seedu.ravi.command.Command;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.PromptType;
import seedu.ravi.data.Module;
import seedu.ravi.data.ModuleManager;
import seedu.ravi.exception.InvalidGradeException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.ui.TextUi;

import static seedu.ravi.util.ExceptionMessage.MESSAGE_INVALID_GRADE;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.ravi.util.Message.MESSAGE_GRADE_MODULE_SUCCESS;


public class GradeCommand extends Command {
    private final String moduleGraded;
    private final double moduleCredit;
    private final String grade;
    public static final String COMMAND_WORD = "grade";
    public static final String FORMAT = COMMAND_WORD + " <module_code> <modular_credit> <grade>";
    public static final String PROMPT_HELP = TextUi.getCommandHelpMessage(COMMAND_WORD);
    public static final String HELP =   "Grades and allocates the Module Credit to the Module."
                                        + "\n\tFormat: " + FORMAT
                                        + "\n\tExample usage: grade CS2113T 4 A+";

    /**
     * Constructs GradeCommand.
     *
     * @param moduleGraded
     * Module to be graded.
     * @param moduleCredit
     * Modular credits of the module to be graded.
     * @param grade
     * Grade attained by user for the specific module.
     */
    public GradeCommand(String moduleGraded, double moduleCredit, String grade) {
        this.moduleGraded = moduleGraded.toUpperCase();
        this.moduleCredit = moduleCredit;
        this.grade = grade.toUpperCase();
        setPromptType(PromptType.EDIT);
    }

    /**
     * Tests if the input grade by the user is valid grade.
     *
     * @param grade
     * grade input by user
     */
    private boolean testGrade(String grade) {
        String[] validGrades = {"A+", "A", "A-", "B+", "B-", "B", "C+", "C", "D+", "D", "F", "CS", "CU"};
        for (String i: validGrades) {
            if (grade.equals(i)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Grades and allocates the Module Credit to the Module.
     *
     * @throws InvalidGradeException
     * If the grade isn't recognised by the NUS grading schematic
     */
    private Module grade() throws InvalidGradeException, ModuleNotFoundException {
        if (testGrade(grade)) {
            ModuleManager.grade(moduleGraded, grade, moduleCredit);
        } else {
            throw new InvalidGradeException();
        }
        return ModuleManager.getModule(moduleGraded);
    }

    /**
     * Executes the Grade Module Command to grade a Module with the module code
     * from the Module List.
     *
     * @return The Command Result of the execution
     */
    @Override
    public CommandResult execute() {
        try {
            Module moduleGraded = grade();
            return new CommandResult(String.format(
                    MESSAGE_GRADE_MODULE_SUCCESS, moduleGraded.toString()));
        } catch (InvalidGradeException e) {
            return new CommandResult(MESSAGE_INVALID_GRADE, true);
        } catch (ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND, true);
        }
    }
}
