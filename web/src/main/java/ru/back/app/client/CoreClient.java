package ru.back.app.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;

import java.util.List;

@FeignClient(name = "core-client", url = "http://localhost:8080")
public interface CoreClient {

    @GetMapping("/core/test")
    String testCoreConnection();

    @GetMapping("/core/brands")
    List<BrandDto> getAllBrands();

    @GetMapping("/core/brands/id/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id);

    @GetMapping ("core/brands/name")
    BrandDto getBrandByName(@RequestParam(value = "name", required = false) String name);

    @PostMapping("core/brands/create")
    BrandDto createBrand(@RequestBody @Valid BrandCreateDto brandCreateDto);

    @PutMapping("core/brands/update")
    BrandDto updateBrand(@RequestBody @Valid BrandDto brandUpdateDto);

    @DeleteMapping("core/brands/delete/{id}")
    void deleteBrand(@PathVariable("id") Long id);
}
