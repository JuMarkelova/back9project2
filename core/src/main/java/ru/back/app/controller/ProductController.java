package ru.back.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import ru.back.app.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/id/{id}")
    ProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/products/name")
    ProductDto getProductByName(@RequestParam(value = "name", required = false) String name) {
        return productService.getProductByName(name);
    }

    @PostMapping("/products/create")
    ProductDto createProduct(@RequestBody @Valid ProductCreateDto productCreateDto) {
        return productService.createProduct(productCreateDto);
    }

    @PutMapping("/products/update")
    ProductDto updateProduct(@RequestBody @Valid ProductDto productUpdateDto) {
        return productService.updateProduct(productUpdateDto);
    }

    @DeleteMapping("/products/delete/{id}")
    void updateProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }
}
