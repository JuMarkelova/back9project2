package ru.back.app.client;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.back.app.dto.WebBrandDto;
import ru.back.app.dto.WebProductDto;

import java.util.List;

@FeignClient(name = "productClient", url = "http://localhost:8080")
public interface ProductFeignClient {

    @GetMapping("/products")
    List<WebProductDto> getAllProducts();

    @GetMapping("/products/id/{id}")
    WebProductDto getProductById(@PathVariable("id") Long id);

    @PostMapping("/products")
    WebProductDto createProduct(@RequestBody WebProductDto webProductDto);

    @GetMapping ("/product/name")
    WebProductDto getProductByName(@RequestParam(value = "name", required = false) String name);

    @PutMapping("/products/{id}")
    WebProductDto updateProduct(@RequestBody WebProductDto webProductDto);

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable("id") Long id);
}
