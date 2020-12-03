package com.springboot.crafthelper.service;

import com.springboot.crafthelper.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MockItemServiceImplTest {
    ItemService itemService = new MockItemServiceImpl();

    {
        init();
    }

    void init() {
        itemService.saveItem(new Item(1L, "Серебрянная руда"));
        itemService.saveItem(new Item(4L, "Кожанные ремни"));
        itemService.saveItem(new Item(5L, "Куски кожи", new HashMap<>(){{
            put(itemService.getItemById(4L).get(), 2);
        }}));
        itemService.saveItem(new Item(2L, "Серебрянный слиток", new HashMap<>() {{
            put(itemService.getItemById(1L).get(), 2);
        }}));
        itemService.saveItem(new Item(3L, "Серебрянный меч", new HashMap<>() {{
            put(itemService.getItemById(2L).get(), 2);
            put(itemService.getItemById(5L).get(), 3);
        }}));
        itemService.saveItem(new Item(6L, "Говно", new HashMap<>(){{
            put(itemService.getItemById(3L).get(), 2);
        }}));
    }

    @Test
    void getFullRecipe() {
        var fullRecipe = itemService.getFullRecipe(itemService.getItemById(6L).get());
        System.out.println(fullRecipe);

    }
}
class ItemDto{
    private Long id;
    private String name;
    private byte[] icon;
    //AbstractMap.SimpleEntry<String, Integer>
//    private List<CraftRecipeEntry> craftRecipe;// = new HashMap<>();
    private Map<ItemDto, Integer> craftRecipe;
}
