package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.back.app.dto.ProductDto;
import ru.back.app.entity.Product;
import ru.back.app.mapper.ProductMapper;
import ru.back.app.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.productToProductDto(product);
    }

    @Override
    public ProductDto getByName(String name) {
        Product product = productRepository.findProductByName(name).orElseThrow();
        return productMapper.productToProductDto(product);
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
