package main.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyIOClassLogic {

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return date.format(formatter);
    }

    public static String formatDateMonth(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM.yyyy");
        return date.format(formatter);
    }

    public static String capitalizedFirstChar(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static String formatTwoDecimal(double number) {
        return String.format("%.2f", number);
    }
}
