package seedu.ravi.command.edit;

import org.junit.jupiter.api.Test;
import seedu.ravi.Executor;
import seedu.ravi.command.CommandResult;
import seedu.ravi.command.IncorrectCommand;
import seedu.ravi.data.Task;
import seedu.ravi.data.TaskManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN;
import static seedu.ravi.util.Message.MESSAGE_EDIT_TASK_SUCCESS;
import static seedu.ravi.util.ExceptionMessage.MESSAGE_NO_EDIT_TASK;

public class EditTaskCommandTest {

    @Test
    void execute() {
        TaskManager.clear();
        Task newTask = new Task("read a book");
        TaskManager.add(newTask);
        String newTaskString = newTask.toString();

        Task editedTask = new Task("return a book");
        //base case
        CommandResult result1 = Executor.executeCommand("edit -t 1 return a book");
        assertEquals(String.format(MESSAGE_EDIT_TASK_SUCCESS, newTaskString, editedTask.toString()),
                result1.feedbackToUser);

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
        String newTaskString = newTask.toString();

        Task editedTask = new Task("return a book",
                LocalDateTime.of(2020, 12, 30, 12, 00));

        //base case
        CommandResult result1 = Executor.executeCommand("edit -t 1 return a book -by 30-12-2020 1200");
        assertEquals(String.format(MESSAGE_EDIT_TASK_SUCCESS, newTaskString, editedTask.toString()),
                result1.feedbackToUser);

        //invalid parameters
        CommandResult result2 = Executor.executeCommand("edit -t 0 return a book");
        assertEquals(MESSAGE_NO_EDIT_TASK, result2.feedbackToUser);
        CommandResult result3 = Executor.executeCommand("edit -t 2 return a book");
        assertEquals(MESSAGE_NO_EDIT_TASK, result3.feedbackToUser);
        CommandResult result4 = Executor.executeCommand("edit -t 1 return a book -by 3131313");

        String dateErrorResult = new IncorrectCommand(MESSAGE_ADD_TASK_DATE_TIME_UNKNOWN).execute().feedbackToUser;
        assertEquals(dateErrorResult, result4.feedbackToUser);
    }
}