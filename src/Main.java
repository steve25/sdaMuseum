import java.time.LocalDate;

public class Main {
    private static final Museum museum = new Museum();

    public static void main(String[] args) {
        Output.printMessage("Welcome our reservation system.");
        mainLoop();
    }

    public static void mainLoop() {
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
                Output.printMessage("Tickets sold all time: " + museum.getTicketCountAllTimes());
                break;
            case 2:
                Output.printMessage(Output.printTicketsSoldDay(museum));
                break;
            case 3:
                Output.printMessage(Output.printTicketsSoldDayPercentage(museum));
                break;
            default:
                break;
        }

    }


    private static void buyTicket() {
        LocalDate ticketDate = getTicketDate();

        int availableTicket = museum.checkAvailableTicketsCount(ticketDate);

        if (availableTicket == 0) {
            System.out.println("Sorry, there are no   tickets for this date.");
            return;
        }

        int maxTicket = Math.min(availableTicket, 3);
        int ticketCount = Output.intInputValidationBetween("How many tickets you want to buy? (min: 1, max: " + maxTicket + "): ", "Enter a valid number: ", 1, maxTicket);
        System.out.println();

        museum.setTicketCount(ticketDate, ticketCount);

        Output.printMessage("You bought " + ticketCount + " tickets for " + museum.getTicketPrice(ticketCount) + "Eur");
    }

    public static LocalDate getTicketDate() {
        int year = LocalDate.now().getYear();

        int monthMin = LocalDate.now().getMonthValue();
        int monthMax = LocalDate.now().plusMonths(2).getMonthValue();
        String monthTextMessage = "Choose a month (min: " + monthMin + ", max: " + monthMax + "): ";

        int month = Output.intInputValidationBetween(monthTextMessage, "Please enter a valid month: ", monthMin, monthMax);

        int minDayInMonth = month == LocalDate.now().getMonthValue() ? LocalDate.now().getDayOfMonth() : 1;
        int maxDayInMonth = maxDayInMonth(year, month, monthMax);
        String dayTextMessage = "Choose a day (min: " + minDayInMonth + ", max: " + maxDayInMonth + "): ";

        int day = Output.intInputValidationBetween(dayTextMessage, "Please enter a valid day: ", minDayInMonth, maxDayInMonth);

        System.out.println();
        return LocalDate.of(year, month, day);
    }

    private static int maxDayInMonth(int year, int month, int monthMax) {
        int result = LocalDate.of(year, month, LocalDate.now().getDayOfMonth()).plusMonths(2).getDayOfMonth();
        int maxDayInTwoMonth = LocalDate.of(year, month, 1).plusMonths(2).lengthOfMonth();

        return month == monthMax ? Math.min(result, maxDayInTwoMonth) : LocalDate.of(year, month, 1).lengthOfMonth();
    }
}