package ru.defezis.sweetdessertbusiness.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.defezis.sweetdessertbusiness.dto.DessertDto;
import ru.defezis.sweetdessertbusiness.dto.IngredientDto;
import ru.defezis.sweetdessertbusiness.enums.Taste;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.model.Ingredient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MapperTest {

    @InjectMocks
    private Mapper mapper;

    @Test
    void toDto_dessert() {
        Dessert dessert = makeDessert(1L, "dessert", 11L, 12L, 13L);
        Ingredient ingredient1 = makeIngredient(11L, "ingredient_1", Taste.SWEET);
        Ingredient ingredient2 = makeIngredient(12L, "ingredient_2", Taste.SOUR);
        Ingredient ingredient3 = makeIngredient(13L, "ingredient_3", Taste.SALTY);

        DessertDto result = mapper.toDto(dessert, List.of(ingredient1, ingredient2, ingredient3));

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("dessert", result.getName());

        List<IngredientDto> ingredientsByDessertActual = result.getIngredients();
        assertNotNull(ingredientsByDessertActual);
        assertEquals(3, ingredientsByDessertActual.size());

        assertEquals(11L, ingredientsByDessertActual.get(0).getId());
        assertEquals("ingredient_1", ingredientsByDessertActual.get(0).getName());
        assertEquals(Taste.SWEET, ingredientsByDessertActual.get(0).getTaste());

        assertEquals(12L, ingredientsByDessertActual.get(1).getId());
        assertEquals("ingredient_2", ingredientsByDessertActual.get(1).getName());
        assertEquals(Taste.SOUR, ingredientsByDessertActual.get(1).getTaste());

        assertEquals(13L, ingredientsByDessertActual.get(2).getId());
        assertEquals("ingredient_3", ingredientsByDessertActual.get(2).getName());
        assertEquals(Taste.SALTY, ingredientsByDessertActual.get(2).getTaste());
    }

    @Test
    void toDto_ingredient() {
        Ingredient ingredient1 = makeIngredient(11L, "ingredient_1", Taste.SWEET);

        IngredientDto result = mapper.toDto(ingredient1);

        assertNotNull(result);
        assertEquals(11L, result.getId());
        assertEquals("ingredient_1", result.getName());
        assertEquals(Taste.SWEET, result.getTaste());
    }

    private static Ingredient makeIngredient(Long id, String name, Taste taste) {
        return Ingredient.builder().id(id).name(name).taste(taste).build();
    }

    private static Dessert makeDessert(Long id, String name, Long... ingredientIds) {
        return Dessert.builder().id(1L).name(name).ingredientIds(List.of(ingredientIds)).build();
    }
}