package seedu.duke.util;

import seedu.duke.exception.InvalidFormatException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * <h3>Date Time Format</h3>
 * The <b>Date Time Format</b> formats and converts strings into various <i>datetime</i> objects, specifically
 * <code>DateTime</code>, <code>LocalDate</code> and <code>LocalTime</code> objects.
 */
public class DateTimeFormat {

    /* An exhaustive list of date formats accepted by the Nuke program */
    private static final String[] ALL_DATE_FORMATS = {
        "dd-MM-yyyy", "dd/MM[/yyyy]", "d/MM[/yyyy]", "dd/M[/yyyy]", "d/M[/yyyy]",
        "dd/MM[/yy]", "d/MM[/yy]", "dd/M[/yy]", "d/M[/yy]",
        "dd-MM[-yyyy]", "d-MM[-yyyy]", "dd-M[-yyyy]", "d-M[-yyyy]",
        "dd-MM[-yy]", "d-MM[-yy]", "dd-M[-yy]", "d-M[-yy]",
        "ddMMyyyy", "ddMMyy"
    };

    /* An exhaustive list of time formats accepted by the Nuke program */
    private static final String[] ALL_TIME_FORMATS = {
        "H:mm", "h:mma", "H:mma",
        "h.mma", "H.mma", "H.mm",
        "hmma", "Hmma", "Hmm",
        "ha", "Ha", "H"
    };

    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    /**
     * Converts the specified <code>datetime</code> string into a <code>DateTime</code> object.
     * <p></p>
     * <b>Note</b>: The <i>time</i> attribute must be present in the string.
     * <br> The <i>date</i> attribute is <u>optional</u> and is set to the current date if absent.
     * <br> The attributes must be in accepted formats for successful conversion.
     * Otherwise, an <code>Exception</code> will be thrown.
     *
     * @param datetime
     *  The string to be converted into its corresponding <code>DateTime</code> object
     * @return
     *  The <code>DateTime</code> object converted from the string
     * @throws InvalidDateTimeException
     *  If more than 2 attributes are present in the string
     * @throws InvalidDateException
     *  If the <i>date</i> attribute in the string is invalid
     * @throws InvalidTimeException
     *  If the <i>time</i> attribute in the string is invalid
     */
    public static DateTime stringToDateTime(String datetime)
            throws InvalidDateTimeException, InvalidDateException, InvalidTimeException {
        if (datetime.isBlank()) {
            return new DateTime();
        }
        String[] dateTimeData = datetime.split("\\s+");

        if (dateTimeData.length == 1) {
            try {
                String time = dateTimeData[0].trim().toUpperCase();
                return new DateTime(stringToTime(time));
            } catch (InvalidTimeException e) {
                String date = dateTimeData[0].trim();
                return new DateTime(stringToDate(date));
            }
        } else if (dateTimeData.length == 2) {
            String date = dateTimeData[0].trim();
            String time = dateTimeData[1].trim().toUpperCase();
            return new DateTime(stringToDate(date), stringToTime(time));
        } else {
            throw new InvalidDateTimeException();
        }
    }

    /**
     * Converts the specified <code>date</code> string into a <code>LocalDate</code> object.
     * <p></p>
     * <b>Note</b>: The <code>date</code> string must be in either an accepted <i>date</i> format or an accepted
     * <i>date specifier</i> <i><small>(See Below)</small></i>.
     * Otherwise, an <code>Exception</code> will be thrown.
     * <p></p>
     * <b>Accepted Date Specifiers</b>
     * <ul>
     *     <li><i>today</i> or <i>tdy</i></li>
     *     <li><i>yesterday</i> or <i>yst</i></li>
     *     <li><i>tomorrow</i> or <i>tmr</i></li>
     * </ul>
     *
     * @param date
     *  The string to be converted into its corresponding <code>LocalDate</code> object
     * @return
     *  The <code>LocalDate</code> object converted from the string
     * @throws InvalidDateException
     *  If the <code>date</code> string is an invalid <i>date</i>
     */
    public static LocalDate stringToDate(String date) throws InvalidDateException {
        if (date == null) {
            return LocalDate.now();
        }

        // Checks if matches date specifiers first, before checking if matches date formats
        switch (date.toLowerCase()) {
        case "today":
        case "tdy":
            return LocalDate.now();

        case "yesterday":
        case "yst":
            return LocalDate.now().minusDays(1);

        case "tomorrow":
        case "tmr":
            return LocalDate.now().plusDays(1);

        case "monday":
        case "mon":
            return getNextDateOfDay(DayOfWeek.MONDAY);

        case "tuesday":
        case "tue":
            return getNextDateOfDay(DayOfWeek.TUESDAY);

        case "wednesday":
        case "wed":
            return getNextDateOfDay(DayOfWeek.WEDNESDAY);

        case "thursday":
        case "thu":
            return getNextDateOfDay(DayOfWeek.THURSDAY);

        case "friday":
        case "fri":
            return getNextDateOfDay(DayOfWeek.FRIDAY);

        case "saturday":
        case "sat":
            return getNextDateOfDay(DayOfWeek.SATURDAY);

        case "sunday":
        case "sun":
            return getNextDateOfDay(DayOfWeek.SUNDAY);

        default:
            for (String formatPattern : ALL_DATE_FORMATS) {
                try {
                    DateTimeFormatter format = new DateTimeFormatterBuilder()
                            .appendPattern(formatPattern)
                            .parseDefaulting(ChronoField.YEAR_OF_ERA, CURRENT_YEAR)
                            .toFormatter();
                    return LocalDate.parse(date, format);
                } catch (DateTimeParseException e) {
                    // Ignore invalid formats
                }
            }
            // Throws exception if fails to parse all date formats
            throw new InvalidDateException();
        }
    }



    /**
     * Converts the specified <code>time</code> string into a <code>LocalTime</code> object.
     * <p></p>
     * <b>Note</b>: The <code>time</code> string must be in either an accepted <i>time</i> format or <code>NULL</code>.
     * Otherwise, an <code>Exception</code> will be thrown.
     * <br> In the case of a <code>NULL</code> <code>time</code> string, <code>NULL</code> will be returned instead.
     *
     * @param time The string to be converted into its corresponding <code>LocalTime</code> object
     * @return The <code>LocalTime</code> object converted from the string
     * @throws InvalidTimeException If the <code>date</code> string is an invalid <i>time</i>
     */
    public static LocalTime stringToTime(String time) throws InvalidTimeException {
        if (time == null) {
            return null;
        }

        for (String format : ALL_TIME_FORMATS) {
            try {
                return LocalTime.parse(time.toUpperCase(), DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                // Ignore invalid formats
            }
        }
        // Throws exception if fails to parse all time formats
        throw new InvalidTimeException();
    }

    /**
     * Gets the closest date of a day of the week after the current date.
     *
     * @param dayOfWeek
     *  The day of the week for which its date is to be found
     * @return
     *  The closest date of the specified day of the week
     */
    public static LocalDate getNextDateOfDay(DayOfWeek dayOfWeek) {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek currentDay = currentDate.getDayOfWeek();
        int dayCount = (dayOfWeek.ordinal() - currentDay.ordinal() + 7) % 7;

        // Toggle between current day or week after
        // if (dayCount == 0) {
        //    dayCount = 7;
        // }

        return currentDate.plusDays(dayCount);
    }

    /**
     * Signals that the <i>datetime</i> string given is in an invalid format.
     */
    public static class InvalidDateTimeException extends InvalidFormatException {
    }

    /**
     * Signals that the <i>date</i> string given is in an invalid format.
     */
    public static class InvalidDateException extends InvalidDateTimeException {
    }

    /**
     * Signals that the <i>time</i> string given is in an invalid format.
     */
    public static class InvalidTimeException extends InvalidDateTimeException {
    }
}
