package main;

import console.Output;
import museum.Museum;

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
            result = Output.makeMenu("Buy a tickets", "Admin panel", "Exit");
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

    public static void buyTicket() {
        LocalDate ticketDate = MenusLogic.getTicketDate();

        int availableTicket = museum.checkAvailableTicketsCount(ticketDate);

        if (availableTicket == 0) {
            Output.printMessage("Sorry, there are no tickets for this date.");
            return;
        }

        System.out.println("Which kind of ticket you want?");

        int ticketType = Output.makeMenu(MenusLogic.makeTicketTypeArr());
        System.out.println();

        int maxTicket = Math.min(availableTicket, 3);
        int ticketCount = Output.intInputValidationBetween("How many tickets you want to buy? (min: 1, max: " + maxTicket + "): ", "Enter a valid number: ", 1, maxTicket);
        System.out.println();

        museum.setTicketCount(ticketDate, ticketCount);

        Output.printMessage("You bought " + ticketCount + " tickets for " + museum.getTicketPrice(ticketCount, ticketType) + "Eur");
    }
}