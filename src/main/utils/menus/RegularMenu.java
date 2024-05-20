package main.utils.menus;

import main.utils.ConsoleColors;
import main.utils.MyIOClass;

public class RegularMenu {
    private final String question;
    private final String[] items;
    private int result;

    public RegularMenu(String[] items) {
        this(null, items);
    }

    public RegularMenu(String question, String[] items) {
        this.question = question;
        this.items = items;
        this.printMenu();
    }

    public int getResult() {
        return result;
    }

    private void printMenu() {

        if (this.question != null) {
            this.printMenuQuestion();
        }

        this.printMenuItems();
        this.result = MyIOClass.intInputValidationBetween("Enter your choice: ", "Please enter a valid number: ", 1, this.items.length);
    }

    private void printMenuQuestion() {
        System.out.println(this.question);
    }

    private void printMenuItems() {
        for (int i = 0; i < this.items.length; i++) {
            System.out.printf(ConsoleColors.YELLOW + "%s" + ConsoleColors.RESET + ": " + ConsoleColors.GREEN + "%s" + ConsoleColors.RESET + "%n", i + 1, items[i]);
        }
    }
}
