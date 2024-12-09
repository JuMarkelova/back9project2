package ru.back.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.client.CoreClient;
import ru.back.app.client.ProductFeignClient;
import ru.back.app.dto.WebProductDto;

import java.util.List;

@RequestMapping("/web/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductFeignClient productFeignClient;

    @GetMapping
    public List<WebProductDto> getAllProducts() {
        return productFeignClient.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public WebProductDto getProductById(@PathVariable("id") Long id) {
        return productFeignClient.getProductById(id);
    }

    @GetMapping("/name")
    public WebProductDto getProductByName(@RequestParam(value = "name", required = false) String name) {
        return productFeignClient.getProductByName(name);
    }

    @PostMapping("/create")
    public WebProductDto createProduct(@RequestBody @Valid WebProductDto productDto) {
        return productFeignClient.createProduct(productDto);
    }

    @PutMapping("/update")
    public WebProductDto updateProduct(@RequestBody @Valid WebProductDto productDto) {
        return productFeignClient.updateProduct(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productFeignClient.deleteProduct(id);
    }
}

