package ru.defezis.sweetdessertbusiness.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.defezis.sweetdessertbusiness.data.DessertInMemoryDataService;
import ru.defezis.sweetdessertbusiness.dto.ChangeDessertRequest;
import ru.defezis.sweetdessertbusiness.dto.DessertDto;
import ru.defezis.sweetdessertbusiness.mapper.Mapper;
import ru.defezis.sweetdessertbusiness.model.Dessert;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DessertService {
    private final DessertInMemoryDataService dataService;
    private final Mapper mapper;

    /**
     * Получить список всех Десертов.
     *
     * @return список десертов
     */
    @NotNull
    public List<DessertDto> getAll() {
        return dataService.listDesserts().stream()
                .map(i -> mapper.toDto(i, dataService.getIngredientsByIds(i.getIngredientIds())))
                .toList();
    }

    /**
     * Метод вернет Десерт по заданному идентификатору.
     *
     * @param id идентификатор Десерта
     * @return модель Десерта
     * @throws RuntimeException если Десерт с таким id не найден
     */
    @NotNull
    public DessertDto getDessert(long id) {
        return dataService.getDessertById(id)
                .map(i -> mapper.toDto(i, dataService.getIngredientsByIds(i.getIngredientIds())))
                .orElseThrow(() -> new RuntimeException("Dessert id must be prefer to dessert"));
    }

    /**
     * Сохранить новый Десерт.
     *
     * @param toCreate новый Десерт
     * @return идентификатор сохраненного десерта
     */
    @NotNull
    public Long create(@NotNull ChangeDessertRequest toCreate) {
        Dessert dessert = Dessert.builder()
                .name(toCreate.getName())
                .ingredientIds(toCreate.getIngredientIds())
                .build();

        return dataService.create(dessert);
    }

    /**
     * Обновить существующий Десерт.
     *
     * @param toUpdate Десерт с данными для обновления
     * @param dessertId идентификатор Десерта для обновления
     */
    public void update(@NotNull ChangeDessertRequest toUpdate, long dessertId) {
        Dessert dessert = dataService.getDessertById(dessertId)
                .orElseThrow(() -> new RuntimeException("Dessert id must be prefer to dessert"));
        dessert.setName(toUpdate.getName());
        dessert.setIngredientIds(toUpdate.getIngredientIds());

        dataService.update(dessert);
    }

    /**
     * Удалить Десерт по заданному идентификатору.
     *
     * @param dessertId идентификатор удаляемого Десерта
     */
    public void delete(long dessertId) {
        dataService.removeById(dessertId);
    }
}
