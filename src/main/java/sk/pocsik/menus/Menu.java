package sk.pocsik.menus;

import sk.pocsik.utils.ConsoleColors;
import sk.pocsik.utils.MyIOClass;

public abstract class Menu<T> {
    protected final String question;
    protected final String[] items;
    protected T result;

    public Menu(String question, String[] items) {
        this.question = question;
        this.items = items;
        this.displayMenu();
    }

    public T getResult() {
        return result;
    }

    private void displayMenu() {

        if (this.question != null && !this.question.trim().isEmpty()) {
            this.printQuestion();
        }

        this.printItems();

        int choice = MyIOClass.intInputValidationBetween(
                "Enter your choice: ",
                "Please enter a valid number: ",
                1, this.items.length);
        processResult(choice);
    }

    private void printQuestion() {
        System.out.println(ConsoleColors.CYAN + this.question + ConsoleColors.RESET);
    }

    protected abstract void printItems();

    protected abstract void processResult(int choice);

}
