package lt.tietoevry.homework.controller;

import lt.tietoevry.homework.model.HikeResponse;
import lt.tietoevry.homework.model.Season;
import lt.tietoevry.homework.service.ItemService;
import lt.tietoevry.homework.service.MealService;
import lt.tietoevry.homework.service.NightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HikeControllerTest {
    private HikeController hikeController;
    @Mock
    private NightService nightService;
    @Mock
    private ItemService itemService;
    @Mock
    private MealService mealService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        hikeController = new HikeController(itemService, mealService, nightService);
    }

    @Test
    public void testGetItemsForHike() {
        double kilometers = 10.0;
        LocalDate startDate = LocalDate.of(2023, 7, 8);
        Map<String, Integer> prefilledItems = prefillItems();
        Map<Season, Integer> totalNightsMap = prefillTotalNightsMap();

        int totalNights = 5;
        when(nightService.getTotalNights(kilometers, startDate)).thenReturn(totalNightsMap);
        when(itemService.getItemsForHike(totalNightsMap.keySet(), totalNights)).thenReturn(prefilledItems);
        when(mealService.getTotalMeals(totalNightsMap)).thenReturn(15);

        ResponseEntity<HikeResponse> responseEntity = hikeController.getItemsForHike(kilometers, startDate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        HikeResponse hikeResponse = responseEntity.getBody();
        assertEquals(prefilledItems, hikeResponse.getItems());
        assertEquals(15, hikeResponse.getMeals());
        assertEquals(totalNights, hikeResponse.getNights());
    }

    @Test
    public void testGetItemsForHike_InternalServerError() {
        double kilometers = 10.0;
        LocalDate startDate = LocalDate.of(2023, 7, 8);

        when(nightService.getTotalNights(kilometers, startDate)).thenThrow(new RuntimeException());

        ResponseEntity<HikeResponse> responseEntity = hikeController.getItemsForHike(kilometers, startDate);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    private Map<String, Integer> prefillItems() {
        Map<String, Integer> items = new HashMap<>();

        items.put("Tent", 1);
        items.put("Socks", 2);

        return items;
    }


    private Map<Season, Integer> prefillTotalNightsMap() {
        Map<Season, Integer> totalNightsMap = new HashMap<>();

        totalNightsMap.put(Season.SUMMER, 5);

        return totalNightsMap;
    }
}
