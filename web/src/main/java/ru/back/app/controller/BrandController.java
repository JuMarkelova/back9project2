package ru.back.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.client.CoreClient;
import ru.back.app.dto.WebBrandCreateDto;
import ru.back.app.dto.WebBrandDto;


import java.util.List;

@RequestMapping("/web/brands")
@RestController
@RequiredArgsConstructor
public class BrandController {
    private final CoreClient coreClient;

    @GetMapping
    public List<WebBrandDto> getAllBrands(){
        return coreClient.getAllBrands();
    }

    @GetMapping("/id/{id}")
    public WebBrandDto getBrandById(@PathVariable("id") Long id){
        return coreClient.getBrandById(id);
    }

    @GetMapping("/name")
    public WebBrandDto getBrandByName(@RequestParam(value = "name", required = false) String name){
        return coreClient.getBrandByName(name);
    }

    @PostMapping("/create")
    public WebBrandDto createBrand(@RequestBody @Valid WebBrandCreateDto webBrandCreateDto){
        return coreClient.createBrand(webBrandCreateDto);
    }

    @PutMapping("/update")
    public WebBrandDto updateBrand(@RequestBody @Valid WebBrandDto brandUpdateDto){
        return coreClient.updateBrand(brandUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBrand(@PathVariable("id") Long id){
        coreClient.deleteBrand(id);
    }
}
