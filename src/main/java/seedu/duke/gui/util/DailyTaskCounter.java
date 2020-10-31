package seedu.duke.gui.util;

import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import seedu.duke.data.ModuleManager;
import seedu.duke.directory.Task;
import seedu.duke.util.DateTime;
import seedu.duke.util.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A utility class to count the number of tasks due for each day and generates the corresponding colored Label.
 */
public class DailyTaskCounter {

    private VBox box;
    private Label countLabel;
    private Label dateLabel;
    private DayOfWeek dayOfWeek;

    /**
     * Constructs the Daily Task Counter to count the number of tasks that are due on the specified day of the week.
     *
     * @param box
     *  The Vertical Box containing the Labels
     * @param countLabel
     *  The Label for the task count
     * @param dateLabel
     *  The label for the date of the day
     * @param dayOfWeek
     *  The day of the week
     */
    public DailyTaskCounter(VBox box, Label countLabel, Label dateLabel, DayOfWeek dayOfWeek) {
        this.box = box;
        this.countLabel = countLabel;
        this.dateLabel = dateLabel;
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Counts the number of undone tasks that are due on the specified date.
     *
     * @param dateOfDay
     *  The date
     * @return
     *  The number of undone tasks due on the specified date
     */
    private int countDayTask(LocalDate dateOfDay) {
        int taskCount = 0;
        ArrayList<Task> taskList = ModuleManager.getAllTasks();

        for (Task task : taskList) {
            DateTime deadline = task.getDeadline();
            // The task is undone and is due on that date
            if (deadline.isPresent() && deadline.isOn(dateOfDay) && !task.isDone()) {
                ++taskCount;
            }
        }

        return taskCount;
    }

    /**
     * Counts the number of undone tasks that are overdue.
     *
     * @return
     *  The number of overdue undone tasks
     */
    private int countOverdueTask() {
        int taskCount = 0;
        ArrayList<Task> taskList = ModuleManager.getAllTasks();

        for (Task task : taskList) {
            DateTime deadline = task.getDeadline();
            // The task is undone and is overdue
            if (deadline.isPresent() && deadline.isDue() && !task.isDone()) {
                ++taskCount;
            }
        }

        return taskCount;
    }

    /**
     * Sets the daily task count and date labels.
     */
    public void setDailyTaskCount() {
        LocalDate dateOfDay = DateTimeFormat.getNextDateOfDay(dayOfWeek);
        int taskCount = countDayTask(dateOfDay);

        // Update task count
        assert taskCount >= 0 : "Task Count should be greater than 0!";
        countLabel.setText(String.valueOf(taskCount));
        if (taskCount == 0) {
            countLabel.setTextFill(Color.MEDIUMSEAGREEN);
        } else if (taskCount <= 3) {
            countLabel.setTextFill(Color.DODGERBLUE);
        } else {
            countLabel.setTextFill(Color.FIREBRICK);
        }

        // Update date
        dateLabel.setText(String.valueOf(dateOfDay.getDayOfMonth()));

        // Update Box color if it is current date
        if (LocalDate.now().getDayOfWeek() == dayOfWeek) {
            box.setStyle("-fx-background-color: #cbd9f5");
        }
    }

    /**
     * Sets the overdue task count label.
     */
    public void setOverdueTaskCount() {
        int taskCount = countOverdueTask();
        countLabel.setText(String.valueOf(taskCount));
    }

    /**
     * Executes the command to show the undone tasks on the current day upon clicking on a Daily Task Box in the
     * Daily Task Counter Panel.
     *
     * @param mouseEvent
     *  The mouse click event
     * @param consoleScreen
     *  The console screen to display the message
     */
    public static void onClickDailyTask(MouseEvent mouseEvent, TextFlow consoleScreen) {
        final String dueMondayString = "due mon";
        final String dueTuesdayString = "due tue";
        final String dueWednesdayString = "due wed";
        final String dueThursdayString = "due thu";
        final String dueFridayString = "due fri";
        final String dueSaturdayString = "due sat";
        final String dueSundayString = "due sun";
    }
}
