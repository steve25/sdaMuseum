package sk.pocsik.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NationalRestDays {
    private final ArrayList<LocalDate> restDays = new ArrayList<>();

    public NationalRestDays(int year) {
        System.out.println();
        System.out.print("Downloading slovak national rest days...");

        do {
            try {
                Elements downloadedRestDays = this.downloadRestDays(year);
                this.setRestDays(downloadedRestDays);
                year--;
            } catch (IOException e) {
                System.err.print("Cant download holidays dates\n");
            }
        } while (year == LocalDate.now().getYear());

        System.out.print(" done\n");
    }

    public ArrayList<LocalDate> getRestDays() {
        return restDays;
    }

    private Elements downloadRestDays(int year) throws IOException {
        String url = "https://kalendar.aktuality.sk/sviatky/" + year + "/";
        Document document = Jsoup.connect(url).get();

        return document.select("td.value > a");
    }

    private void setRestDays(Elements restDaysElement) {
        for (Element element : restDaysElement) {
            restDays.add(this.formatToLocalDate(element));
        }
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
            if (restDate.equals(buyDate))
                return true;
        }

        return false;
    }
}
