package main.museum;

public enum TicketTypes {
    REGULAR("Regular", 5),
    HEALTH_DISORDERS("Health disorders", 0.5, true),
    SENIOR("Senior", 1),
    JUNIOR("Junior", 2),
    STUDENTS("Students", 0.7, true);

    private final String ticketName;
    private final double price;
    private final boolean hasDiscount;

    TicketTypes(String ticketName, double price) {
        this.ticketName = ticketName;
        this.price = price;
        this.hasDiscount = false;
    }

    TicketTypes(String ticketName, double price, boolean hasDiscount) {
        this.ticketName = ticketName;
        this.price = price;
        this.hasDiscount = hasDiscount;
    }

    public double calculatePrize() {
        if (hasDiscount)
            return REGULAR.price * this.price;

        return this.price;
    }

    public String getTicketName() {
        return this.ticketName;
    }
}
