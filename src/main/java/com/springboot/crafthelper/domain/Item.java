package com.springboot.crafthelper.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private byte[] icon;

    @ElementCollection
//    @JsonSerialize(using = CraftRecipeSerializer.class)
    private Map<Item, Integer> craftRecipe = new HashMap<>();

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Long id, String name, Map<Item, Integer> craftRecipe) {
        this(id, name);
        this.craftRecipe = craftRecipe;
    }
}
