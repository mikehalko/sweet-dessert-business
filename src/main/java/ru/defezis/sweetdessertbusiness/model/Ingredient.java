package ru.defezis.sweetdessertbusiness.model;

import lombok.Builder;
import lombok.Data;
import ru.defezis.sweetdessertbusiness.enums.Taste;

@Data
@Builder
public class Ingredient {
    private Long id;
    private String name;
    private Taste taste;
}
