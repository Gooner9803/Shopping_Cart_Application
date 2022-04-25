package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.persistance.product.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product mapToEntity(ProductDto productDto);

    ProductDto mapToDto(Product product);
}
