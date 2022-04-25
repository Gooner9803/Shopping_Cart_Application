package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.persistance.product.Product;
import com.example.demo.persistance.product.Product.ProductBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-25T06:19:15+0400",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product mapToEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        ProductBuilder product = Product.builder();

        product.id( productDto.getId() );
        product.name( productDto.getName() );
        product.description( productDto.getDescription() );
        product.type( productDto.getType() );
        product.price( productDto.getPrice() );
        product.countInStock( productDto.getCountInStock() );
        product.createdDateTime( productDto.getCreatedDateTime() );
        product.updatedDateTime( productDto.getUpdatedDateTime() );

        return product.build();
    }

    @Override
    public ProductDto mapToDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId( product.getId() );
        productDto.setName( product.getName() );
        productDto.setDescription( product.getDescription() );
        productDto.setType( product.getType() );
        productDto.setPrice( product.getPrice() );
        productDto.setCountInStock( product.getCountInStock() );
        productDto.setCreatedDateTime( product.getCreatedDateTime() );
        productDto.setUpdatedDateTime( product.getUpdatedDateTime() );

        return productDto;
    }
}
