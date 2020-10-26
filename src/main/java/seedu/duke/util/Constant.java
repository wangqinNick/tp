package seedu.duke.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constant {
    public static final String DEFAULT_SYSTEM_MUSIC = "/music/sad.mp3";
    public static final String SECONDARY_SYSTEM_LANGUAGE = "CHINESE";
    public static final String DEFAULT_SYSTEM_LANGUAGE = "ENGLISH";
    public static final String USER_DIALOG_COLOR = "#a1f7f7";
    public static final String DEFAULT_DIALOG_COLOR = "#e5f59d";
    public static final String WARNING_DIALOG_COLOR = "#e5b6fa";
    public static final String INFORMATIVE_DIALOG_COLOR = "#f5bdba";
    public static final String DEFAULT_DIALOG_BORDER_COLOR = "#4141B2";
    public static final String DEFAULT_DIALOG_FONT = "Comic Sans MS";
    public static final int DEFAULT_DIALOG_SIZE = 14;
    public static final String DEFAULT_TASK_TIME = "-1";
    public static final String DEFAULT_USERNAME = "duck";
    public static final String DEFAULT_PASSWORD = "123";
    public static final String NEWLINE = "\n";
    public static final int INDEX_OFF_SET = -1;
    public static final int LIST_INDEX_OFFSET = 1;
    public static final String PROJECT_ROOT = System.getProperty("user.dir");
    public static final String DATA_FOLDER = "data";
    public static final String DATA_FILE = "duke.json";
    public static final Path PATH_TO_DATA_FOLDER = Paths.get(PROJECT_ROOT, DATA_FOLDER);
    public static final Path PATH_TO_DATA_FILE = Paths.get(PROJECT_ROOT, DATA_FOLDER, DATA_FILE);

    public static final String YES_ICON = "[Y]";
    public static final String NO_ICON = "[N]";
}
