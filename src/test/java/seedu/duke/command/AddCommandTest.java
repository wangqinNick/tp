package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.command.add.AddCommand;
import seedu.duke.data.ModuleManager;
import seedu.duke.parser.Parser;
import seedu.duke.ui.TextUi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.duke.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;

class AddCommandTest {
    @Test
    void addModule_duplicateModule_throwsException() {
        Parser.TypeOfEntries typeOfEntry = Parser.TypeOfEntries.MODULE;
        final String[] moduleList = { "CS2113", "CS2040", "CG2007", "CS2113" };
        CommandResult commandResult = null;
        CommandResult expectedResult = new CommandResult(MESSAGE_DUPLICATE_MODULE);
        for (String module : moduleList) {
            AddCommand addDupModTest = new AddCommand(typeOfEntry, module, null);
            commandResult = addDupModTest.execute();
        }
        assertTrue(TextUi.showResultToUser(commandResult), TextUi.showResultToUser(expectedResult));
    }
}
