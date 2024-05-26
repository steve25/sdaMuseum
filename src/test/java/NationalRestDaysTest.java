import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sk.pocsik.utils.NationalRestDays;

import java.time.LocalDate;
import java.util.ArrayList;

public class NationalRestDaysTest {
    private static Integer[] yearsArray;

    @BeforeAll
    public static void init() {
        yearsArray = getYearsArray(10);
    }


    private static Integer[] getYearsArray(int monthAheadToBuy) {
        int currentYear = LocalDate.now().getYear();
        int yearAheadToBuy = LocalDate.now().plusMonths(monthAheadToBuy).getYear();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = currentYear; i <= yearAheadToBuy; i++) {
            result.add(i);
        }

        return result.toArray(new Integer[0]);
    }

    @Test
    @DisplayName("National rest days not null")
    public void nationalRestDaysArrayNotNull() {
        for (Integer year : yearsArray) {
            NationalRestDays nationalRestDays = new NationalRestDays(year);
            LocalDate[] dates = nationalRestDays.getRestDays();
            Assertions.assertNotEquals(0, dates.length, "nationalRestDays for " + year);
        }

    }
}
