package ru.defezis.sweetdessertbusiness.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.defezis.sweetdessertbusiness.data.DessertDataService;
import ru.defezis.sweetdessertbusiness.dto.ChangeDessertRequest;
import ru.defezis.sweetdessertbusiness.dto.DessertDto;
import ru.defezis.sweetdessertbusiness.mapper.Mapper;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.model.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DessertServiceTest {

    @Mock
    private DessertDataService dataService;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private DessertService dessertService;

    @Test
    void getAll() {
        Dessert dessert1 = mock(Dessert.class);
        Dessert dessert2 = mock(Dessert.class);
        Dessert dessert3 = mock(Dessert.class);
        DessertDto dessertDto1 = mock(DessertDto.class);
        DessertDto dessertDto2 = mock(DessertDto.class);
        DessertDto dessertDto3 = mock(DessertDto.class);

        List<Long> ingredientIdsByDessert1 = mock(List.class);
        List<Long> ingredientIdsByDessert2 = mock(List.class);
        List<Long> ingredientIdsByDessert3 = mock(List.class);
        List<Ingredient> ingredientsByDessert1 = mock(List.class);
        List<Ingredient> ingredientsByDessert2 = mock(List.class);
        List<Ingredient> ingredientsByDessert3 = mock(List.class);

        when(dataService.listDesserts())
                .thenReturn(List.of(dessert1, dessert2, dessert3));

        when(dessert1.getIngredientIds()).thenReturn(ingredientIdsByDessert1);
        when(dessert2.getIngredientIds()).thenReturn(ingredientIdsByDessert2);
        when(dessert3.getIngredientIds()).thenReturn(ingredientIdsByDessert3);

        when(dataService.getIngredientsByIds(eq(ingredientIdsByDessert1)))
                .thenReturn(ingredientsByDessert1);
        when(dataService.getIngredientsByIds(eq(ingredientIdsByDessert2)))
                .thenReturn(ingredientsByDessert2);
        when(dataService.getIngredientsByIds(eq(ingredientIdsByDessert3)))
                .thenReturn(ingredientsByDessert3);

        when(mapper.toDto(eq(dessert1), eq(ingredientsByDessert1))).thenReturn(dessertDto1);
        when(mapper.toDto(eq(dessert2), eq(ingredientsByDessert2))).thenReturn(dessertDto2);
        when(mapper.toDto(eq(dessert3), eq(ingredientsByDessert3))).thenReturn(dessertDto3);


        List<DessertDto> actual = dessertService.getAll();


        verify(dataService).listDesserts();
        verify(dataService, times(3)).getIngredientsByIds(anyList());
        verify(mapper, times(3)).toDto(any(Dessert.class), anyList());

        assertNotNull(actual);
        List<DessertDto> expected = List.of(dessertDto1, dessertDto2, dessertDto3);
        assertEquals(expected, actual);
    }

    @Test
    void getAll_dessertWithoutIngredients() {
        Dessert dessert1 = mock(Dessert.class);
        Dessert dessert2 = mock(Dessert.class);
        DessertDto dessertDto1 = mock(DessertDto.class);
        DessertDto dessertDto2 = mock(DessertDto.class);

        List<Long> ingredientIdsByDessert1 = mock(List.class);
        List<Long> ingredientIdsByDessert2 = mock(List.class);
        List<Ingredient> ingredientsByDessert1 = mock(List.class);
        List<Ingredient> ingredientsByDessert2 = Collections.emptyList();

        when(dataService.listDesserts())
                .thenReturn(List.of(dessert1, dessert2));

        when(dessert1.getIngredientIds()).thenReturn(ingredientIdsByDessert1);
        when(dessert2.getIngredientIds()).thenReturn(ingredientIdsByDessert2);

        when(dataService.getIngredientsByIds(eq(ingredientIdsByDessert1)))
                .thenReturn(ingredientsByDessert1);
        when(dataService.getIngredientsByIds(eq(ingredientIdsByDessert2)))
                .thenReturn(ingredientsByDessert2);

        when(mapper.toDto(eq(dessert1), eq(ingredientsByDessert1))).thenReturn(dessertDto1);
        when(mapper.toDto(eq(dessert2), eq(ingredientsByDessert2))).thenReturn(dessertDto2);


        List<DessertDto> actual = dessertService.getAll();


        verify(dataService).listDesserts();
        verify(dataService, times(2)).getIngredientsByIds(anyList());
        verify(mapper, times(2)).toDto(any(Dessert.class), anyList());

        assertNotNull(actual);
        List<DessertDto> expected = List.of(dessertDto1, dessertDto2);
        assertEquals(expected, actual);
    }

    @Test
    void getAll_dessertsNotFound() {
        when(dataService.listDesserts()).thenReturn(Collections.emptyList());

        List<DessertDto> actual = dessertService.getAll();

        verify(dataService).listDesserts();
        verify(dataService, never()).getIngredientsByIds(anyList());
        verify(mapper, never()).toDto(any(Dessert.class), anyList());

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getDessert() {
        Dessert dessert = mock(Dessert.class);
        DessertDto dessertDto = mock(DessertDto.class);
        List<Long> ingredientIdsByDessert = mock(List.class);
        List<Ingredient> ingredientsByDessert = mock(List.class);

        when(dessert.getIngredientIds()).thenReturn(ingredientIdsByDessert);
        when(dataService.getDessertById(eq(1L)))
                .thenReturn(Optional.of(dessert));
        when(dataService.getIngredientsByIds(eq(ingredientIdsByDessert)))
                .thenReturn(ingredientsByDessert);
        when(mapper.toDto(eq(dessert), eq(ingredientsByDessert)))
                .thenReturn(dessertDto);

        DessertDto actual = dessertService.getDessert(1L);

        verify(dataService).getDessertById(anyLong());
        verify(dataService).getIngredientsByIds(anyList());
        verify(mapper).toDto(any(), anyList());

        assertNotNull(actual);
        assertEquals(dessertDto, actual);
    }

    @Test
    void create() {
        when(dataService.create(any())).thenReturn(1L);

        Long actual = dessertService.create(makeRequest("dessert_1", 11L, 12L, 13L));

        ArgumentCaptor<Dessert> captor = ArgumentCaptor.forClass(Dessert.class);
        verify(dataService).create(captor.capture());

        assertEquals(1L, actual);
        Dessert dessertToCreateActual = captor.getValue();
        assertEquals("dessert_1", dessertToCreateActual.getName());
        assertEquals(List.of(11L, 12L, 13L), dessertToCreateActual.getIngredientIds());
    }

    @Test
    void update() {
        Dessert storageDessert = Dessert.builder().id(1L).build();
        when(dataService.getDessertById(eq(1L))).thenReturn(Optional.of(storageDessert));

        dessertService.update(makeRequest("dessert_1", 11L, 12L, 13L), 1L);


        verify(dataService).getDessertById(anyLong());
        ArgumentCaptor<Dessert> captor = ArgumentCaptor.forClass(Dessert.class);
        verify(dataService).update(captor.capture());

        Dessert dessertToCreateActual = captor.getValue();
        assertEquals(1L, dessertToCreateActual.getId());
        assertEquals("dessert_1", dessertToCreateActual.getName());
        assertEquals(List.of(11L, 12L, 13L), dessertToCreateActual.getIngredientIds());
    }

    @Test
    void update_dessertNotFound_thenExceptionThrown() {
        when(dataService.getDessertById(eq(1L))).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                dessertService.update(makeRequest("dessert_1", 11L, 12L, 13L), 1L));

        verify(dataService).getDessertById(anyLong());
        verify(dataService, never()).update(any());
    }

    @Test
    void delete() {
        dessertService.delete(1L);

        verify(dataService).removeDessertById(eq(1L));
    }

    private static ChangeDessertRequest makeRequest(String name, Long... ingredientIds) {
        ChangeDessertRequest request = new ChangeDessertRequest();
        request.setName(name);
        request.setIngredientIds(List.of(ingredientIds));

        return request;
    }
}