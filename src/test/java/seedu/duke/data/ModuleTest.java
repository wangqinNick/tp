package seedu.duke.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.ModuleNotProvidedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModuleTest {
    static Module normalMod1, normalMod2;
    final static String MOD_CODE_1 = "CS2113T";
    final static String MOD_CODE_2 = "CG2271";

    @BeforeEach
    void setupModObjects() throws ModuleManager.DuplicateModuleException {
        normalMod1 = new Module(MOD_CODE_1);
        normalMod1.setTitle("Test");
        normalMod2 = new Module(MOD_CODE_2);
        normalMod1.setTitle("Test 2");
        try {
            ModuleManager.clearModules();
            ModuleManager.add(normalMod1);
            ModuleManager.add(normalMod2);
        } catch (ModuleManager.DuplicateModuleException e) {
            // do nothing
        }
    }

    @Test
    void getModuleCount_isEquals2() {
        assertEquals(2, ModuleManager.getModCodeList().length);
    }

    @Test
    void getModule_isCorrect() throws ModuleManager.ModuleNotFoundException {
        assertEquals(normalMod1.getTitle(), ModuleManager.getModule(MOD_CODE_1).getTitle());
        assertEquals(normalMod1.getCode(), ModuleManager.getModule(MOD_CODE_1).getCode());
        assertEquals(normalMod2.getTitle(), ModuleManager.getModule(MOD_CODE_2).getTitle());
        assertEquals(normalMod2.getCode(), ModuleManager.getModule(MOD_CODE_2).getCode());
    }

    @Test
    void check_moduleNotFoundException_isThrown() {
        assertThrows(ModuleManager.ModuleNotFoundException.class,
                () -> ModuleManager.getModule("WHAT1010"));
    }

    @Test
    void editMod_getTitle_equalsNewTitle()
            throws ModuleManager.DuplicateModuleException, ModuleNotProvidedException,
            ModuleManager.ModuleNotFoundException {
        String newTitle = "NEW";
        String newCode = "CODE1";
        Module editedMod = new Module(newCode);
        editedMod.setTitle(newTitle);
        ModuleManager.edit(editedMod, MOD_CODE_1);

        assertEquals(newTitle, ModuleManager.getModule(newCode).getTitle());
    }

    @Test
    void deleteTask_getTaskCount_isEquals0() throws ModuleManager.ModuleNotFoundException {
        ModuleManager.delete(MOD_CODE_1);
        ModuleManager.delete(MOD_CODE_2);

        assertEquals(0, ModuleManager.getModCodeList().length);
        assertThrows(ModuleManager.ModuleNotFoundException.class, () -> ModuleManager.getModule(MOD_CODE_1));
    }
}
