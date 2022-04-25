package com.example.demo.validation;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductValidator {

    private final ProductRepository repository;

    public void validateProduct(ProductDto productDto){
        validateId(productDto.getId());
        validateDateTimes(productDto);
    }

    public void validateProductForUpdate(ProductDto productDto){
        validateIdForUpdate(productDto.getId());
    }

    public void validateExistence(UUID id){
        if(!repository.existsById(id)){
            throw new ProductNotFoundException();
        }
    }

    private void validateId(UUID id){
        if(repository.findById(id).isPresent()){
            throw new IllegalArgumentException("There  is product created with such id!");
        }
    }

    private void validateIdForUpdate(UUID id){
        if(!repository.findById(id).isPresent()){
            throw new ProductNotFoundException();
        }
    }

    private void validateDateTimes(ProductDto productDto){
        if(productDto.getCreatedDateTime() != null || productDto.getUpdatedDateTime() != null ){
            throw new IllegalArgumentException("The date times for creation/update can't be set manually!");
        }
    }
}
