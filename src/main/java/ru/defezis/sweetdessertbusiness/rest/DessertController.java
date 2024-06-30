package ru.defezis.sweetdessertbusiness.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.defezis.sweetdessertbusiness.data.DessertDataService;
import ru.defezis.sweetdessertbusiness.model.Dessert;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/dessert", produces = "application/json")
public class DessertController {

    private final DessertDataService dessertDataService;

    public DessertController(DessertDataService dessertDataService) {
        this.dessertDataService = dessertDataService;
    }

    @GetMapping
    public List<Dessert> list() {
        log.info("list desserts");
        return dessertDataService.list();
    }
}
