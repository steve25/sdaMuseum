import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Museum {
    private final int ticketLimitPerDay = 10;
    private final double ticketPrice = 5;
    private final int arraysSize = (int) ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(2));
    private final LocalDate[] localDate = new LocalDate[arraysSize];
    private final int[] ticketCountPerDay = new int[arraysSize];
    private int ticketCountAllTimes = 0;

    public void setTicketCount(LocalDate date, int ticketCount) {

        int dateIndex = findIndex(date);

        this.ticketCountAllTimes += ticketCount;

        if (dateIndex == -1) {
            int nullIndex = findNextNull();
            this.localDate[nullIndex] = date;
            this.ticketCountPerDay[nullIndex] = ticketCount;
            return;
        }

        this.ticketCountPerDay[dateIndex] += ticketCount;
    }

    public int getTicketCountAllTimes() {
        return ticketCountAllTimes;
    }

    public int getTicketCountPerDay(LocalDate date) {
        int dateIndex = findIndex(date);

        return dateIndex == -1 ? 0 : this.ticketCountPerDay[dateIndex];
    }


    public double getTicketCountPerDayPercentage(LocalDate date) {
        int dateIndex = findIndex(date);

        return dateIndex == -1 ? 0 : ((double) this.ticketCountPerDay[dateIndex] / this.ticketLimitPerDay) * 100;
    }

    private void showDates() {
        for (int i = 0; i < this.localDate.length; i++) {
            if (this.localDate[i] != null) {
                System.out.println(this.localDate[i] + " - " + this.ticketCountPerDay[i]);
            }
        }
    }

    private int findIndex(LocalDate date) {
        for (int i = 0; i < this.localDate.length; i++) {
            if (date.equals(this.localDate[i])) {
                return i;
            }
        }

        return -1;
    }

    private int findNextNull() {
        for (int i = 0; i < this.localDate.length; i++) {
            if (this.localDate[i] == null) return i;
        }

        return -1;
    }

    public int checkAvailableTicketsCount(LocalDate date) {
        int dateIndex = findIndex(date);

        return dateIndex == -1 ? this.ticketLimitPerDay : this.ticketLimitPerDay - this.ticketCountPerDay[dateIndex];
    }

    public double getTicketPrice(int count, int ticketType) {

        String ticketTypeValue = TicketTypes.values()[ticketType - 1].getTicketTypeValue();
        String specialString = ticketTypeValue.substring(0, 1);
        double value = Double.parseDouble(ticketTypeValue.substring(1));

        if (specialString.equalsIgnoreCase("v")) {
            return value * count;
        }

        return this.ticketPrice * (value / 100) * count;
    }
}
