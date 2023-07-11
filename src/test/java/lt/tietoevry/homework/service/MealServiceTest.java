package lt.tietoevry.homework.service;
import lt.tietoevry.homework.model.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealServiceTest {
    private MealService mealService;

    @BeforeEach
    public void setup() {
        mealService = new MealService();
    }

    @Test
    public void testGetTotalMeals() {
        Map<Season, Integer> totalNights = new HashMap<>();
        totalNights.put(Season.WINTER, 3);
        totalNights.put(Season.SPRING, 1);

        int totalMeals = mealService.getTotalMeals(totalNights);

        assertEquals(18, totalMeals);
    }

    @Test
    public void testGetTotalMeals_ZeroNights() {
        Map<Season, Integer> totalNights = new HashMap<>();
        totalNights.put(Season.SUMMER, 0);

        int totalMeals = mealService.getTotalMeals(totalNights);

        assertEquals(3, totalMeals);
    }

    @Test
    public void testGetTotalMeals_EmptyMap() {
        Map<Season, Integer> totalNights = new HashMap<>();

        int totalMeals = mealService.getTotalMeals(totalNights);

        assertEquals(0, totalMeals);
    }
}
