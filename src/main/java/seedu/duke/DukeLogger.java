package seedu.duke;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.File;
import java.util.logging.SimpleFormatter;

public class DukeLogger {
    private Logger dukeLogger; // One logger for each instance

    // The following are shared across all DukeLogger instances
    private static FileHandler logFile;
    private static String logFileName = null;
    private static final String LOG_PATH = "./logs/";
    private static final String LOG_NAME = "session_";
    private static final String LOG_EXT = ".log";
    private static final Level LOGGING_LEVEL = Level.INFO; // CHANGE LOGGING LEVEL HERE!

    private static final SimpleFormatter FORMATTER = new SimpleFormatter() {
        private static final String formatE = "%1$s - %2$s:%n[%3$-7s] %4$s%n%5$s%n%n";
        private static final String format = "%1$s - %2$s:%n[%3$-7s] %4$s%n%n";
        @Override
        public synchronized String format(LogRecord lr) {
            if (lr.getThrown() != null) {
                return String.format(formatE,
                        lr.getSourceClassName(),
                        lr.getSourceMethodName(),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage(),
                        lr.getThrown()
                );
            } else {
                return String.format(format,
                        lr.getSourceClassName(),
                        lr.getSourceMethodName(),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        }
    };

    public DukeLogger(String className) {
        try {
            dukeLogger = Logger.getLogger(className);
            if (logFileName == null) {
                globalSetup();
                logFile = new FileHandler(logFileName);
            }
            dukeLogger.setUseParentHandlers(false); // Stop it from logging from console...
            dukeLogger.addHandler(logFile); // Make it log to file instead
            dukeLogger.setLevel(LOGGING_LEVEL);
            logFile.setFormatter(FORMATTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger() {
        return dukeLogger;
    }

    private static void globalSetup() {
        try {
            preparePath();
            logFileName = prepareFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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