package sk.pocsik.utils;

import java.time.LocalDate;

public class DateInputClass {
    final int monthToAdd;
    int ticketYear;
    int ticketMonth;
    int ticketDay;

    public DateInputClass(int monthToAdd) {
        this.monthToAdd = monthToAdd;
    }

    public LocalDate getTicketDate() {
        this.ticketYear = getYearInput();
        this.ticketMonth = getMonthInput();
        this.ticketDay = getDayInput();
        System.out.println();

        return LocalDate.of(this.ticketYear, this.ticketMonth, this.ticketDay);    }


    public LocalDate getTicketDateMonth() {
        this.ticketYear = getYearInput();
        this.ticketMonth = getMonthInput();
        System.out.println();

        return LocalDate.of(this.ticketYear, this.ticketMonth, 1);
    }

    private int getYearInput() {
        int minYear = LocalDate.now().getYear();
        int maxYear = LocalDate.now().plusMonths(monthToAdd).getYear();

        if (minYear != maxYear) {
            String yearTextMessage = "Choose a year " + ConsoleColors.YELLOW +
                    "(min: " + minYear + ", max: " + maxYear + ")" +
                    ConsoleColors.RESET + ": ";

            return MyIOClass.intInputValidationBetween(
                    yearTextMessage,
                    "Please enter a valid year: ",
                    minYear, maxYear);
        }
        return LocalDate.now().getYear();
    }

    private int getMonthInput() {
        int minMonth;
        int maxMonth;
        if (this.ticketYear == LocalDate.now().getYear()) {
            minMonth = LocalDate.now().getMonthValue();
            maxMonth = 12;
        } else {
            minMonth = 1;
            maxMonth = LocalDate.now().plusMonths(this.monthToAdd).getMonthValue();
        }
        String monthTextMessage = "Choose a month " + ConsoleColors.YELLOW +
                "(min: " + minMonth + ", max: " + maxMonth + ")" +
                ConsoleColors.RESET + ": ";

        return MyIOClass.intInputValidationBetween(
                monthTextMessage,
                "Please enter a valid month: ",
                minMonth, maxMonth);
    }

    private int getDayInput() {
        int minDayInMonth = getMinDayInMonth();
        int maxDayInMonth = getMaxDayInMonth();
        String dayTextMessage = "Choose a day " + ConsoleColors.YELLOW +
                "(min: " + minDayInMonth + ", max: " + maxDayInMonth + ")" +
                ConsoleColors.RESET + ": ";

        return MyIOClass.intInputValidationBetween(
                dayTextMessage,
                "Please enter a valid day: ",
                minDayInMonth, maxDayInMonth);
    }

    private int getMinDayInMonth() {
        int actualDay = LocalDate.of(this.ticketYear, this.ticketMonth, LocalDate.now().getDayOfMonth()).getDayOfMonth();

        LocalDate ticketBuyDate = LocalDate.of(this.ticketYear, this.ticketMonth, LocalDate.now().getDayOfMonth());

        return ticketBuyDate.equals(LocalDate.now()) ? actualDay : 1;
    }

    private int getMaxDayInMonth() {
        int actualDay = LocalDate.of(this.ticketYear, this.ticketMonth, LocalDate.now().getDayOfMonth()).getDayOfMonth();
        int maxDayInMonth = LocalDate.of(this.ticketYear, this.ticketMonth, 1).lengthOfMonth();

        LocalDate ticketBuyDate = LocalDate.of(this.ticketYear, this.ticketMonth, LocalDate.now().getDayOfMonth());
        LocalDate maxDateToBuy = LocalDate.now().plusMonths(monthToAdd);

        return ticketBuyDate.equals(maxDateToBuy) ? actualDay : maxDayInMonth;
    }
}
