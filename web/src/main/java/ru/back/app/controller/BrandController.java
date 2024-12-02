package ru.back.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.back.app.client.CoreClient;
import ru.back.app.dto.BrandDto;

import java.util.List;

@RequestMapping("/web/brands")
@RestController
@RequiredArgsConstructor
public class BrandController {
    private final CoreClient coreClient;

    @GetMapping
    public List<BrandDto> getAllBrands(){
        return coreClient.getAllBrands();
    }
}
