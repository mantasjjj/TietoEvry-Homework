package lt.tietoevry.homework.model;

import java.time.Month;
import java.util.Set;

import static java.time.Month.*;

public enum Season {
    WINTER(30, 5, Set.of(DECEMBER, JANUARY, FEBRUARY)),
    SPRING(40, 3, Set.of(MARCH, APRIL, MAY)),
    SUMMER(45, 3, Set.of(JUNE, JULY, AUGUST)),
    AUTUMN(35, 4, Set.of(SEPTEMBER, OCTOBER, NOVEMBER));

    public final double maxKmPerDay;
    public final int mealsPerDay;
    public final Set<Month> months;

    Season(double maxKmPerDay, int mealsPerDay, Set<Month> months) {
        this.maxKmPerDay = maxKmPerDay;
        this.mealsPerDay = mealsPerDay;
        this.months = months;
    }

    public static Season getSeasonForMonth(Month month) {
        for (Season season : Season.values()) {
            if (season.months.contains(month)) {
                return season;
            }
        }
        throw new IllegalArgumentException("No season found for month: " + month);
    }
}
