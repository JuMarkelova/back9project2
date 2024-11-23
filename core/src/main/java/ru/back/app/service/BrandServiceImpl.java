package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.back.app.dto.BrandDto;
import ru.back.app.repository.BrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;

    @Override
    public BrandDto getBrandById(Long Id) {
        return null;
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return List.of();
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        return null;
    }

    @Override
    public void deleteBrand(Long id) {
    }
}
