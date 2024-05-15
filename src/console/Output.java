package console;

import main.MenusLogic;
import museum.Museum;

import java.time.LocalDate;
import java.util.Scanner;

public class Output {

    private final static Scanner sc = new Scanner(System.in);

    /**
     * Print message only
     * @param message
     */
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

    public static boolean answerYesNo(String question) {
        System.out.print(question);
        String answer = sc.next();
        System.out.println();
        return answer.equalsIgnoreCase("y");
    }

    public static int makeMenu(String... items) {
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%s: %s%n", i + 1, items[i]);
        }

        return intInputValidationBetween("Enter your choice: ", "Please enter a valid number: ", 1, items.length);
    }

    public static String printTicketsSoldDay(Museum museum) {
        LocalDate date = MenusLogic.getTicketDate(museum.getMonthAheadToBuy());
        int ticketCountPerDay = museum.getTicketCountPerDay(date);
        double ticketCountPerDayPercentage = museum.getTicketCountPerDayPercentage(date);

        return OutputLogic.formatDate(date) + " - " + ticketCountPerDay + " (" + OutputLogic.formatTwoDecimal(ticketCountPerDayPercentage) + "%) tickets sold.";
    }

    public static String printTicketsSoldMonth(Museum museum) {
        LocalDate date = MenusLogic.getTicketDateMonth(museum.getMonthAheadToBuy());
        int ticketCountPerMonth = museum.getTicketCountPerMonth(date);
        double ticketCountPerMonthPercentage = museum.getTicketCountPerMonthPercentage(date, ticketCountPerMonth);

        return "In month " + OutputLogic.formateDateMonth(date) + " - " + ticketCountPerMonth + " (" + OutputLogic.formatTwoDecimal(ticketCountPerMonthPercentage) + "%) tickets sold.";
    }
}
