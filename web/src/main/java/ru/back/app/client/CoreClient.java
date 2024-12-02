package ru.back.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.back.app.dto.BrandDto;

import java.util.List;

@FeignClient(name = "core-client", url = "http://localhost:8080")
public interface CoreClient {

    @GetMapping("/core/test")
    String testCoreConnection();

    @GetMapping("/core/brands")
    List<BrandDto> getAllBrands();

    @GetMapping("/core/brands/id/{id}")
    BrandDto getBrandById(@PathVariable("id") Long id);

    @GetMapping("core/brands/create")
    BrandDto getBrandByName(@RequestParam(value = "name", required = false) String name);
}
