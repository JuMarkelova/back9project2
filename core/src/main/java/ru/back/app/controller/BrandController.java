package ru.back.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.BrandDto;
import ru.back.app.service.BrandService;

@RestController
@RequiredArgsConstructor
//@SecurityRequirement(name = "site-users")
public class BrandController {

    private final BrandService brandService; //todo

    @GetMapping("/brands")
    String getBrandsView(Model model) {
        model.addAttribute("brands", brandService.getAllBrands());
        return "brands";
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
