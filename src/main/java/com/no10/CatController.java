package com.no10;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@Validated
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
    public ResponseEntity<CatResponse> insert(@RequestBody CatRequest catRequest, UriComponentsBuilder uriBuilder) {
        Cat cat = catService.insert(catRequest.getName(), catRequest.getSex(), catRequest.getAge());
        URI location = uriBuilder.path("/cats").buildAndExpand(cat.getId()).toUri();
        CatResponse body = new CatResponse("Cat created");
        return ResponseEntity.created(location).body(body);
    }

    @PutMapping("/cats/{name}")
    public CatResponse update(@PathVariable String name, @RequestBody @Validated CatRequest catRequest)
            throws CatNotFoundException {
        catService.update(name, catRequest.getSex(), catRequest.getAge());
        return new CatResponse("Cat updated");
    }
}
