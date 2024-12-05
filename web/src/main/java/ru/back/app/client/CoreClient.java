package ru.back.app.client;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import ru.back.app.dto.WebProductDto;

import java.util.List;

@FeignClient(name = "core-client", url = "http://localhost:8080")
public interface CoreClient {

    @GetMapping("/core/test")
    String testCoreConnection();

    @GetMapping("/products")
    List<WebProductDto> getAllProducts();

    @GetMapping("/products/id/{id}")
    WebProductDto getProductById(@PathVariable("id") Long id);

    @GetMapping("/products/name")
    WebProductDto getProductByName(@RequestParam(value = "name", required = false) String name);

    @PostMapping("/products/create")
    WebProductDto createProduct(@RequestBody WebProductDto productDto);

    @PutMapping("/products/update")
    WebProductDto updateProduct(@RequestBody WebProductDto productDto);

    @DeleteMapping("/products/delete/{id}")
    void deleteProduct(@PathVariable("id") Long id);



}
