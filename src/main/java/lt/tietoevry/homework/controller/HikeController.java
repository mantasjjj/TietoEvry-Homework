package lt.tietoevry.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lt.tietoevry.homework.model.HikeResponse;
import lt.tietoevry.homework.model.Season;
import lt.tietoevry.homework.service.ItemService;
import lt.tietoevry.homework.service.MealService;
import lt.tietoevry.homework.service.NightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/hike", name = "Hike")
@RequiredArgsConstructor
public class HikeController {
    public final ItemService itemCalculator;
    public final MealService mealCalculator;
    public final NightService nightCalculator;

    //TODO:
    //possibility to add basic authentication
    //possibility to add rate limiter
    @GetMapping("/items")
    @Operation(summary = "Find the duration of your hike, the number of meals and items needed to pack.")
    public ResponseEntity<HikeResponse> getItemsForHike(@RequestParam
                                                        @Min(value = 1, message = "The value must be positive")
                                                        double kilometers,
                                                        @RequestParam @NonNull LocalDate startDate) {
        try {
            Map<Season, Integer> totalNightsMap = nightCalculator.getTotalNights(kilometers, startDate);
            int totalNights = totalNightsMap.values().stream().mapToInt(i -> i).sum();

            return new ResponseEntity<>(HikeResponse.builder()
                    .items(itemCalculator.getItemsForHike(totalNightsMap.keySet(), totalNights))
                    .meals(mealCalculator.getTotalMeals(totalNightsMap))
                    .nights(totalNights)
                    .endDate(startDate.plusDays(totalNights))
                    .build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
