package ru.defezis.sweetdessertbusiness.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.defezis.sweetdessertbusiness.model.Dessert;
import ru.defezis.sweetdessertbusiness.service.DessertService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/desserts", produces = "application/json")
public class DessertController {

    private final DessertService service;

    @GetMapping
    public List<Dessert> list() {
        log.info("list desserts");
        return service.getAll();
    }

    @GetMapping("/{dessertId}")
    public Dessert get(@PathVariable int dessertId) {
        log.info("get dessert with id {}", dessertId);
        return service.getDessert(dessertId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{dessertId}")
    public void update(@RequestBody Dessert dessert, @PathVariable long dessertId) {
        log.info("update dessert {} with id {}", dessert, dessertId);
        dessert.setId(dessertId);
        service.update(dessert, dessertId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody Dessert dessert) {
        log.info("create dessert {}", dessert);
        return service.create(dessert);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{dessertId}")
    public void delete(@PathVariable long dessertId) {
        log.info("delete dessert with id {}", dessertId);
        service.delete(dessertId);
    }

    @GetMapping("/check")
    public ResponseEntity<String> check() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
