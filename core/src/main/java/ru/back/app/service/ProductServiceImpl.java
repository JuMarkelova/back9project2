package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import ru.back.app.entity.Product;
import ru.back.app.mapper.ProductMapper;
import ru.back.app.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
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
    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        Product product = productMapper.productDtoToProduct(productCreateDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDto(savedProduct);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setBrandId(productDto.getBrandId());
        Product updatedProduct = productRepository.save(product);
        return productMapper.productToProductDto(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
