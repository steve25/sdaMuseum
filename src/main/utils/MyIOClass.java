package main.utils;

import main.museum.Museum;

import java.time.LocalDate;
import java.util.Scanner;

public class MyIOClass {
    private final static Scanner sc = new Scanner(System.in);


    public static void printMessage(String message) {
        System.out.println(ConsoleColors.WHITE + message + ConsoleColors.RESET);
        System.out.println();
    }

    public static void printErrorMessage(String message) {
        System.out.println(ConsoleColors.RED + message + ConsoleColors.RESET);
        System.out.println();
    }

    public static int intInputValidation(String notValidText) {
        while (!sc.hasNextInt()) {
            System.out.print(ConsoleColors.RED + notValidText + ConsoleColors.YELLOW);
            sc.next();
        }

        return sc.nextInt();
    }

    public static int intInputValidationBetween(String text, String notValidText, int minValue, int maxValue) {
        int result;
        System.out.print(ConsoleColors.CYAN + text + ConsoleColors.YELLOW);
        do {
            result = intInputValidation(notValidText);

            if (result < minValue || result > maxValue) {
                System.out.print(ConsoleColors.RED + notValidText + ConsoleColors.YELLOW);
            }

        } while (result < minValue || result > maxValue);

        return result;
    }

    public static boolean answerYesNo(String question) {
        System.out.print(ConsoleColors.CYAN + question + ConsoleColors.YELLOW + " (y/n) " + ConsoleColors.YELLOW);
        String answer = sc.next();

        return answer.equalsIgnoreCase("y");
    }

    public static String printTicketsSoldMonth(Museum museum) {
        DateInputClass ticketDateInput = new DateInputClass(museum.getMonthAheadToBuy());
        LocalDate date = ticketDateInput.getTicketDateMonth();
        int ticketCountPerMonth = museum.getTicketCountPerMonth(date);
        double ticketCountPerMonthPercentage = museum.getTicketCountPerMonthPercentage(date, ticketCountPerMonth);

        return "In month " + MyFormatter.formatDateMonth(date) + " - " + ticketCountPerMonth + " (" + MyFormatter.formatTwoDecimal(ticketCountPerMonthPercentage) + "%) tickets sold.";
    }

    public static String printTicketsSoldDay(Museum museum) {
        DateInputClass ticketDateInput = new DateInputClass(museum.getMonthAheadToBuy());
        LocalDate date = ticketDateInput.getTicketDate();
        int ticketCountPerDay = museum.getTicketCountPerDay(date);
        double ticketCountPerDayPercentage = museum.getTicketCountPerDayPercentage(ticketCountPerDay);

        return MyFormatter.formatDate(date) + " - " + ticketCountPerDay + " (" + MyFormatter.formatTwoDecimal(ticketCountPerDayPercentage) + "%) tickets sold.";
    }
}
