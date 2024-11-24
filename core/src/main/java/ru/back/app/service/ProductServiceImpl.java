package ru.back.app.service;

import ru.back.app.dto.ProductDto;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    @Override
    public ProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public ProductDto getByName(String name) {
        return null;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return List.of();
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
