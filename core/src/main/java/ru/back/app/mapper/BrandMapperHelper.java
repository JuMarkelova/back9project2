package ru.back.app.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.back.app.entity.Brand;
import ru.back.app.repository.BrandRepository;

@Component
public class BrandMapperHelper {

    @Autowired
    private BrandRepository brandRepository;

    public Brand map(Long brandId) {
        return brandRepository.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + brandId));
    }
}
