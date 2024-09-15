package ru.defezis.sweetdessertbusiness.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChangeDessertRequest {
    private String name;
    private List<Long> ingredientIds;
}
