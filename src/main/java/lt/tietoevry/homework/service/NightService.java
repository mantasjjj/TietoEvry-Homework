package lt.tietoevry.homework.service;

import lombok.RequiredArgsConstructor;
import lt.tietoevry.homework.model.Season;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static lt.tietoevry.homework.model.Season.getSeasonForMonth;

@Service
@RequiredArgsConstructor
public class NightService {
    public Map<Season, Integer> getTotalNights(double kilometers, LocalDate startDate) {
        double totalKilometersHiked = 0;
        Map<Season, Integer> totalNightsMap = new HashMap<>();
        Season currentSeason = getSeasonForMonth(startDate.getMonth());
        int totalNightsForSeason = 0;

        while (totalKilometersHiked < kilometers) {
            if (currentSeason != getSeasonForMonth(startDate.getMonth())) {
                currentSeason = getSeasonForMonth(startDate.getMonth());
                totalNightsForSeason = totalNightsMap.get(currentSeason) != null ? totalNightsMap.get(currentSeason) : 0;
            }
            totalKilometersHiked += currentSeason.maxKmPerDay;
            startDate = startDate.plusDays(1);
            totalNightsForSeason++;
            totalNightsMap.put(currentSeason, totalNightsForSeason);
        }

        totalNightsMap.put(currentSeason,
                totalNightsMap.get(currentSeason) != null ? totalNightsMap.get(currentSeason) - 1 : 0);

        return totalNightsMap;
    }
}
