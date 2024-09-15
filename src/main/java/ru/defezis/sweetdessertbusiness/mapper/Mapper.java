package ru.defezis.sweetdessertbusiness.mapper;

import org.springframework.stereotype.Component;
import ru.defezis.sweetdessertbusiness.dto.DessertDto;
import ru.defezis.sweetdessertbusiness.dto.IngredientDto;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.model.Ingredient;

import java.util.List;

@Component
public class Mapper {

    public DessertDto toDto(Dessert dessert, List<Ingredient> ingredients) {
        return DessertDto.builder()
                .id(dessert.getId())
                .name(dessert.getName())
                .ingredients(ingredients.stream().map(this::toDto).toList())
                .build();
    }

    public IngredientDto toDto(Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .taste(ingredient.getTaste())
                .build();
    }
}
