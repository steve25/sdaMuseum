package sk.pocsik.menus;


import sk.pocsik.museum.TicketTypes;
import sk.pocsik.utils.ConsoleColors;
import sk.pocsik.utils.MyFormatter;

public class TicketTypeMenu extends Menu<String> {

    public TicketTypeMenu(String question) {
        super(question, createItemsArray());
    }

    private static String[] createItemsArray() {
        String[] items = new String[TicketTypes.values().length];
        for (int i = 0; i < items.length; i++) {
            items[i] = TicketTypes.values()[i].name();
        }
        return items;
    }


    @Override
    protected void printItems() {
        int count = 1;
        for (TicketTypes ticket : TicketTypes.values()) {
            System.out.printf(
                    ConsoleColors.YELLOW + "%d" +
                            ConsoleColors.RESET + ": %s - %s Eur%n",
                    count, ticket.getTicketHumanReadableName(),
                    MyFormatter.formatTwoDecimal(ticket.calculatePrize()));
            count++;
        }
    }

    @Override
    protected void processResult(int choice) {
        this.result = TicketTypes.values()[choice - 1].name();
    }
}
