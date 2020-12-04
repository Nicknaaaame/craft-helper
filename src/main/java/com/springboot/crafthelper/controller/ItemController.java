package com.springboot.crafthelper.controller;

import com.springboot.crafthelper.controller.dto.ItemAmountEntry;
import com.springboot.crafthelper.controller.dto.ItemDto;
import com.springboot.crafthelper.domain.Item;
import com.springboot.crafthelper.exception.ItemNotFoundException;
import com.springboot.crafthelper.service.ItemService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    @Qualifier("mockItemServiceImpl")
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<HttpStatus> postItem(@NotNull @RequestBody ItemDto itemDto) {
        Map<Item, Integer> craftRecipe = new HashMap<>();
        itemDto.getCraftRecipe().forEach(recipeEntry -> {
            Item item = itemService.getItemById(recipeEntry.getId())
                    .orElseThrow(() -> new ItemNotFoundException("Recipe has nonexistent item with id: " + recipeEntry.getId()));
            craftRecipe.put(item, recipeEntry.getAmount());
        });

        Item item = new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getIcon(),
                craftRecipe);
        itemService.saveItem(item);
        System.out.println(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItemsByName(@RequestParam(required = false, defaultValue = "") String name) {
        return new ResponseEntity<>(itemService.getAllItems().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id).map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + id + " not found"));
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<List<ItemAmountEntry>> getCraftRecipe(@PathVariable Long id) {
        Item item = getItemById(id).getBody();
        return new ResponseEntity<>(item.getCraftRecipe().entrySet().stream().map(
                entry -> new ItemAmountEntry(entry.getKey(), entry.getValue())).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/fullrecipe/{id}")
    public ResponseEntity<Map<Item, Integer>> getFullCraftRecipe(@PathVariable Long id) {
        Item item = getItemById(id).getBody();
        Map<Item, Integer> fullRecipe = itemService.getFullRecipe(item);
        return new ResponseEntity<>(fullRecipe, HttpStatus.OK);
    }

    @ExceptionHandler({ItemNotFoundException.class})
    public ResponseEntity<ErrorResponse> itemNotFoundHandler(ItemNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ""), HttpStatus.BAD_REQUEST);
    }
}
