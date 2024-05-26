package sk.pocsik.museum;

import java.time.LocalDate;

public record Ticket(LocalDate ticketDate, String ticketType, int ticketAmount) {
}
