package ru.back.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.back.app.client.CoreClient;
import ru.back.app.dto.WebProductDto;

import java.util.List;

@RequestMapping("/web/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final CoreClient coreClient;

    @GetMapping
    public List<WebProductDto> getAllProducts() {
        return coreClient.getAllProducts();
    }

    @GetMapping("/id/{id}")
    public WebProductDto getProductById(@PathVariable Long id) {
        return coreClient.getProductById(id);
    }

    @GetMapping("/name")
    public WebProductDto getProductByName(@RequestParam(value = "name", required = false) String name) {
        return coreClient.getProductByName(name);
    }

    @PostMapping("/create")
    public WebProductDto createProduct(@RequestBody WebProductDto productDto) {
        return coreClient.createProduct(productDto);
    }

    @PutMapping("/update")
    public WebProductDto updateProduct(@RequestBody WebProductDto productDto) {
        return coreClient.updateProduct(productDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        coreClient.deleteProduct(id);
    }
}

