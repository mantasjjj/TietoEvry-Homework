package lt.tietoevry.homework.service;

import lombok.RequiredArgsConstructor;
import lt.tietoevry.homework.repository.ItemRepository;
import lt.tietoevry.homework.model.Item;
import lt.tietoevry.homework.model.Season;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Map<String, Integer> getItemsForHike(Set<Season> seasons, int nights) {
        List<Item> allItems = itemRepository.findAll();

        return allItems
                .stream()
                .filter(i -> i.getNightMinimum() != null && nights >= i.getNightMinimum() &&
                        i.getSeasons() != null && i.getSeasons().stream().anyMatch(seasons::contains))
                .peek(i -> {
                    if (Boolean.TRUE.equals(i.getDuplicatesNeeded())) {
                        i.setQuantity(i.getQuantity() * (nights == 0 ? 1 : nights));
                    }
                })
                .collect(Collectors.toMap(Item::getName, Item::getQuantity));
    }

    @Transactional
    public Item add(Item item) {
        return itemRepository.save(item);
    }
}
