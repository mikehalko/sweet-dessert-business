package ru.defezis.sweetdessertbusiness.data;

import org.jetbrains.annotations.NotNull;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface DessertDataService {
    @NotNull
    List<Dessert> listDesserts();

    Optional<Dessert> getDessertById(long id);

    @NotNull
    Long create(@NotNull Dessert dessert);

    @NotNull
    Dessert update(@NotNull Dessert updated);

    void removeDessertById(long id);

    @NotNull
    List<Ingredient> getIngredientsByIds(List<Long> ids);
}
