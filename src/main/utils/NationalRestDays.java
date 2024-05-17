package main.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NationalRestDays {
    private LocalDate[] restDays = null;
    private int restDaysCount = 0;

    public NationalRestDays(int year) {
        Elements downloadedRestDays = this.downloadRestDays(year);
        try {
            this.restDaysCount = downloadedRestDays.size();
            this.restDays = this.setRestDays(downloadedRestDays);
        } catch (NullPointerException e) {
            System.err.println("Cant create holidays dates");
        }
    }

    private Elements downloadRestDays(int year) {
        String url = "https://kalendar.aktuality.sk/sviatky/" + year + "/";
        Elements days = null;
        try {
            Document document = Jsoup.connect(url).get();
            days = document.select("td.value > a");
        } catch (IOException e) {
            System.err.println("Cant download holidays dates!");
        }

        return days;
    }

    private LocalDate[] setRestDays(Elements restDays) {
        LocalDate[] result = new LocalDate[this.restDaysCount];
        for (int i = 0; i < restDays.size(); i++) {
            result[i] = this.formatToLocalDate(restDays.get(i));
        }

        return result;
    }

    private LocalDate formatToLocalDate(Element day) {
        String url = day.attr("href");
        String[] parts = url.split("/");

        String[] result = parts[parts.length - 1].split("-");

        int yearLocalDate = Integer.parseInt(result[0]);
        int monthLocalDate = Integer.parseInt(result[1]);
        int dayLocalDate = Integer.parseInt(result[2]);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.of(yearLocalDate, monthLocalDate, dayLocalDate);

        return LocalDate.parse(date.format(dateTimeFormatter));
    }

    public boolean isRestDay(LocalDate buyDate) {
        for (LocalDate restDate : this.restDays) {
            if (restDate.equals(buyDate)) return true;
        }

        return false;
    }
}
