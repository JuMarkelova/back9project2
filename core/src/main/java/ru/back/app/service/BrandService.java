package ru.back.app.service;

import ru.back.app.dto.BrandDto;
import java.util.List;

public interface BrandService {
    BrandDto getBrandById(Long id);
    BrandDto createBrand(BrandDto brandDto);
    List<BrandDto> getAllBrands();
    BrandDto updateBrand(BrandDto brandDto);
    void deleteBrand(Long id);
}
