package lt.tietoevry.homework.service;

import lt.tietoevry.homework.model.Item;
import lt.tietoevry.homework.model.Season;
import lt.tietoevry.homework.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemServiceTest {
    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    public void testRetrieveItems() {
        Set<Season> seasons = new HashSet<>(Arrays.asList(Season.SUMMER, Season.SPRING));
        int nights = 5;

        Item item1 = Item.builder()
                .name("Tent")
                .nightMinimum(1)
                .quantity(1)
                .duplicatesNeeded(false)
                .seasons(Arrays.asList(Season.SUMMER, Season.SPRING))
                .build();

        Item item2 = Item.builder()
                .name("Tissues")
                .nightMinimum(0)
                .quantity(2)
                .duplicatesNeeded(true)
                .seasons(List.of(Season.SUMMER))
                .build();

        List<Item> allItems = Arrays.asList(item1, item2);
        when(itemRepository.findAll()).thenReturn(allItems);

        Map<String, Integer> itemsForHike = itemService.getItemsForHike(seasons, nights);

        verify(itemRepository, times(1)).findAll();

        assertEquals(2, itemsForHike.size());
        assertEquals(1, itemsForHike.get("Tent"));
        assertEquals(10, itemsForHike.get("Tissues"));
    }

    @Test
    public void testAddItem() {
        Item item = Item.builder()
                .name("Tent")
                .nightMinimum(1)
                .quantity(1)
                .duplicatesNeeded(false)
                .seasons(Arrays.asList(Season.SUMMER, Season.SPRING))
                .build();

        Item savedItem = Item.builder()
                .name("Tent")
                .nightMinimum(1)
                .quantity(1)
                .duplicatesNeeded(false)
                .seasons(Arrays.asList(Season.SUMMER, Season.SPRING))
                .build();
        when(itemRepository.save(any(Item.class))).thenReturn(savedItem);

        Item result = itemService.add(item);

        verify(itemRepository, times(1)).save(item);

        assertEquals(savedItem, result);
    }
}