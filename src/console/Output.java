package console;

import main.MenusLogic;
import museum.Museum;

import java.time.LocalDate;
import java.util.Scanner;

public class Output {

    private final static Scanner sc = new Scanner(System.in);

    public static void printMessage(String message) {
        System.out.println(message);
        System.out.println();
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

    public static int intInputValidation(String notValidText) {
        while (!sc.hasNextInt()) {
            System.out.print(notValidText);
            sc.next();
        }

        return sc.nextInt();
    }

    public static int makeMenu(String... items) {
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%s: %s%n", i + 1, items[i]);
        }

        return intInputValidationBetween("Enter your choice: ", "Please enter a valid number: ", 1, items.length);
    }

    public static String printTicketsSoldDay(Museum museum) {
        LocalDate date = MenusLogic.getTicketDate();
        int ticketCountPerDay = museum.getTicketCountPerDay(date);

        return OutputLogic.formatDate(date) + " - " + ticketCountPerDay + " tickets sold.";
    }

    public static String printTicketsSoldDayPercentage(Museum museum) {
        LocalDate date = MenusLogic.getTicketDate();
        double ticketCountPerDayPercentage = museum.getTicketCountPerDayPercentage(date);
        return OutputLogic.formatDate(date) + " - " + ticketCountPerDayPercentage + "% of places are occupied";
    }
}
