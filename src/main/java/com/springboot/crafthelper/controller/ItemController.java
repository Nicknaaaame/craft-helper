package com.springboot.crafthelper.controller;

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

import java.util.List;
import java.util.Map;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> postItem(@ModelAttribute ItemDto itemDto) {
        itemService.saveItem(itemService.createItemFrom(itemDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<HttpStatus> updateItem(@ModelAttribute ItemDto itemDto) {
        itemService.saveItem(itemService.createItemFrom(itemDto));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

    //--------unnecessary points

    @GetMapping
    public ResponseEntity<List<Item>> getItemsByName(@RequestParam(required = false, defaultValue = "") String name) {
        return new ResponseEntity<>(itemService.getAllItems().stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList()), HttpStatus.OK);
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
