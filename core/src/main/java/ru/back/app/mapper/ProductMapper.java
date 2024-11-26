package ru.back.app.mapper;

import org.mapstruct.Mapper;
import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import ru.back.app.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    Product productDtoToProduct(ProductCreateDto productCreateDto);
}

