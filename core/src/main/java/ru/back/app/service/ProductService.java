package ru.back.app.service;

import ru.back.app.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    ProductDto getByName(String name);
    ProductDto createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
}
