package lt.tietoevry.homework.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lt.tietoevry.homework.model.Item;
import lt.tietoevry.homework.model.Season;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUtils {
    public static Item parseItemObject(JSONObject item) {
        JSONObject itemObject = (JSONObject) item.get("item");
        return Item.builder()
                .name((String) itemObject.get("name"))
                .seasons(getSeasonList((List<String>) itemObject.get("season")))
                .quantity(Math.toIntExact((Long) itemObject.get("quantity")))
                .nightMinimum(Math.toIntExact((Long) itemObject.get("nightMinimum")))
                .duplicatesNeeded((Boolean) itemObject.get("duplicatesNeeded"))
                .build();
    }

    public static List<Season> getSeasonList(List<String> strings) {
        return strings.stream()
                .map(Season::valueOf)
                .collect(Collectors.toList());
    }
}
