package ru.back.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.back.app.dto.ProductCreateDto;
import ru.back.app.dto.ProductDto;
import ru.back.app.entity.Product;

@Mapper(componentModel = "spring", uses = {BrandMapperHelper.class})
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);

    @Mapping (source = "brandId", target = "brand")
    Product productDtoToProduct(ProductCreateDto productCreateDto);
}

