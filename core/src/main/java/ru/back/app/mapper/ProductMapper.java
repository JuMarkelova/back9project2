package ru.back.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.back.app.dto.ProductDto;
import ru.back.app.entity.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);
}

