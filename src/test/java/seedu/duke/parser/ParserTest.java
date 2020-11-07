//@@author tobiasceg

package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.exception.InvalidMatchException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {
    private static final Pattern TEST_FORMAT = Pattern.compile("(?<firstArg>\\S+)");

    @Test
    void isEmptyParse_emptyString_returnsTrue() {
        assertTrue(new Parser().isEmptyParse(""));
        assertTrue(new Parser().isEmptyParse("", "", ""));
    }

    @Test
    void isEmptyParse_nonemptyString_returnsFalse() {
        assertFalse(new Parser().isEmptyParse(" "));
        assertFalse(new Parser().isEmptyParse("", "b", ""));
    }

    @Test
    void matcherMatches_InvalidMatchException_isThrown() {
        String userInput = "this mod is time consuming";
        Matcher matcher = TEST_FORMAT.matcher(userInput);
        assertThrows(InvalidMatchException.class,
                () -> Parser.matcherMatches(matcher, userInput, "", ""));
    }
}