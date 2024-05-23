package main.museum;

public enum TicketTypes {
    REGULAR("Regular", 5, false),
    HEALTH_DISORDERS("Health disorders", 0.5, true),
    SENIOR("Senior", 1, false),
    JUNIOR("Junior", 2, false),
    STUDENTS("Students", 0.7, true);

    private final String ticketName;
    private final double value;
    private final boolean isPercentageValue;

    TicketTypes(String ticketName, double value, boolean isPercentageValue) {
        this.ticketName = ticketName;
        this.value = value;
        this.isPercentageValue = isPercentageValue;
    }

    public double calculatePrize() {
        if (isPercentageValue)
            return REGULAR.value * this.value;

        return this.value;
    }

    public String getTicketName() {
        return this.ticketName;
    }
}
