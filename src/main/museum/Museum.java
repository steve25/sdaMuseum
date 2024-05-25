package main.museum;

import main.menus.Menu;
import main.utils.*;
import main.menus.RegularMenu;
import main.menus.TicketTypeMenu;

import java.time.LocalDate;

public class Museum {
    private final Invoice invoice = new Invoice();
    private final NationalRestDays nationalRestDays;

    private final int ticketLimitPerDay;
    private final int monthAheadToBuy;

    public Museum(int ticketLimitPerDay, int monthAheadToBuy) {
        this.ticketLimitPerDay = ticketLimitPerDay;
        this.monthAheadToBuy = monthAheadToBuy;
        nationalRestDays = new NationalRestDays(LocalDate.now().plusMonths(this.monthAheadToBuy).getYear());
        this.startApp();
    }

    public int getMonthAheadToBuy() {
        return monthAheadToBuy;
    }

    private void startApp() {
        System.out.println();
        MyIOClass.printMessage("Welcome our reservation system.");
        this.mainLoop();
    }

    private void mainLoop() {
        while (true) {
            Menu<Integer> mainRegularMenu = new RegularMenu(new String[]{"Buy tickets", "Admin panel", "Exit"});
            int result = mainRegularMenu.getResult();
            System.out.println();

            switch (result) {
                case 1:
                    this.buyTicketLoop();
                    break;
                case 2:
                    this.adminPanel();
                    break;
                default:
                    System.out.println(ConsoleColors.RESET);
                    return;
            }
        }
    }


    private void buyTicketLoop() {
        boolean isSameDate = false;
        LocalDate ticketDate = null;

        while (true) {
            if (!isSameDate) {
                ticketDate = DateInputClass.getTicketDate(this.monthAheadToBuy);
            }

            int freeTicketsAmount = this.checkFreeTicketsAmount(ticketDate);
            if (freeTicketsAmount == 0) {
                MyIOClass.printErrorMessage("Sorry, there are no free tickets for this date.");
                return;
            }

            if (nationalRestDays.isRestDay(ticketDate)) {
                MyIOClass.printErrorMessage("Sorry, our museum is closed this date.");
                return;
            }

            Menu<String> ticketTypeMenu = new TicketTypeMenu("Which type of ticket you want to buy?");
            String ticketType = ticketTypeMenu.getResult();

            System.out.println();
            int maxTicketsValue = Math.min(3, freeTicketsAmount);
            int ticketAmount = MyIOClass.intInputValidationBetween(
                    "How many tickets you want to buy? " + ConsoleColors.YELLOW +
                            "(min: 1, max: " + maxTicketsValue + ")" + ConsoleColors.RESET + ": ",
                    "Enter a valid number: ",
                    1, maxTicketsValue);

            Ticket ticket = new Ticket(ticketDate, ticketType, ticketAmount);

            invoice.addTickets(ticket);

            boolean isAnotherTicket = MyIOClass.answerYesNo("Do you want buy another tickets?");
            if (!isAnotherTicket) {
                System.out.println();
                break;
            }

            isSameDate = MyIOClass.answerYesNo(
                    "Do you want buy a tickets for the same date? - " +
                            MyFormatter.formatDate(ticketDate) + " (y/n) "
            );
        }
    }

    private void adminPanel() {
        while (true) {
            Menu<Integer> adminPanelRegularMenu = new RegularMenu(new String[]{"Tickets sold - all time", "Tickets sold - month", "Tickets sold - day", "Cancel"});
            int result = adminPanelRegularMenu.getResult();
            System.out.println();

            switch (result) {
                case 1:
                    MyIOClass.printMessage("Tickets sold all time: " + this.allTimeTicketsSold());
                    break;
                case 2:
                    MyIOClass.printMessage(MyIOClass.printTicketsSoldMonth(this));
                    break;
                case 3:
                    MyIOClass.printMessage(MyIOClass.printTicketsSoldDay(this));
                    break;
                default:
                    return;
            }
        }
    }

    private int checkFreeTicketsAmount(LocalDate date) {
        int result = 0;

        for (Ticket ticket : invoice.getTickets()) {
            if (ticket.ticketDate().equals(date)) {
                result += ticket.ticketAmount();
            }
        }

        return this.ticketLimitPerDay - result;
    }

    private int allTimeTicketsSold() {
        int result = 0;

        for (Ticket ticket : this.invoice.getTickets()) {
            result += ticket.ticketAmount();
        }

        return result;
    }

    public int getTicketCountPerMonth(LocalDate date) {
        int result = 0;

        for (Ticket ticket : this.invoice.getTickets()) {
            if (ticket.ticketDate().getMonthValue() == date.getMonthValue() &&
                    ticket.ticketDate().getYear() == date.getYear()) {
                result += ticket.ticketAmount();
            }
        }

        return result;
    }

    public double getTicketCountPerMonthPercentage(LocalDate date, int totalSoldTicket) {
        int actualDay = LocalDate.now().getDayOfMonth();
        int totalDays = date.lengthOfMonth();

        if (date.getMonthValue() == LocalDate.now().getMonthValue()) {
            return ((double) totalSoldTicket / (this.ticketLimitPerDay * (totalDays - actualDay))) * 100;
        }

        if (LocalDate.of(LocalDate.now().getYear(), LocalDate.now().plusMonths(this.monthAheadToBuy).getMonthValue(), 1).getMonthValue() == date.getMonthValue()) {
            return ((double) totalSoldTicket / (this.ticketLimitPerDay * actualDay)) * 100;
        }

        return ((double) totalSoldTicket / (this.ticketLimitPerDay * totalDays)) * 100;
    }

    public int getTicketCountPerDay(LocalDate date) {
        int result = 0;

        for (Ticket ticket : this.invoice.getTickets()) {
            if (ticket.ticketDate().getDayOfMonth() == date.getDayOfMonth() &&
                    ticket.ticketDate().getMonthValue() == date.getMonthValue() &&
                    ticket.ticketDate().getYear() == date.getYear()) {
                result += ticket.ticketAmount();
            }
        }

        return result;
    }

    public double getTicketCountPerDayPercentage(int soldTickets) {
        return ((double) soldTickets / this.ticketLimitPerDay) * 100;
    }
}


