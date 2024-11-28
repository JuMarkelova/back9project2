package ru.back.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;
import ru.back.app.service.BrandService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brands")
    List<BrandDto> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/brands/id/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id) {
        return brandService.getBrandById(id);
    }

    @GetMapping("/brands/name")
    BrandDto getBrandByName(@RequestParam(value = "name", required = false) String name) {
        return brandService.getBrandByName(name);
    }

    @PostMapping("/brands/create")
    BrandDto createBrand(@RequestBody @Valid BrandCreateDto brandCreateDto) {
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
