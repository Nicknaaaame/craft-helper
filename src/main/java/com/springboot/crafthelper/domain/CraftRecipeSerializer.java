package com.springboot.crafthelper.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class CraftRecipeSerializer extends JsonSerializer<Map<Item, Integer>> {
    @Override
    public void serialize(Map<Item, Integer> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (Map.Entry<Item, Integer> entry : value.entrySet()) {
            gen.writeStartObject();
            gen.writeObjectField("key", entry.getKey());
            gen.writeNumberField("value", entry.getValue());
            gen.writeEndObject();
        }
        gen.writeEndArray();
    }
}
