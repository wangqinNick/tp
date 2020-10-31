package seedu.duke.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.duke.gui.util.DailyTaskCounter;
import seedu.duke.gui.util.DirectoryTree;

import java.time.DayOfWeek;

public class DailyTaskWindow extends VBox{
    /* Daily Task Counter Panel components */

    public VBox mondayBox;
    public Label mondayTaskCount;
    public Label mondayDate;
    public VBox tuesdayBox;
    public Label tuesdayTaskCount;
    public Label tuesdayDate;
    public VBox wednesdayBox;
    public Label wednesdayTaskCount;
    public Label wednesdayDate;
    public VBox thursdayBox;
    public Label thursdayTaskCount;
    public Label thursdayDate;
    public VBox fridayBox;
    public Label fridayTaskCount;
    public Label fridayDate;
    public VBox saturdayBox;
    public Label saturdayTaskCount;
    public Label saturdayDate;
    public VBox sundayBox;
    public Label sundayTaskCount;
    public Label sundayDate;

    private MainStage parentController;

    @FXML
    private VBox displayBox;

    void setParentController(MainStage parentController) {
        this.parentController = parentController;
    }

    @FXML
    void initialize() {
        new DailyTaskCounter(mondayBox, mondayTaskCount, mondayDate, DayOfWeek.MONDAY).setDailyTaskCount();
        new DailyTaskCounter(tuesdayBox, tuesdayTaskCount, tuesdayDate, DayOfWeek.TUESDAY).setDailyTaskCount();
        new DailyTaskCounter(wednesdayBox, wednesdayTaskCount, wednesdayDate, DayOfWeek.WEDNESDAY).setDailyTaskCount();
        new DailyTaskCounter(thursdayBox, thursdayTaskCount, thursdayDate, DayOfWeek.THURSDAY).setDailyTaskCount();
        new DailyTaskCounter(fridayBox, fridayTaskCount, fridayDate, DayOfWeek.FRIDAY).setDailyTaskCount();
        new DailyTaskCounter(saturdayBox, saturdayTaskCount, saturdayDate, DayOfWeek.SATURDAY).setDailyTaskCount();
        new DailyTaskCounter(sundayBox, sundayTaskCount, sundayDate, DayOfWeek.SUNDAY).setDailyTaskCount();
    }
}
