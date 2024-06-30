package ru.defezis.sweetdessertbusiness.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Dessert {
    private Long id;
    private String name;
    private List<Ingredient> ingredients;
}
