package seedu.ravi.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.ravi.exception.DuplicateModuleException;
import seedu.ravi.exception.ModuleNotFoundException;
import seedu.ravi.exception.ModuleNotProvidedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModuleTest {
    static final String MOD_CODE_1 = "CS2113T";
    static final String MOD_CODE_2 = "CG2271";

    @BeforeEach
    void setupModObjects() throws DuplicateModuleException, ModuleNotProvidedException {
        ModuleManager.clearModules();
        ModuleManager.add(MOD_CODE_1);
        ModuleManager.add(MOD_CODE_2);
    }
    
    @Test
    void getModuleCount_isEquals2() {
        assertEquals(2, ModuleManager.getModCodeList().length);
    }

    @Test
    void getModule_isCorrect() throws ModuleNotFoundException {
        assertEquals(MOD_CODE_1, ModuleManager.getModule(MOD_CODE_1).getModuleCode());
        assertEquals(MOD_CODE_2, ModuleManager.getModule(MOD_CODE_2).getModuleCode());
    }

    @Test
    void check_moduleNotFoundException_isThrown() {
        assertThrows(ModuleNotFoundException.class,
                () -> ModuleManager.getModule("WHAT1010"));
    }

    @Test
    void editMod_getCode_equalsNewCode()
            throws DuplicateModuleException, ModuleNotProvidedException,
            ModuleNotFoundException {
        String newCode = "MA1512";
        ModuleManager.edit(newCode, MOD_CODE_1);

        assertEquals(newCode, ModuleManager.getModule(newCode).getModuleCode());
    }

    @Test
    void deleteTask_getTaskCount_isEquals0() throws ModuleNotFoundException {
        ModuleManager.delete(MOD_CODE_1);
        ModuleManager.delete(MOD_CODE_2);

        assertEquals(0, ModuleManager.getModCodeList().length);
        assertThrows(ModuleNotFoundException.class, () -> ModuleManager.getModule(MOD_CODE_1));
    }
}
