package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.command.grade.GradeCommand;
import seedu.duke.exception.InvalidMatchException;
import seedu.duke.exception.InvalidModuleCreditException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GradeCommandParserTest {
    static final String VALID_GRADE_INPUT = "CG2271 4 A+";
    static final String INVALID_GRADE_INPUT_STRING_SWAP = "CG2271 B+ 4";
    static final String INVALID_GRADE_INPUT_STRING = "CG2271 four B+";
    static final String INVALID_GRADE_INPUT_EXCESS = "CG2271 4 A+ or B+ anything tbh";

    @Test
    void prepareGradeCommand_returnsGradeCommand() throws InvalidMatchException, InvalidModuleCreditException {
        assertTrue(GradeCommandParser.prepareGradeCommand(VALID_GRADE_INPUT)
            instanceof GradeCommand);
    }

    @Test
    void prepareGradeCommand_NumberFormatException_isThrown() {
        assertThrows(NumberFormatException.class,
            () -> GradeCommandParser.prepareGradeCommand(INVALID_GRADE_INPUT_STRING_SWAP));
        assertThrows(NumberFormatException.class,
            () -> GradeCommandParser.prepareGradeCommand(INVALID_GRADE_INPUT_STRING));
    }

    @Test
    void prepareGradeCommand_InvalidMatchException_isThrown() {
        assertThrows(InvalidMatchException.class,
            () -> GradeCommandParser.prepareGradeCommand(INVALID_GRADE_INPUT_EXCESS));
    }
}
