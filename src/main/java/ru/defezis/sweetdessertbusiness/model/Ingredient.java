package ru.defezis.sweetdessertbusiness.model;

import lombok.Builder;
import lombok.Data;
import ru.defezis.sweetdessertbusiness.enums.Flavor;

@Data
@Builder
public class Ingredient {
    private Long id;
    private String name;
    private Flavor flavor;
}
