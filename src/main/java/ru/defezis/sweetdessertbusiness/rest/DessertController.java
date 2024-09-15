package ru.defezis.sweetdessertbusiness.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.defezis.sweetdessertbusiness.dto.ChangeDessertRequest;
import ru.defezis.sweetdessertbusiness.dto.DessertDto;
import ru.defezis.sweetdessertbusiness.service.DessertService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/desserts", produces = "application/json")
public class DessertController {

    private final DessertService service;

    @GetMapping
    public List<DessertDto> list() {
        log.info("list desserts");
        return service.getAll();
    }

    @GetMapping("/{dessertId}")
    public DessertDto get(@PathVariable int dessertId) {
        log.info("get dessert with id {}", dessertId);
        return service.getDessert(dessertId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{dessertId}")
    public void update(@RequestBody ChangeDessertRequest request, @PathVariable long dessertId) {
        log.info("update dessert {} with id {}", request, dessertId);
        service.update(request, dessertId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Long create(@RequestBody ChangeDessertRequest request) {
        log.info("create dessert {}", request);
        return service.create(request);
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
