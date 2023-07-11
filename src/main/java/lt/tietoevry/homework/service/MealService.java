package lt.tietoevry.homework.service;

import lombok.RequiredArgsConstructor;
import lt.tietoevry.homework.model.Season;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MealService {
    //TODO:
    //Algorithm could be improved:
    //1. Find the last day of trip.
    //2. Find the season of the last day of trip.
    //3. Add the number of meals for the last day (currently the algorithm only finds meals when the hiker has to stay the night or
        //when the hiker does not require to stay a single night).
    public int getTotalMeals(Map<Season, Integer> totalNights) {
        return totalNights
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().mealsPerDay * (entry.getValue() != 0 ? entry.getValue() : 1))
                .sum();
    }
}
