package seedu.duke;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.io.File;

public class DukeLogger {
    private static Logger dukeLogger;
    private static FileHandler logFile;
    private static SimpleFormatter formatterTxt = new SimpleFormatter();
    private static final String LOG_PATH = "./logs/";
    private static final String LOG_NAME = "session_";
    private static final String LOG_EXT = ".log";
    private static final Level LOGGING_LEVEL = Level.INFO; // CHANGE LOGGING LEVEL HERE!

    public static void setup(String className) {
        try {
            preparePath();
            String currentLogFile = prepareFile();
            dukeLogger = Logger.getLogger(className);
            logFile = new FileHandler(currentLogFile); // todo: remove magic file path
            dukeLogger.setUseParentHandlers(false); // Stop it from logging from console...
            logFile.setFormatter(formatterTxt);
            dukeLogger.addHandler(logFile); // Make it log to file instead
            dukeLogger.setLevel(LOGGING_LEVEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String log) {
        dukeLogger.info(log);
    }

    public static void info(String log, Exception e) {
        dukeLogger.log(Level.INFO, log, e);
    }

    public static void info(String log, Object ob) {
        dukeLogger.log(Level.INFO, log, ob);
    }

    public static void warning(String log) {
        dukeLogger.warning(log);
    }

    public static void warning(String log, Exception e) {
        dukeLogger.log(Level.WARNING, log, e);
    }

    public static void warning(String log, Object ob) {
        dukeLogger.log(Level.WARNING, log, ob);
    }

    public static void severe(String log) {
        dukeLogger.severe(log);
    }

    public static void severe(String log, Exception e) {
        dukeLogger.log(Level.SEVERE, log, e);
    }

    public static void severe(String log, Object ob) {
        dukeLogger.log(Level.SEVERE, log, ob);
    }

    public static void preparePath() {
        File logFolder = new File(LOG_PATH);
        if (!logFolder.exists()) {
            logFolder.mkdir();
        }
    }

    public static String prepareFile() throws IOException {
        int sessionNum = 1;
        String currentLogFileName = LOG_PATH + LOG_NAME + sessionNum + LOG_EXT;
        File logFile = new File(currentLogFileName);
        while (logFile.exists()) {
            sessionNum++;
            currentLogFileName = LOG_PATH + LOG_NAME + sessionNum + LOG_EXT;
            logFile = new File(currentLogFileName);
        }
        logFile.createNewFile();
        return currentLogFileName;
    }
}