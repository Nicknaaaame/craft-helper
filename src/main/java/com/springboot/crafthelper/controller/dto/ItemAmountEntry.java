package com.springboot.crafthelper.controller.dto;

import com.springboot.crafthelper.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemAmountEntry {
    private Item item;
    private Integer amount;
}
