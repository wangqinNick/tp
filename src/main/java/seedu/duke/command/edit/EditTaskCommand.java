package seedu.duke.command.edit;

import seedu.duke.command.CommandResult;
import seedu.duke.directory.Directory;
import seedu.duke.exception.DuplicateDataException;
import seedu.duke.exception.ModuleNotProvidedException;

public class EditTaskCommand extends EditCommand{

    public EditTaskCommand(int taskIndex,String newTaskDescription){

    }

    @Override
    protected void edit(Directory toEdit) throws ModuleNotProvidedException, DuplicateDataException {

    }

    @Override
    public CommandResult execute() {
        return null;
    }
}
