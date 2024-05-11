package console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Output {
    private final static Scanner sc = new Scanner(System.in);

    /**
     * Print a welcome message
     */
    public static void printWelcomeMessage() {
        System.out.println("Welcome our reservation system.");
        System.out.println();
    }

    public static LocalDate getTicketDate() {
        int year = LocalDate.now().getYear();

        int monthMin = LocalDate.now().getMonthValue();
        int monthMax = LocalDate.now().plusMonths(2).getMonthValue();
        String monthTextMessage = "Choose a month (min: " + monthMin + ", max: " + monthMax + "): ";

        int month = intInputValidationBetween(monthTextMessage, "Please enter a valid month: ", monthMin, monthMax);

        int minDayInMonth = month == LocalDate.now().getMonthValue() ? LocalDate.now().getDayOfMonth() : 1;
        int maxDayInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
        String dayTextMessage = "Choose a day (min: " + minDayInMonth + ", max: " + maxDayInMonth + "): ";

        int day = intInputValidationBetween(dayTextMessage, "Please enter a valid day: ", minDayInMonth, maxDayInMonth);

        System.out.println();
        return LocalDate.of(year, month, day);
    }

    public static int intInputValidationBetween(String text, String notValidText, int minValue, int maxValue) {
        int result;
        System.out.print(text);
        do {
            while (!sc.hasNextInt()) {
                System.out.print(notValidText);
                sc.next();
            }

            result = sc.nextInt();

            if (result < minValue || result > maxValue) {
                System.out.print(notValidText);
            }

        } while (result < minValue || result > maxValue);

        return result;
    }

    public static int makeMenu(String... items) {
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%s: %s%n", i + 1, items[i]);
        }

        return intInputValidationBetween("Enter your choice: ", "Please enter a valid number: ", 1, items.length);
    }

}
