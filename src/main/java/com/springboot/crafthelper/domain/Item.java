package com.springboot.crafthelper.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    private Map<Item, Integer> craftRecipe = new HashMap<>();

    @Lob
    private byte[] icon;

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Long id, String name, Map<Item, Integer> craftRecipe) {
        this(id, name);
        this.craftRecipe = craftRecipe;
    }
}
