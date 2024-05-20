package main.utils;

import main.museum.Museum;

import java.time.LocalDate;
import java.util.Scanner;

public class MyIOClass {
    private final static Scanner sc = new Scanner(System.in);


    public static void printMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public static int intInputValidation(String notValidText) {
        while (!sc.hasNextInt()) {
            System.out.print(notValidText);
            sc.next();
        }

        return sc.nextInt();
    }

    public static int intInputValidationBetween(String text, String notValidText, int minValue, int maxValue) {
        int result;
        System.out.print(text);
        do {
            result = intInputValidation(notValidText);

            if (result < minValue || result > maxValue) {
                System.out.print(notValidText);
            }

        } while (result < minValue || result > maxValue);

        return result;
    }

    public static boolean answerYesNo(String question) {
        System.out.print(question);
        String answer = sc.next();
        System.out.println();

        return answer.equalsIgnoreCase("y");
    }

    public static String printTicketsSoldMonth(Museum museum) {
        LocalDate date = DateInputClass.getTicketDateMonth(museum.getMonthAheadToBuy());
        int ticketCountPerMonth = museum.getTicketCountPerMonth(date);
        double ticketCountPerMonthPercentage = museum.getTicketCountPerMonthPercentage(date, ticketCountPerMonth);

        return "In month " + MyFormatter.formatDateMonth(date) + " - " + ticketCountPerMonth + " (" + MyFormatter.formatTwoDecimal(ticketCountPerMonthPercentage) + "%) tickets sold.";
    }

    public static String printTicketsSoldDay(Museum museum) {
        LocalDate date = DateInputClass.getTicketDate(museum.getMonthAheadToBuy());
        int ticketCountPerDay = museum.getTicketCountPerDay(date);
        double ticketCountPerDayPercentage = museum.getTicketCountPerDayPercentage(ticketCountPerDay);

        return MyFormatter.formatDate(date) + " - " + ticketCountPerDay + " (" + MyFormatter.formatTwoDecimal(ticketCountPerDayPercentage) + "%) tickets sold.";
    }
}
