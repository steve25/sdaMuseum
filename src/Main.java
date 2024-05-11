import console.Output;

import java.time.LocalDate;

public class Main {
    private static final Museum museum = new Museum();

    public static void main(String[] args) {
        Output.printWelcomeMessage();
        menuLogic();

    }

    public static void menuLogic() {
        int result;
        while (true) {
            result = Output.makeMenu("Buy a ticket", "Exit");

            switch (result) {
                case 1:
                    buyTicket();
                    break;
                default:
                    return;
            }
        }
    }

    private static void buyTicket() {
        LocalDate ticketDate = Output.getTicketDate();

        int availableTicket = museum.checkAvailableTicketsCount(ticketDate);

        if (availableTicket == 0) {
            System.out.println("Sorry, there are no available tickets for this date.");
            return;
        }

        int maxTicket = Math.min(availableTicket, 3);
        int ticketCount = Output.intInputValidationBetween("How many tickets you want to buy? (min: 1, max: " + maxTicket + "): ", "Enter a valid number: ", 1, maxTicket);

        museum.setTicketCount(ticketDate, ticketCount);
    }
}