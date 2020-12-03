package com.springboot.crafthelper.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class ItemDto{
    private Long id;
    private String name;
    private byte[] icon;
    private List<CraftRecipeEntry> craftRecipe;
}

