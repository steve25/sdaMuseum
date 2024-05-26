import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sk.pocsik.utils.NationalRestDays;

import java.time.LocalDate;
import java.util.ArrayList;

public class NationalRestDaysTest {
    private static NationalRestDays nationalRestDays;

    @BeforeAll
    public static void init() {
        nationalRestDays = new NationalRestDays(LocalDate.now().getYear());
    }

    @Test
    @DisplayName("National rest days not null")
    public void nationalRestDaysArrayNotNull() {
            ArrayList<LocalDate> dates = nationalRestDays.getRestDays();
            Assertions.assertNotEquals(0, dates.size(), "nationalRestDays not null");
    }
}
