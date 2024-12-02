package ru.back.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.client.CoreClient;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;

import java.util.List;

@RequestMapping("/web/brands")
@RestController
@RequiredArgsConstructor
public class BrandController {
    private final CoreClient coreClient;

    @GetMapping
    public List<BrandDto> getAllBrands(){
        return coreClient.getAllBrands();
    }

    @GetMapping("/id/{id}")
    public BrandDto getBrandById(@PathVariable("id") Long id){
        return coreClient.getBrandById(id);
    }

    @GetMapping("/name")
    public BrandDto getBrandByName(@RequestParam(value = "name", required = false) String name){
        return coreClient.getBrandByName(name);
    }

    @PostMapping("/create")
    public BrandDto createBrand(@RequestBody @Valid BrandCreateDto brandCreateDto){
        return coreClient.createBrand(brandCreateDto);
    }

    @PutMapping("/update")
    public BrandDto updateBrand(@RequestBody @Valid BrandDto brandUpdateDto){
        return coreClient.updateBrand(brandUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBrand(@PathVariable("id") Long id){
        coreClient.deleteBrand(id);
    }
}
