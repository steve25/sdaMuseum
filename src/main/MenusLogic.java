package main;

import console.Output;
import console.OutputLogic;
import museum.TicketTypes;

import java.time.LocalDate;

public class MenusLogic {

    public static String[] makeTicketTyperArr() {
        TicketTypes[] ticketTypes = TicketTypes.values();
        String[] result = new String[ticketTypes.length];
        for (int i = 0; i < ticketTypes.length; i++) {
            result[i] = OutputLogic.capitalizedFirstChar(ticketTypes[i].name().toLowerCase().replace("_", " "));
        }

        return result;
    }

    public static LocalDate getTicketDate() {
        int year = LocalDate.now().getYear();

        int monthMin = LocalDate.now().getMonthValue();
        int monthMax = LocalDate.now().plusMonths(2).getMonthValue();
        String monthTextMessage = "Choose a month (min: " + monthMin + ", max: " + monthMax + "): ";

        int month = Output.intInputValidationBetween(monthTextMessage, "Please enter a valid month: ", monthMin, monthMax);

        int minDayInMonth = month == LocalDate.now().getMonthValue() ? LocalDate.now().getDayOfMonth() : 1;
        int maxDayInMonth = maxDayInMonth(year, month, monthMax);
        String dayTextMessage = "Choose a day (min: " + minDayInMonth + ", max: " + maxDayInMonth + "): ";

        int day = Output.intInputValidationBetween(dayTextMessage, "Please enter a valid day: ", minDayInMonth, maxDayInMonth);

        System.out.println();
        return LocalDate.of(year, month, day);
    }

    private static int maxDayInMonth(int year, int month, int monthMax) {
        int result = LocalDate.of(year, month, LocalDate.now().getDayOfMonth()).plusMonths(2).getDayOfMonth();
        int maxDayInTwoMonth = LocalDate.of(year, month, 1).plusMonths(2).lengthOfMonth();

        return month == monthMax ? Math.min(result, maxDayInTwoMonth) : LocalDate.of(year, month, 1).lengthOfMonth();
    }
}
