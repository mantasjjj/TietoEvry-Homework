package lt.tietoevry.homework.jpa;

import lombok.RequiredArgsConstructor;
import lt.tietoevry.homework.service.ItemService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;

import static lt.tietoevry.homework.utils.ItemUtils.parseItemObject;

@Component
@RequiredArgsConstructor
public class AbstractCommandLineRunner implements CommandLineRunner {
    private final ItemService itemService;

    @Override
    public void run(String... args) {
        fillAllItemsFromJson();
    }

    private void fillAllItemsFromJson() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("src/main/resources/items.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray jsonItems = (JSONArray) obj;

            jsonItems.forEach(item -> itemService.add(parseItemObject((JSONObject) item)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
