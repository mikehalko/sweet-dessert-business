package ru.defezis.sweetdessertbusiness.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DessertDto {
    private Long id;
    private String name;
    private List<IngredientDto> ingredients;
}
