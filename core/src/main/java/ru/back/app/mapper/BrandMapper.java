package ru.back.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.back.app.dto.BrandDto;
import ru.back.app.entity.Brand;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDto toDTO(Brand brand);
    Brand toEntity(BrandDto brandDto);
}

