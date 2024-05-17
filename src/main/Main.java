package main;

import utils.MyIOClass;
import utils.MyIOClassLogic;
import museum.Museum;

import utils.NationalRestDays;

import java.time.LocalDate;

public class Main {
    private static final Museum museum = new Museum();

    public static void main(String[] args) {

        MyIOClass.printMessage("Welcome our reservation system.");
        mainLoop();
    }

    public static void mainLoop() {
        int result;
        while (true) {
            result = MyIOClass.makeMenu("Buy a tickets", "Admin panel", "Exit");
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
        int result;
        while (true) {
            result = MyIOClass.makeMenu("Tickets sold - all time", "Tickets sold - day", "Tickets sold - month", "Cancel");
            System.out.println();

            switch (result) {
                case 1:
                    MyIOClass.printMessage("Tickets sold all time: " + museum.getTicketCountAllTimes());
                    break;
                case 2:
                    MyIOClass.printMessage(MyIOClass.printTicketsSoldDay(museum));
                    break;
                case 3:
                    MyIOClass.printMessage(MyIOClass.printTicketsSoldMonth(museum));
                    break;
                default:
                    return;
            }
        }
    }

    public static void buyTicket() {
        int totalTicketCount = 0;
        double totalPrice = 0;
        boolean isSameDate = false;
        LocalDate ticketDate = null;

        while (true) {

            if (!isSameDate) {
                ticketDate = MenusLogic.getTicketDate(museum.getMonthAheadToBuy());
            }

            int availableTicket = museum.checkAvailableTicketsCount(ticketDate);

            if (availableTicket == 0) {
                MyIOClass.printMessage("Sorry, there are no tickets for this date.");
                return;
            }

            NationalRestDays nationalRestDays = new NationalRestDays(ticketDate.getYear());

            if (nationalRestDays.isRestDay(ticketDate)) {
                MyIOClass.printMessage("Sorry, our museum is closed this date.");
                return;
            }

            System.out.println("Which kind of ticket you want?");

            int ticketType = MyIOClass.makeMenu(MenusLogic.makeTicketTypeArr());
            System.out.println();

            int maxTicket = Math.min(availableTicket, 3);
            int ticketCount = MyIOClass.intInputValidationBetween("How many tickets you want to buy? (min: 1, max: " + maxTicket + "): ", "Enter a valid number: ", 1, maxTicket);
            System.out.println();

            totalTicketCount += ticketCount;
            totalPrice += museum.getTicketPrice(ticketCount, ticketType);

            museum.setTicketCount(ticketDate, ticketCount);

            boolean isAnotherTicket = MyIOClass.answerYesNo("Do you want buy another tickets? (y/n) ");

            if (!isAnotherTicket) {
                break;
            }

            isSameDate = MyIOClass.answerYesNo("Do you want buy a tickets for the same date? - " + MyIOClassLogic.formatDate(ticketDate) + " (y/n) ");
        }

        MyIOClass.printMessage("You bought " + totalTicketCount + " tickets for " + totalPrice + "Eur");
    }
}