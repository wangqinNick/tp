package seedu.duke.command.edit;

import org.junit.jupiter.api.Test;
import seedu.duke.Executor;
import seedu.duke.command.CommandResult;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.data.Task;
import seedu.duke.data.TaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.util.ExceptionMessage.MESSAGE_DATE_TIME_UNKNOWN;
import static seedu.duke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;
import static seedu.duke.util.ExceptionMessage.MESSAGE_NO_EDIT_TASK;

public class EditTaskCommandTest {

    @Test
    void execute() {
        TaskManager.clear();
        Task newTask = new Task("read a book");
        TaskManager.add(newTask);

        //base case
        CommandResult result1 = Executor.executeCommand("edit -t 1 return a book");
        assertEquals(MESSAGE_EDIT_TASK_SUCCESS, result1.feedbackToUser);

        //invalid parameters
        CommandResult result2 = Executor.executeCommand("edit -t 0 return a book");
        assertEquals(MESSAGE_NO_EDIT_TASK, result2.feedbackToUser);
        CommandResult result3 = Executor.executeCommand("edit -t 2 return a book");
        assertEquals(MESSAGE_NO_EDIT_TASK, result3.feedbackToUser);
    }

    @Test
    void execute_withDeadline() {
        TaskManager.clear();
        Task newTask = new Task("read a book");
        TaskManager.add(newTask);

        //base case
        CommandResult result1 = Executor.executeCommand("edit -t 1 return a book -by 30/12/2020 1200");
        assertEquals(MESSAGE_EDIT_TASK_SUCCESS, result1.feedbackToUser);

        //invalid parameters
        CommandResult result2 = Executor.executeCommand("edit -t 0 return a book");
        assertEquals(MESSAGE_NO_EDIT_TASK, result2.feedbackToUser);
        CommandResult result3 = Executor.executeCommand("edit -t 2 return a book");
        assertEquals(MESSAGE_NO_EDIT_TASK, result3.feedbackToUser);
        CommandResult result4 = Executor.executeCommand("edit -t 1 return a book -by 3131313");

        String dateErrorResult = new IncorrectCommand(MESSAGE_DATE_TIME_UNKNOWN).execute().feedbackToUser;
        assertEquals(dateErrorResult, result4.feedbackToUser);
    }
}