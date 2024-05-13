package museum;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Museum {

    private final int ticketLimitPerDay = 10;
    private final double ticketPrice = 5;
    private final int monthAheadToBuy = 2;
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

    public int getMonthAheadToBuy() {
        return monthAheadToBuy;
    }

    public int getTicketCountPerDay(LocalDate date) {
        int dateIndex = findIndex(date);

        return dateIndex == -1 ? 0 : this.ticketCountPerDay[dateIndex];
    }

    public double getTicketCountPerDayPercentage(LocalDate date) {
        int dateIndex = findIndex(date);

        return dateIndex == -1 ? 0 : ((double) this.ticketCountPerDay[dateIndex] / this.ticketLimitPerDay) * 100;
    }

    public int getTicketCountPerMonth(int month) {
        int[] arrayOfTickets = new int[this.localDate.length];
        int result = 0;

        for (int i = 0; i < this.localDate.length; i++) {
            if (this.localDate[i] != null && this.localDate[i].getMonthValue() == month) {
                arrayOfTickets[i] = this.ticketCountPerDay[i];
            }
        }

        for (int number : arrayOfTickets) {
            result += number;
        }

        return result;
    }

    public double getTicketCountPerMonthPercentage(int month, int totalSoldTicket) {
        int actualDay = LocalDate.now().getDayOfMonth();
        int totalDays = LocalDate.of(LocalDate.now().getYear(), month, 1).lengthOfMonth();

        if (month == LocalDate.now().getMonthValue()) {
            return ((double) totalSoldTicket / (this.ticketLimitPerDay * (totalDays - actualDay))) * 100;
        }

        if (LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + this.monthAheadToBuy, 1).getMonthValue() == month) {
            return ((double) totalSoldTicket / (this.ticketLimitPerDay * actualDay)) * 100;
        }

        return ((double) totalSoldTicket / (this.ticketLimitPerDay * totalDays)) * 100;

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
