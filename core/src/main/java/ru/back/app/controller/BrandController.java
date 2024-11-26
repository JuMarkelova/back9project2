package ru.back.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.BrandDto;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/brands/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id) {
        return brandService.getBrandById(id);
    }

    @GetMapping("/brands/{name}")
    BrandDto getBrandByName(@RequestParam("name") String name) {
        return brandService.getByName(name);
    }

    @PostMapping("/brands/create")
    BrandDto createBrand(@RequestBody @Valid BrandDto brandCreateDto) {
        return brandService.createBrand(brandCreateDto);
    }

    @PutMapping("/brands/update")
    BrandDto updateBrand(@RequestBody @Valid BrandDto brandUpdateDto) {
        return brandService.updateBrand(brandUpdateDto);
    }

    @DeleteMapping("/brands/delete/{id}")
    void updateBrand(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
    }
}
