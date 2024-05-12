public enum TicketTypes {
    REGULAR("p100"),
    HEALTH_DISORDERS("p50"),
    SENIOR("v1"),
    JUNIOR("v1"),
    STUDENTS("p50");

    private final String ticketTypeValue;

    TicketTypes(String ticketTypeValue) {
        this.ticketTypeValue = ticketTypeValue;
    }

    public String getTicketTypeValue() {
        return this.ticketTypeValue;
    }
}
