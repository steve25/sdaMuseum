package sk.pocsik.menus;


import sk.pocsik.utils.ConsoleColors;

public class RegularMenu extends Menu<Integer>{

    public RegularMenu(String[] items) {
        this(null, items);
    }

    public RegularMenu(String question, String[] items) {
        super(question, items);
    }

    @Override
    protected void printItems() {
        for (int i = 0; i < this.items.length; i++) {
            System.out.printf(
                    ConsoleColors.YELLOW + "%s" + ConsoleColors.RESET + ": " +
                    ConsoleColors.GREEN + "%s" + ConsoleColors.RESET + "%n",
                    i + 1, items[i]);
        }
    }

    @Override
    protected void processResult(int choice) {
        this.result = choice;
    }


}
