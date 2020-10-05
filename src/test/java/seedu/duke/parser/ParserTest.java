package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseCommand() {
    }

    @Test
    void isNothingToEdit_emptyString_returnsTrue(){
        assertTrue(new Parser().isNothingToEdit(""));
        assertTrue(new Parser().isNothingToEdit("", ""));
        assertTrue(new Parser().isNothingToEdit("", "", ""));
    }
}