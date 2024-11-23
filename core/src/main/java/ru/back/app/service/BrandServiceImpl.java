package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.back.app.dto.BrandDto;
import ru.back.app.mapper.BrandMapper;
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
        Brand brand = BrandMapper.INSTANCE.toEntity(brandDto);
        Brand savedBrand = brandRepository.save(brand);
        return BrandMapper.INSTANCE.toDTO(savedBrand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return List.of();
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        Brand brand = brandRepository.findById(brandDto.getId())
                .orElseThrow(() -> new RuntimeException("Brand was not found"));
        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());
        Brand updatedBrand = brandRepository.save(brand);
        return BrandMapper.INSTANCE.toDTO(updatedBrand);
    }

    @Override
    public void deleteBrand(Long id) {
    }
}
