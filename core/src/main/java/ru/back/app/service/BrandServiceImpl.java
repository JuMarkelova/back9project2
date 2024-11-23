package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.back.app.dto.BrandDto;
import ru.back.app.mapper.BrandMapper;
import ru.back.app.repository.BrandRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;

    @Override
    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand was not found"));
        return BrandMapper.INSTANCE.toDTO(brand);
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        Brand brand = BrandMapper.INSTANCE.toEntity(brandDto);
        Brand savedBrand = brandRepository.save(brand);
        return BrandMapper.INSTANCE.toDTO(savedBrand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        List<Brand> brands= brandRepository.findAll();
        return brands.stream()
                .map(BrandMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
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
        brandRepository.deleteById(id);
    }
}
