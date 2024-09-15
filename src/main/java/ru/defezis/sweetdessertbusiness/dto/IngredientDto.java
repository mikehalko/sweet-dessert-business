package ru.defezis.sweetdessertbusiness.dto;

import lombok.Builder;
import lombok.Data;
import ru.defezis.sweetdessertbusiness.enums.Taste;

@Data
@Builder
public class IngredientDto {
    private Long id;
    private String name;
    private Taste taste;
}
