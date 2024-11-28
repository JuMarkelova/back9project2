package ru.back.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import ru.back.app.entity.Product;
import ru.back.app.mapper.ProductMapper;
import ru.back.app.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {
    @Mock
    ProductRepository productRepository;
    @Mock
    ProductMapper productMapper;
    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void getProductByIdTest() {
        Long id = 1L;
        Product product = new Product(id, "Product 1", 10.0, null);
        ProductDto productDto = new ProductDto(id, "Product 1", 10.0, null);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.getProductById(id);

        verify(productRepository, times(1)).findById(id);
        verify(productMapper, times(1)).productToProductDto(product);
        Assertions.assertEquals(productDto, result, "Result does not match expected");
    }

    @Test
    public void getProductByIdFailureTest() {
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> productService.getProductById(id));
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void getProductByNameTest() {
        String name = "Product 1";
        Product product = new Product(1L, name, 10.0, null);
        ProductDto productDto = new ProductDto(1L, name, 10.0, null);

        when(productRepository.findProductByName(name)).thenReturn(Optional.of(product));
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.getProductByName(name);

        verify(productRepository, times(1)).findProductByName(name);
        verify(productMapper, times(1)).productToProductDto(product);
        Assertions.assertEquals(productDto, result, "Result does not match expected");
    }

    @Test
    public void getProductByNameFailureTest() {
        String name = "Nonexistent Product";

        when(productRepository.findProductByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> productService.getProductByName(name));
        verify(productRepository, times(1)).findProductByName(name);
    }

    @Test
    public void getAllProductsTest() {
        Product product1 = new Product(1L, "Product 1", 10.0, null);
        Product product2 = new Product(2L, "Product 2", 20.0, null);
        List<Product> products = List.of(product1, product2);

        ProductDto productDto1 = new ProductDto(1L, "Product 1", 10.0, null);
        ProductDto productDto2 = new ProductDto(2L, "Product 2", 20.0, null);
        List<ProductDto> productDtos = List.of(productDto1, productDto2);

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.productToProductDto(product1)).thenReturn(productDto1);
        when(productMapper.productToProductDto(product2)).thenReturn(productDto2);

        List<ProductDto> result = productService.getAllProducts();

        verify(productRepository, times(1)).findAll();
        verify(productMapper, times(1)).productToProductDto(product1);
        verify(productMapper, times(1)).productToProductDto(product2);
        Assertions.assertEquals(productDtos, result, "Result list does not match expected");
    }

    @Test
    public void createProductTest() {
        ProductCreateDto productCreateDto = new ProductCreateDto("Product 1", 10.0, null);
        Product product = new Product(1L, "Product 1", 10.0, null);
        ProductDto productDto = new ProductDto(1L, "Product 1", 10.0, null);

        when(productMapper.productDtoToProduct(productCreateDto)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.createProduct(productCreateDto);

        verify(productMapper, times(1)).productDtoToProduct(productCreateDto);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).productToProductDto(product);
        Assertions.assertEquals(productDto, result, "Result does not match expected");
    }

    @Test
    public void updateProductTest() {
        ProductDto productDto = new ProductDto(1L, "Updated Product", 15.0, null);
        Product product = new Product(1L, "Original Product", 10.0, null);

        when(productRepository.findById(productDto.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productMapper.productToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.updateProduct(productDto);

        verify(productRepository, times(1)).findById(productDto.getId());
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).productToProductDto(product);
        Assertions.assertEquals(productDto, result, "Result does not match expected");
    }

    @Test
    public void deleteProductTest() {
        Long id = 1L;

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }
}

