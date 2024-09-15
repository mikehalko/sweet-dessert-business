package ru.defezis.sweetdessertbusiness.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.defezis.sweetdessertbusiness.enums.Taste;
import ru.defezis.sweetdessertbusiness.exception.IncorrectDataException;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
public class InMemoryDessertDataService implements DessertDataService {

    private static final AtomicLong ids = new AtomicLong(1);
    private static final List<Ingredient> ingredientList = new ArrayList<>(List.of(
            Ingredient.builder().id(nextId()).name("Sugar").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Salt").taste(Taste.SALTY).build(),
            Ingredient.builder().id(nextId()).name("Egg").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Oil").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Corn Syrup").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Flour").taste(Taste.NEUTRAL).build(),

            Ingredient.builder().id(nextId()).name("Vanilla").taste(Taste.FLAVOR).build(),
            Ingredient.builder().id(nextId()).name("Cacao").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Cinnamon").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Lemon").taste(Taste.SOUR).build(),

            Ingredient.builder().id(nextId()).name("Milk").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Butter").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Sour cream").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Heavy cream").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Condensed milk").taste(Taste.SWEET).build(),

            Ingredient.builder().id(nextId()).name("Graham crackers").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Cream cheese").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Pecan").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Almond").taste(Taste.NEUTRAL).build(),
            Ingredient.builder().id(nextId()).name("Banana").taste(Taste.SWEET).build(),
            Ingredient.builder().id(nextId()).name("Wafer").taste(Taste.SWEET).build(),

            Ingredient.builder().id(nextId()).name("Wine").taste(Taste.ALCOHOL).build(),
            Ingredient.builder().id(nextId()).name("Coffee").taste(Taste.NEUTRAL).build()
    ));
    private static final List<Dessert> dessertList = new ArrayList<>(List.of(
            Dessert.builder().id(nextId()).name("Cheesecake")
                    .ingredientIds(List.of(
                            getIdByName("Cream cheese"),
                            getIdByName("Egg"),
                            getIdByName("Butter"),
                            getIdByName("Vanilla"),
                            getIdByName("Sour cream"),
                            getIdByName("Graham crackers"),
                            getIdByName("Sugar")))
                    .build(),
            Dessert.builder().id(nextId()).name("Ice cream")
                    .ingredientIds(List.of(
                            getIdByName("Milk"),
                            getIdByName("Sugar"),
                            getIdByName("Egg"),
                            getIdByName("Heavy cream")))
                    .build(),
            Dessert.builder().id(nextId()).name("Tiramisu")
                    .ingredientIds(List.of(
                            getIdByName("Sugar"),
                            getIdByName("Salt"),
                            getIdByName("Milk"),
                            getIdByName("Heavy cream"),
                            getIdByName("Egg"),
                            getIdByName("Cacao"),
                            getIdByName("Coffee"),
                            getIdByName("Wine")))
                    .build(),
            Dessert.builder().id(nextId()).name("Brownies")
                    .ingredientIds(List.of(
                            getIdByName("Sugar"),
                            getIdByName("Egg"),
                            getIdByName("Cacao"),
                            getIdByName("Oil"),
                            getIdByName("Vanilla")))
                    .build(),
            Dessert.builder().id(nextId()).name("Pecan pie")
                    .ingredientIds(List.of(
                            getIdByName("Pecan"),
                            getIdByName("Egg"),
                            getIdByName("Corn Syrup"),
                            getIdByName("Sugar"),
                            getIdByName("Vanilla"),
                            getIdByName("Butter"),
                            getIdByName("Salt"),
                            getIdByName("Cinnamon")))
                    .build(),
            Dessert.builder().id(nextId()).name("Chocolate")
                    .ingredientIds(List.of(
                            getIdByName("Cacao")))
                    .build(),
            Dessert.builder().id(nextId()).name("Madeira cake")
                    .ingredientIds(List.of(
                            getIdByName("Butter"),
                            getIdByName("Sugar"),
                            getIdByName("Egg"),
                            getIdByName("Lemon"),
                            getIdByName("Vanilla"),
                            getIdByName("Flour"),
                            getIdByName("Almond")))
                    .build(),
            Dessert.builder().id(nextId()).name("Banana pudding")
                    .ingredientIds(List.of(
                            getIdByName("Milk"),
                            getIdByName("Vanilla"),
                            getIdByName("Banana"),
                            getIdByName("Wafer"),
                            getIdByName("Condensed milk")))
                    .build()
    ));

    /**
     * Получить список всех Десертов.
     *
     * @return список десертов
     */
    @Override
    @NotNull
    public List<Dessert> listDesserts() {
        return List.copyOf(dessertList);
    }

    /**
     * Найти Десерт по заданному идентификатору.
     *
     * @param id идентификатор Десерта
     * @return Десерта, либо пусто, если Десерт с таким id не найден
     */
    @Override
    public Optional<Dessert> getDessertById(long id) {
        return dessertList.stream()
                .filter(dessert -> dessert.getId().equals(id))
                .findFirst();
    }

    /**
     * Добавить новый Десерт.
     *
     * @param dessert десерт
     * @return идентификатор сохраненного десерта
     */
    @Override
    @NotNull
    public Long create(@NotNull Dessert dessert) {
        dessert.setId(nextId());
        dessertList.add(dessert);
        log.info("Created Dessert with id {}", dessert.getId());
        return dessert.getId();
    }

    /**
     * Обновить существующий Десерт.
     *
     * @param updated Десерт для обновления
     * @return обновленный Десерт
     * @throws IncorrectDataException если десерт не найден по идентификатору
     */
    @Override
    public Dessert update(@NotNull Dessert updated) {
        Dessert dessert = dessertList.stream()
                .filter(item -> item.getId().equals(updated.getId()))
                .findFirst()
                .orElseThrow(() -> new IncorrectDataException("Dessert not found"));

        dessert.setName(updated.getName());
        dessert.setIngredientIds(updated.getIngredientIds());
        log.debug("Updated dessert with id: {}", dessert.getId());

        return dessert;
    }

    /**
     * Удалить Десерт из списка по заданному идентификатору.
     *
     * @param id идентификатор
     */
    @Override
    public void removeDessertById(long id) {
        boolean removed = dessertList.removeIf(dessert -> dessert.getId().equals(id));
        if (removed) {
            log.debug("Removed dessert with id: {}", id);
        } else {
            log.warn("Failed to remove dessert with id: {}", id);
        }
    }

    /**
     * Найти Ингридиенты по заданным идентификаторам.
     *
     * @param ids идентификаторы Ингридиентов
     * @return список Ингридиентов
     */
    @Override
    @NotNull
    public List<Ingredient> getIngredientsByIds(List<Long> ids) {
        return ingredientList.stream().filter(i -> ids.contains(i.getId())).toList();
    }

    @NotNull
    private static Long getIdByName(String name) {
        return ingredientList.stream()
                .filter(ingredient -> ingredient.getName().equals(name))
                .map(Ingredient::getId)
                .findFirst()
                .orElseThrow(() -> new IncorrectDataException("No such ingredient: " + name));
    }
    
    @NotNull
    private static Long nextId() {
        return ids.getAndIncrement();
    }
}
