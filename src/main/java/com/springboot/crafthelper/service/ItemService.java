package com.springboot.crafthelper.service;

import com.springboot.crafthelper.domain.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemService {
    void saveItem(Item item);
    Optional<Item> getItemById(Long id);
    Map<Item, Integer> getFullRecipe(Item item);
    Optional<Item> getItemByName(String name);
    List<Item> getAllItems();
}
