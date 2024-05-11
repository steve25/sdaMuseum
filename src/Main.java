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
            result = Output.makeMenu("Buy a ticket", "Admin panel", "Exit");
            System.out.println();

            switch (result) {
                case 1:
                    buyTicket();
                    break;
                case 2:
                    adminPanel();
                    break;
                default:
                    return;
            }
        }
    }

    private static void adminPanel() {
        int result = Output.makeMenu("Tickets sold - all time", "Ticket sold - day", "Ticket sold - day (percentage)", "Cancel");
        System.out.println();

            switch (result) {
                case 1:
                    System.out.println("Tickets sold: " + museum.getTicketCountAllTimes());
                    System.out.println();
                    break;
                case 2:
                    System.out.println(Output.printTicketsSoldDay(museum));
                    System.out.println();
                    break;
                case 3:
                    System.out.println(Output.printTicketsSoldDayPercentage(museum));
                    System.out.println();
                    break;
                default:
                    break;

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
        System.out.println();

        museum.setTicketCount(ticketDate, ticketCount);
    }
}