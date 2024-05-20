package main.museum;

import main.utils.DateInputClass;
import main.utils.MyFormatter;
import main.utils.NationalRestDays;
import main.utils.menus.RegularMenu;
import main.utils.MyIOClass;
import main.utils.menus.TicketTypeMenu;

import java.time.LocalDate;

public class Museum {
    Invoice invoice = new Invoice();
    NationalRestDays nationalRestDays;

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
        MyIOClass.printMessage("Welcome our reservation system.");
        this.mainLoop();
    }

    private void mainLoop() {
        while (true) {
            RegularMenu mainRegularMenu = new RegularMenu(new String[]{"Buy tickets", "Admin panel", "Exit"});
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
                MyIOClass.printMessage("Sorry, there are no free tickets for this date.");
                return;
            }

            if (nationalRestDays.isRestDay(ticketDate)) {
                MyIOClass.printMessage("Sorry, our museum is closed this date.");
                return;
            }

            TicketTypeMenu ticketTypeRegularMenu = new TicketTypeMenu("Which type of ticket you want to buy?");
            String ticketType = ticketTypeRegularMenu.getResult();

            System.out.println();
            int maxTicketsValue = Math.min(3, freeTicketsAmount);
            int ticketAmount = MyIOClass.intInputValidationBetween("How many tickets you want to buy? (min: 1, max: " + maxTicketsValue + "): ", "Enter a valid number: ", 1, maxTicketsValue);

            Ticket ticket = new Ticket(ticketDate, ticketType, ticketAmount);

            invoice.addTickets(ticket);

            System.out.println();

            boolean isAnotherTicket = MyIOClass.answerYesNo("Do you want buy another tickets? (y/n) ");
            if (!isAnotherTicket) {
                break;
            }

            isSameDate = MyIOClass.answerYesNo("Do you want buy a tickets for the same date? - " + MyFormatter.formatDate(ticketDate) + " (y/n) ");
        }
    }

    private void adminPanel() {
        while (true) {
            RegularMenu adminPanelRegularMenu = new RegularMenu(new String[]{"Tickets sold - all time", "Tickets sold - month", "Tickets sold - day", "Cancel"});
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


