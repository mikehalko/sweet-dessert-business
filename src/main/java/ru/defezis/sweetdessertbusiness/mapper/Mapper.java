package ru.defezis.sweetdessertbusiness.mapper;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import ru.defezis.sweetdessertbusiness.dto.DessertDto;
import ru.defezis.sweetdessertbusiness.dto.IngredientDto;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.model.Ingredient;

import java.util.List;

@Component
public class Mapper {

    @NotNull
    public DessertDto toDto(@NotNull Dessert dessert, @NotNull List<Ingredient> ingredients) {
        return DessertDto.builder()
                .id(dessert.getId())
                .name(dessert.getName())
                .ingredients(ingredients.stream().map(this::toDto).toList())
                .build();
    }

    @NotNull
    public IngredientDto toDto(@NotNull Ingredient ingredient) {
        return IngredientDto.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .taste(ingredient.getTaste())
                .build();
    }
}
