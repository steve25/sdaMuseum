package main.utils;

import java.time.LocalDate;

public class DateInputClass {
    public static LocalDate getTicketDate(int monthToAdd) {

        int year = getYearInput(monthToAdd);
        int month = getMonthInput(year, monthToAdd);
        int day = getDayInput(year, month, monthToAdd);
        System.out.println();

        return LocalDate.of(year, month, day);
    }

    public static LocalDate getTicketDateMonth(int monthToAdd) {
        int year = getYearInput(monthToAdd);
        int month = getMonthInput(year, monthToAdd);
        System.out.println();

        return LocalDate.of(year, month, 1);
    }

    private static int getYearInput(int monthToAdd) {
        int minYear = LocalDate.now().getYear();
        int maxYear = LocalDate.now().plusMonths(monthToAdd).getYear();

        if (minYear != maxYear) {
            String yearTextMessage = "Choose a year (min: " + minYear + ", max: " + maxYear + "): ";

            return MyIOClass.intInputValidationBetween(yearTextMessage, "Please enter a valid year: ", minYear, maxYear);
        }
        return LocalDate.now().getYear();
    }

    private static int getMonthInput(int year, int monthToAdd) {
        int minMonth;
        int maxMonth;
        if (year == LocalDate.now().getYear()) {
            minMonth = LocalDate.now().getMonthValue();
            maxMonth = 12;
        } else {
            minMonth = 1;
            maxMonth = LocalDate.now().plusMonths(monthToAdd).getMonthValue();
        }
        String monthTextMessage = "Choose a month (min: " + minMonth + ", max: " + maxMonth + "): ";

        return MyIOClass.intInputValidationBetween(monthTextMessage, "Please enter a valid month: ", minMonth, maxMonth);
    }

    private static int getDayInput(int year, int month, int monthToAdd) {
        int minDayInMonth = month == LocalDate.now().getMonthValue() ? LocalDate.now().getDayOfMonth() : 1;
        int maxDayInMonth = maxDayInMonth(year, month, monthToAdd);
        String dayTextMessage = "Choose a day (min: " + minDayInMonth + ", max: " + maxDayInMonth + "): ";

        return MyIOClass.intInputValidationBetween(dayTextMessage, "Please enter a valid day: ", minDayInMonth, maxDayInMonth);
    }

    private static int maxDayInMonth(int year, int month, int monthToAdd) {
        int result = LocalDate.of(year, month, LocalDate.now().getDayOfMonth()).plusMonths(2).getDayOfMonth();
        int maxDayInTwoMonth = LocalDate.of(year, month, 1).plusMonths(2).lengthOfMonth();

        return month == LocalDate.now().plusMonths(monthToAdd).getMonthValue() ? Math.min(result, maxDayInTwoMonth) : LocalDate.of(year, month, 1).lengthOfMonth();
    }
}
