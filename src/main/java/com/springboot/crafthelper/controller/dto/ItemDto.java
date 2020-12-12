package com.springboot.crafthelper.controller.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemDto{
    private Long id;
    private String name;
    private MultipartFile icon;
    private List<CraftRecipeEntry> craftRecipe;
}

