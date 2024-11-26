package ru.back.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;
import ru.back.app.entity.Brand;
import ru.back.app.entity.Product;
import ru.back.app.mapper.BrandMapper;
import ru.back.app.repository.BrandRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
public class BrandServiceTest {
    @Mock
    BrandRepository brandRepository;
    @Mock
    BrandMapper brandMapper;
    @InjectMocks
    BrandServiceImpl brandService;


    @Test
    public void getBrandById() {
        Long id = 1L;
        String name = "Test brand name";
        String description = "Test brand description";
        Set<Product> products = new HashSet<>();
        Brand brand = new Brand(id, name, description, products);
        BrandDto brandDto = new BrandDto(id, name, description);
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));
        when(brandMapper.toDTO(brand)).thenReturn(brandDto);

        BrandDto result = brandService.getBrandById(id);

        verify(brandRepository, times(1)).findById(id);
        verify(brandMapper, times(1)).toDTO(brand);
        Assertions.assertEquals(result.getId(), brand.getId(), "Wrong id");
        Assertions.assertEquals(result.getName(), brand.getName(), "Wrong name");
        Assertions.assertEquals(result.getDescription(), brand.getDescription(), "Wrong description");
    }

    @Test
    public void getBrandByIdFailure() {
        Long id = 1L;

        when(brandRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> brandService.getBrandById(id));
        verify(brandRepository, times(1)).findById(id);
    }

    @Test
    public void getBrandByName() {
        Long id = 1L;
        String name = "Test brand name";
        String description = "Test brand description";
        Set<Product> products = new HashSet<>();
        Brand brand = new Brand(id, name, description, products);
        BrandDto brandDto = new BrandDto(id, name, description);
        when(brandRepository.findBrandByName(name)).thenReturn(Optional.of(brand));
        when(brandMapper.toDTO(brand)).thenReturn(brandDto);

        BrandDto result = brandService.getByName(name);

        verify(brandRepository, times(1)).findBrandByName(name);
        verify(brandMapper, times(1)).toDTO(brand);
        Assertions.assertEquals(result.getId(), brand.getId(), "Wrong id");
        Assertions.assertEquals(result.getName(), brand.getName(), "Wrong name");
        Assertions.assertEquals(result.getDescription(), brand.getDescription(), "Wrong description");
    }

    @Test
    public void getBrandByNameFailure() {
        String name = "Test brand name";

        when(brandRepository.findBrandByName(name)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> brandService.getByName(name));
        verify(brandRepository, times(1)).findBrandByName(name);
    }

    @Test
    public void getAllBrandsTest() {
        Brand brand1 = new Brand(1L, "Brand 1", "Description 1", new HashSet<>());
        Brand brand2 = new Brand(2L, "Brand 2", "Description 2", new HashSet<>());
        List<Brand> brands = List.of(brand1, brand2);

        BrandDto brandDto1 = new BrandDto(1L, "Brand 1", "Description 1");
        BrandDto brandDto2 = new BrandDto(2L, "Brand 2", "Description 2");
        List<BrandDto> brandsDto = List.of(brandDto1, brandDto2);

        when(brandRepository.findAll()).thenReturn(brands);
        when(brandMapper.toDTO(brand1)).thenReturn(brandDto1);
        when(brandMapper.toDTO(brand2)).thenReturn(brandDto2);

        List<BrandDto> result = brandService.getAllBrands();

        verify(brandRepository, times(1)).findAll();
        verify(brandMapper, times(1)).toDTO(brand1);
        verify(brandMapper, times(1)).toDTO(brand2);
        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertEquals(2, result.size(), "Result list size should be 2");
        Assertions.assertEquals(brandsDto, result, "Result should match expected DTOs");
    }

    @Test
    public void createBrandTest() {
        BrandCreateDto brandCreateDto = new BrandCreateDto("Brand 1", "Description 1");
        Brand brand = new Brand(1L, "Brand 1", "Description 1", new HashSet<>());
        BrandDto brandDto = new BrandDto(1L, "Brand 1", "Description 1");

        when(brandMapper.toEntity(any(BrandCreateDto.class))).thenReturn(brand);
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(brandMapper.toDTO(any(Brand.class))).thenReturn(brandDto);

        BrandDto result = brandService.createBrand(brandCreateDto);

        verify(brandRepository, times(1)).save(any(Brand.class));
        verify(brandMapper, times(1)).toEntity(brandCreateDto);
        Assertions.assertNotNull(result, "Result should not be null");
        Assertions.assertEquals(result.getId(), brand.getId(), "Wrong id");
        Assertions.assertEquals(result.getName(), brand.getName(), "Wrong name");
        Assertions.assertEquals(result.getDescription(), brand.getDescription(), "Wrong description");
    }

    @Test
    public void updateBrand() {
        BrandDto brandDto = new BrandDto(1L, "Brand 1", "Description 1");
        Brand brand = new Brand(1L, "Brand 1", "Description 1", new HashSet<>());


        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        when(brandRepository.save(any(Brand.class))).thenReturn(brand);
        when(brandMapper.toDTO(any(Brand.class))).thenReturn(brandDto);

        BrandDto result = brandService.updateBrand(brandDto);

        verify(brandRepository, times(1)).findById(1L);
        verify(brandRepository, times(1)).save(any(Brand.class));
        verify(brandMapper, times(1)).toDTO(brand);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), brand.getId(), "Wrong id");
        Assertions.assertEquals(result.getName(), brand.getName(), "Wrong name");
        Assertions.assertEquals(result.getDescription(), brand.getDescription(), "Wrong description");
    }

    @Test
    public void deleteBrandTest() {
        Long id = 1L;
        brandService.deleteBrand(id);
        verify(brandRepository, times(1)).deleteById(id);
    }
}
