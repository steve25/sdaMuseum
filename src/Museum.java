import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


public class Museum {
    private final int ticketLimitPerDay = 10;
    private final LocalDate[] localDate = new LocalDate[(int) ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(2))];
    private final int[] ticketCountPerDay = new int[(int) ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusMonths(2))];
    private int ticketCountAllTimes = 0;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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

    public String getTicketCountPerDay(LocalDate date) {
        int dateIndex = findIndex(date);

        String dateString = date.format(this.formatter);

        int result;
        if (dateIndex == -1) {
            result = 0;
        } else  {
            result = this.ticketCountPerDay[dateIndex];
        }

        return dateString + " - " + result + " was sold";
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
            if (this.localDate[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int checkAvailableTicketsCount(LocalDate date) {
        int dateIndex = findIndex(date);

        if (dateIndex == -1) {
            return this.ticketLimitPerDay;
        }

        return this.ticketLimitPerDay - this.ticketCountPerDay[dateIndex];
    }

    public String getTicketCountPerDayPercentage(LocalDate date) {
        int dateIndex = findIndex(date);

        String dateString = date.format(this.formatter);

        int result;
        if (dateIndex == -1) {
            result =  0;
        } else {
            result = (int) (((double) this.ticketCountPerDay[dateIndex] / this.ticketLimitPerDay) * 100);
        }

        return dateString + " - " + result + "% tickets sold";
    }
}
