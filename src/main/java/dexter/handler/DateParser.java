package dexter.handler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/** Converts deadline input and saved ISO dates into date-time values. */
public class DateParser {
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("d/M/uuuu HHmm").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Accepts yyyy-MM-dd, d/M/uuuu HHmm, or an ISO date-time saved by Storage.
     * Dates without a time use midnight. Invalid dates throw DateTimeParseException.
     */
    public static LocalDateTime parse(String text) {
        text = text.trim();
        if (text.contains("T")) {
            return LocalDateTime.parse(text);
        }
        if (text.contains("/")) {
            return LocalDateTime.parse(text, DATE_TIME_FORMAT);
        }
        return LocalDate.parse(text).atStartOfDay();
    }
}
