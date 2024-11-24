package ru.back.app.service;

import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    ProductDto getByName(String name);
    ProductDto createProduct(ProductCreateDto productCreateDto);
    List<ProductDto> getAllProducts();
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(Long id);
}
