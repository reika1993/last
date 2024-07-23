package com.no10;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }


    @GetMapping("/cats")
    public List<Cat> getCat(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) Integer age) throws CatNotFoundException {
        if ((Objects.nonNull(name) ? 1 : 0) + (Objects.nonNull(sex) ? 1 : 0) + (Objects.nonNull(age) ? 1 : 0) > 1) {
            throw new CatNotFoundException("一度に許可されるパラメタはひとつだけです。");
        }

        return catService.findCat(name, sex, age);

    }

    @PostMapping("/cats")
    public Map<String, String> insertCat(@RequestBody @Validated CatRequest catRequest) {
        return Map.of("status", String.valueOf(HttpStatus.CREATED),
                "message", "successfully created");
    }

    @PatchMapping("/cats/{name}")
    public Map<String, String> updateCat(@PathVariable String name, @RequestBody @Validated CatRequest catRequest) throws CatNotFoundException {
        catService.updateCat(name, catRequest.getSex(), catRequest.getAge());
        return Map.of("status", String.valueOf(HttpStatus.OK),
                "message", "successfully updated");
    }
}

