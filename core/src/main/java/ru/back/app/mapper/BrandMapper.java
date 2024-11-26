package ru.back.app.mapper;

import org.mapstruct.Mapper;
import ru.back.app.dto.BrandCreateDto;
import ru.back.app.dto.BrandDto;
import ru.back.app.entity.Brand;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDTO(Brand brand);

    Brand toEntity(BrandDto brandDto);

    Brand toEntity(BrandCreateDto brandCreateDto);
}

