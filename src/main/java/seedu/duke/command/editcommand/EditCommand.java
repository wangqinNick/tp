package seedu.duke.command.editcommand;

import seedu.duke.command.Command;
import seedu.duke.directory.Directory;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;

public abstract class EditCommand extends Command {
    protected abstract void edit(Directory toEdit) throws ModuleNotProvidedException, DuplicateDataException;
}
