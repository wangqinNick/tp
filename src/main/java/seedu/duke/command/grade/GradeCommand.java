package seedu.duke.command.grade;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.PromptType;
import seedu.duke.data.Module;
import seedu.duke.data.ModuleManager;
import seedu.duke.exception.InvalidGradeException;
import seedu.duke.ui.TextUi;

import static seedu.duke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.duke.util.ExceptionMessage.MESSAGE_INVALID_GRADE;
import static seedu.duke.util.Message.MESSAGE_GRADE_MODULE_SUCCESS;


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
     * @param grade
     * Grade attained by user for the specific module.
     */
    public GradeCommand(String moduleGraded, double moduleCredit, String grade) {
        this.moduleGraded = moduleGraded;
        this.moduleCredit = moduleCredit;
        this.grade = grade;
        setPromptType(PromptType.EDIT);
    }

    /**
     * Tests if the input grade by the user is valid grade.
     *
     * @param grade
     * grade input by user
     */
    private boolean testGrade(String grade) {
        String[] validGrades = {"A+","A","A-","B+","B-","B","C+","C","D+","D","F"};
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
     * @param moduleToBeGraded
     * The module to be graded by user.
     * @throws InvalidGradeException
     * If the grade isn't recognised by the NUS grading schematic
     */
    private void grade(Module moduleToBeGraded) throws InvalidGradeException, ModuleManager.ModuleNotFoundException {
        if (testGrade(grade)) {
            ModuleManager.grade(moduleToBeGraded,grade,moduleCredit);
        } else {
            throw new InvalidGradeException();
        }
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
            Module moduleToBeGraded = ModuleManager.getModule(moduleGraded);
            grade(moduleToBeGraded);
            return new CommandResult(MESSAGE_GRADE_MODULE_SUCCESS);
        } catch (InvalidGradeException e) {
            return new CommandResult(MESSAGE_INVALID_GRADE);
        } catch (ModuleManager.ModuleNotFoundException e) {
            return new CommandResult(MESSAGE_MODULE_NOT_FOUND);
        }
    }
}
