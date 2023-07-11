package lt.tietoevry.homework.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HikeResponse {
    Map<String, Integer> items;
    int nights;
    int meals;
    LocalDate endDate;
}
