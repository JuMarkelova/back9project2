package ru.back.app.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.ProductDto;
import ru.back.app.service.ProductService;

@RestController
@RequiredArgsConstructor
//@SecurityRequirement(name = "site-users")
public class ProductController {
    private final ProductService productService; //todo

    @GetMapping("/products")
    String getProductsView(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/{id}")
    ProductDto getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/products/{name}")
    ProductDto getProductByName(@RequestParam("name") String name) {
        return productService.getByName(name);
    }

    @PostMapping("/products/create")
    ProductDto createProduct(@RequestBody @Valid ProductDto productCreateDto) {
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
