package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String format(LocalDate date) {
        return date.format(formatter);
    }

    public static LocalDate parse(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }
}
