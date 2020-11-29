package com.springboot.crafthelper.service;

import com.springboot.crafthelper.domain.Item;
import com.springboot.crafthelper.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Map<Item, Integer> getFullRecipe(Item item) {
        Map<Item, Integer> craftRecipe = item.getCraftRecipe();
        if (craftRecipe.isEmpty())
            return new HashMap<>();
        else {
            Map<Item, Integer> fullRecipe = new HashMap<>();
            for (Map.Entry<Item, Integer> entry : craftRecipe.entrySet()) {
                Map<Item, Integer> currFullRecipe = getFullRecipe(entry.getKey());
                Integer itemAmount = entry.getValue();
                if (currFullRecipe.isEmpty()) {
                    fullRecipe.merge(entry.getKey(), itemAmount, (prev, next) -> prev + next * itemAmount);
                } else {
                    currFullRecipe.forEach((currItem, currAmount) ->
                            fullRecipe.merge(currItem, currAmount * itemAmount, (prev, next) -> prev + next * itemAmount));
                }

            }
            return fullRecipe;
        }
    }

    @Override
    public Optional<Item> getItemByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> result = new ArrayList<>();
        itemRepository.findAll().forEach(result::add);
        return result;
    }
}
