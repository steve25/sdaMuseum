package sk.pocsik.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyFormatter {
    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return date.format(formatter);
    }

    public static String formatDateMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
        return date.format(formatter);
    }

    public static String formatTwoDecimal(double number) {
        return String.format("%.2f", number);
    }
}
