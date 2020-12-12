package com.springboot.crafthelper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.crafthelper.controller.dto.CraftRecipeEntry;
import com.springboot.crafthelper.controller.dto.ItemAmountEntry;
import com.springboot.crafthelper.controller.dto.ItemDto;
import com.springboot.crafthelper.domain.Item;
import com.springboot.crafthelper.exception.ItemNotFoundException;
import com.springboot.crafthelper.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    @Qualifier("itemServiceImpl")
    private ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id).map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseThrow(() -> new ItemNotFoundException("Item with id: " + id + " not found"));
    }

    @GetMapping
    public ResponseEntity<List<Item>> getItemsByName(@RequestParam(required = false, defaultValue = "") String name) {
        return new ResponseEntity<>(itemService.getAllItems().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> postItem(@ModelAttribute ItemDto itemDto) {
        Map<Item, Integer> craftRecipe = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<CraftRecipeEntry> recipeJson = new ArrayList<>();
        try {
            recipeJson = objectMapper.readValue(itemDto.getCraftRecipe(), new TypeReference<List<CraftRecipeEntry>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Recipe has not been serialized");
        }
        recipeJson.forEach(recipeEntry -> {
            Item item = itemService.getItemById(recipeEntry.getId())
                    .orElseThrow(() -> new ItemNotFoundException("Recipe has nonexistent item with id: " + recipeEntry.getId()));
            craftRecipe.put(item, recipeEntry.getAmount());
        });
        try {
            Item item = new Item(
                    itemDto.getId(),
                    itemDto.getName(),
                    itemDto.getIcon() == null ? null : itemDto.getIcon().getBytes(),
                    craftRecipe);
            itemService.saveItem(item);
        } catch (IOException e) {
            throw new RuntimeException("Icon has not been serialized", e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
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

//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(RuntimeException ex) {
//        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), ""), HttpStatus.BAD_REQUEST);
//    }
}
