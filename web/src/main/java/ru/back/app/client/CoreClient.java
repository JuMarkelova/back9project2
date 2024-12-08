package ru.back.app.client;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.WebBrandCreateDto;
import ru.back.app.dto.WebBrandDto;
import ru.back.app.dto.WebProductDto;

import java.util.List;

@FeignClient(name = "core-client", url = "http://localhost:8080")
public interface CoreClient {

    @GetMapping("/core/test")
    String testCoreConnection();

    @GetMapping("/brands")
    List<WebBrandDto> getAllBrands();

    @GetMapping("/brands/id/{id}")
    WebBrandDto getBrandById(@PathVariable("id") Long id);

    @GetMapping ("/brands/name")
    WebBrandDto getBrandByName(@RequestParam(value = "name", required = false) String name);

    @PostMapping("/brands/create")
    WebBrandDto createBrand(@RequestBody @Valid WebBrandCreateDto webBrandCreateDto);

    @PutMapping("/brands/update")
    WebBrandDto updateBrand(@RequestBody @Valid WebBrandDto brandUpdateDto);

    @DeleteMapping("/brands/delete/{id}")
    void deleteBrand(@PathVariable("id") Long id);

    @GetMapping("/products")
    List<WebProductDto> getAllProducts();

    @GetMapping("/products/id/{id}")
    WebProductDto getProductById(@PathVariable("id") Long id);

    @GetMapping("/products/name")
    WebProductDto getProductByName(@RequestParam(value = "name", required = false) String name);

    @PostMapping("/products/create")
    WebProductDto createProduct(@RequestBody @Valid WebProductDto productDto);

    @PutMapping("/products/update")
    WebProductDto updateProduct(@RequestBody @Valid WebProductDto productDto);

    @DeleteMapping("/products/delete/{id}")
    void deleteProduct(@PathVariable("id") Long id);

}
