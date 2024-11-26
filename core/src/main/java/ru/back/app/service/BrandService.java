package ru.back.app.service;

import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;
import java.util.List;

public interface BrandService {
    BrandDto getBrandById(Long id);

    BrandDto getByName(String name);

    BrandDto createBrand(BrandCreateDto brandCreateDto);

    List<BrandDto> getAllBrands();

    BrandDto updateBrand(BrandDto brandDto);

    void deleteBrand(Long id);
}
