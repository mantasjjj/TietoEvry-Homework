package lt.tietoevry.homework.service;

import lombok.RequiredArgsConstructor;
import lt.tietoevry.homework.model.Season;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MealService {
    public int getTotalMeals(Map<Season, Integer> totalNights) {
        return totalNights
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().mealsPerDay * (entry.getValue() == 0 ? 1 : entry.getValue()))
                .sum();
    }
}
