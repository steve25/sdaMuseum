package main.utils.menus;

import main.museum.TicketTypes;
import main.utils.ConsoleColors;
import main.utils.MyFormatter;
import main.utils.MyIOClass;

import java.util.HashMap;
import java.util.Map;

public class TicketTypeMenu {
    private final String question;
    private final Map<String, String> items = new HashMap<>();
    private String result;

    public TicketTypeMenu(String question) {
        this.question = question;
        this.makeItems();
        this.printMenu();
    }

    public String getResult() {
        return result;
    }

    private void makeItems() {
        for (TicketTypes ticket : TicketTypes.values()) {
            this.items.put(ticket.name(), ticket.getTicketName());
        }
    }

    private void printMenu() {
        this.printMenuQuestion();
        this.printMenuItems();
        int choice = MyIOClass.intInputValidationBetween("Enter your choice: ", "Please enter a valid number: ", 1, this.items.size());
        this.result = convertResultToString(choice);
    }

    private void printMenuQuestion() {
        System.out.println(ConsoleColors.CYAN + this.question + ConsoleColors.RESET);
    }

    private void printMenuItems() {
        int count = 1;
        for (Map.Entry<String, String> item : this.items.entrySet()) {
            System.out.printf(ConsoleColors.YELLOW + "%d" + ConsoleColors.RESET + ": %s - %s Eur%n", count, item.getValue(), MyFormatter.formatTwoDecimal(this.getPriceFromTicket(item.getKey())));
            count++;
        }
    }

    private double getPriceFromTicket(String key) {
        return TicketTypes.valueOf(key).calculatePrize();
    }

    private String convertResultToString(int index) {
        return String.valueOf(items.keySet().toArray()[index - 1]);
    }
}
