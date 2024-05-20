package main.museum;

import java.util.ArrayList;

public class Invoice {
    private final ArrayList<Ticket> tickets = new ArrayList<>();

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTickets(Ticket ticket) {
        this.tickets.add(ticket);
    }
}
