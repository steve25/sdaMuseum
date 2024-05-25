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
            String yearTextMessage = "Choose a year " + ConsoleColors.YELLOW +
                    "(min: " + minYear + ", max: " + maxYear + ")" +
                    ConsoleColors.RESET + ": ";

            return MyIOClass.intInputValidationBetween(
                    yearTextMessage,
                    "Please enter a valid year: ",
                    minYear, maxYear);
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
        String monthTextMessage = "Choose a month " + ConsoleColors.YELLOW +
                "(min: " + minMonth + ", max: " + maxMonth + ")" +
                ConsoleColors.RESET + ": ";

        return MyIOClass.intInputValidationBetween(
                monthTextMessage,
                "Please enter a valid month: ",
                minMonth, maxMonth);
    }

    private static int getDayInput(int year, int month, int monthToAdd) {
        int minDayInMonth = getMinDayInMonth(year, month);
        int maxDayInMonth = getMaxDayInMonth(year, month, monthToAdd);
        String dayTextMessage = "Choose a day " + ConsoleColors.YELLOW +
                "(min: " + minDayInMonth + ", max: " + maxDayInMonth + ")" +
                ConsoleColors.RESET + ": ";

        return MyIOClass.intInputValidationBetween(
                dayTextMessage,
                "Please enter a valid day: ",
                minDayInMonth, maxDayInMonth);
    }

    private static int getMinDayInMonth(int year, int month) {

        int actualDay = LocalDate.of(year, month, LocalDate.now().getDayOfMonth()).getDayOfMonth();

        LocalDate ticketBuyDate = LocalDate.of(year, month, LocalDate.now().getDayOfMonth());

        return ticketBuyDate.equals(LocalDate.now()) ? actualDay : 1;
    }

    private static int getMaxDayInMonth(int year, int month, int monthToAdd) {
        int actualDay = LocalDate.of(year, month, LocalDate.now().getDayOfMonth()).getDayOfMonth();
        int maxDayInMonth = LocalDate.of(year, month, 1).lengthOfMonth();

        LocalDate ticketBuyDate = LocalDate.of(year, month, LocalDate.now().getDayOfMonth());
        LocalDate maxDateToBuy = LocalDate.now().plusMonths(monthToAdd);

        return ticketBuyDate.equals(maxDateToBuy) ? actualDay : maxDayInMonth;
    }
}
