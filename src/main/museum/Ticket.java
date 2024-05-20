package main.museum;

import java.time.LocalDate;

public record Ticket(LocalDate ticketDate, String ticketType, int ticketAmount) {
}
