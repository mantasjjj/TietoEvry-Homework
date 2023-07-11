package lt.tietoevry.homework.service;

import lt.tietoevry.homework.model.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NightServiceTest {
    private NightService nightService;

    @BeforeEach
    public void setup() {
        nightService = new NightService();
    }

    @Test
    public void testGetTotalNights() {
        double kilometers = 200;
        LocalDate startDate = LocalDate.of(2023, 5, 31);

        Map<Season, Integer> totalNights = nightService.getTotalNights(kilometers, startDate);

        assertEquals(1, totalNights.get(Season.SPRING));
        assertEquals(3, totalNights.get(Season.SUMMER));
    }

    @Test
    public void testGetTotalNights_FractionalKilometers() {
        double kilometers = 75.5;
        LocalDate startDate = LocalDate.of(2023, 9, 1);

        Map<Season, Integer> totalNights = nightService.getTotalNights(kilometers, startDate);

        assertEquals(2, totalNights.get(Season.AUTUMN));
    }
}
