package seedu.duke.directory;

import seedu.duke.data.ModuleManager;
import seedu.duke.exception.DataNotFoundException;
import seedu.duke.exception.DirectoryTraversalOutOfBoundsException;
import seedu.duke.exception.IncorrectDirectoryLevelException;

import java.util.Stack;

public class DirectoryTraverser {
    private static Stack<Directory> directoryStack = new Stack<>();
    private static final DirectoryLevel[] DIRECTORY_LEVELS = {
            DirectoryLevel.ROOT, DirectoryLevel.MODULE, DirectoryLevel.TASK
    };

    private static int currentLevel = 0; //root
    private static final int MINIMUM_LEVEL = 0;
    private static final int MAXIMUM_LEVEL = 2; //task
    private static final int ROOT_LEVEL = 0;
    private static final int MODULE_LEVEL = 1;
    private static final int TASK_LEVEL = 2;

    public static Directory getCurrentDirectory() {
        return directoryStack.isEmpty() ? new Root() : directoryStack.peek();
    }

    public static DirectoryLevel getCurrentDirectoryLevel() {
        return DIRECTORY_LEVELS[currentLevel];
    }
    public static void setCurrentLevelToRoot() {
        currentLevel = 0;
    }
    public static void traverseDown(Directory nextDirectory) throws DirectoryTraversalOutOfBoundsException {
        if (currentLevel >= MAXIMUM_LEVEL) {
            throw new DirectoryTraversalOutOfBoundsException();
        }
        currentLevel++;
        directoryStack.push(nextDirectory);
    }

    public static void traverseUp() throws DirectoryTraversalOutOfBoundsException {
        if (currentLevel <= MINIMUM_LEVEL) {
            throw new DirectoryTraversalOutOfBoundsException();
        }
        currentLevel--;
        directoryStack.pop();
    }

    public static void traverseTo(Directory toTraverse) {
        // Clear the stack
        directoryStack.empty();

        // Fill up the stack with parent directories
        if (toTraverse instanceof Root) {
            currentLevel = ROOT_LEVEL;
        } else if (toTraverse instanceof Module) {
            directoryStack.push(toTraverse);
            currentLevel = MODULE_LEVEL;
        } else if (toTraverse instanceof Task) {
            directoryStack.push(toTraverse.getParent().getParent());
            directoryStack.push(toTraverse.getParent());
            directoryStack.push(toTraverse);
            currentLevel = TASK_LEVEL;
        }
    }

    public static Directory findNextDirectory(String nextDirectoryName)
            throws DataNotFoundException, DirectoryTraversalOutOfBoundsException {
        switch (getCurrentDirectoryLevel()) {
        case ROOT:
            return ModuleManager.getModule(nextDirectoryName);
        case MODULE:
            return ((Module) getCurrentDirectory()).getTasks().getTask(nextDirectoryName);
        default:
            throw new DirectoryTraversalOutOfBoundsException();
        }
    }

    public static String getFullPath() {
        StringBuilder path = new StringBuilder("root");

        switch (getCurrentDirectoryLevel()) {
        case MODULE:
            Module currentModuleDirectory = (Module) getCurrentDirectory();
            path.append(String.format(" / %s", currentModuleDirectory.getModuleCode()));
            break;

        case TASK:
            Task currentTaskDirectory = (Task) getCurrentDirectory();
            path.append(String.format(" / %s / %s",
                    currentTaskDirectory.getParent().getModuleCode(),
                    currentTaskDirectory.getDescription()));
            break;

        default:
            break;
        }

        return path.toString();
    }

    public static Module getBaseModule() throws IncorrectDirectoryLevelException {
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
        case MODULE:
            return (Module) DirectoryTraverser.getCurrentDirectory();
        case TASK:
            return (Module) DirectoryTraverser.getCurrentDirectory().getParent().getParent();
        default:
            throw new IncorrectDirectoryLevelException();
        }
    }

    public static Task getBaseTask() throws IncorrectDirectoryLevelException {
        switch (DirectoryTraverser.getCurrentDirectoryLevel()) {
        case TASK:
            return (Task) DirectoryTraverser.getCurrentDirectory();
        default:
            throw new IncorrectDirectoryLevelException();
        }
    }

    /**
     * Returns the module level directory of the current Directory.
     *
     * @return
     *  The module level directory of the current Directory
     * @throws IncorrectDirectoryLevelException
     *  If the current directory is too low to obtain the module level directory
     * @throws ModuleManager.ModuleNotFoundException
     *  If the module with the module code is not found in the Module List
     */
    public static Module getModuleDirectory(String moduleCode)
            throws IncorrectDirectoryLevelException, ModuleManager.ModuleNotFoundException {
        // Fill up missing values first
        if (moduleCode.isEmpty()) {
            return getBaseModule();
        } else {
            return ModuleManager.getModule(moduleCode);
        }
    }
}
