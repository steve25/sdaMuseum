package main.museum;

import main.utils.MyFormatter;
import main.utils.MyIOClass;

import java.util.ArrayList;

public class Invoice {
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    private Customer customer;

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void printSummary() {
        System.out.println(
                "Ticket owner: " +
                this.customer.getName() +
                ", " +
                this.customer.getAddress() +
                ", " +
                this.customer.getEmail());
        System.out.println("_".repeat(20));
        for (Ticket ticket : this.tickets) {
            System.out.println(
                    MyFormatter.formatDate(ticket.ticketDate()) +
                    " - " +
                    TicketTypes.valueOf(ticket.ticketType()).getTicketHumanReadableName() +
                    " - " +
                    ticket.ticketAmount() +
                    " - " +
                    MyFormatter.formatTwoDecimal(
                            TicketTypes.valueOf(ticket.ticketType())
                                    .calculatePrize() * ticket.ticketAmount()) +
                    "Eur");
        }
        System.out.println();
    }
}
