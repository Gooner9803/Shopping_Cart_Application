package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.persistance.product.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.validation.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductValidator validator;

    public List<ProductDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> getByIdAndReceiveSuggestions(UUID id) {
        List<ProductDto> products = new ArrayList<>();
        Product product = repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        products.add(mapper.mapToDto(product));
        products.addAll(getSuggestions(product));

        return products;
    }

    public ProductDto create(ProductDto productDto) {
        validator.validateProduct(productDto);

        Product product = mapper.mapToEntity(productDto);
        product.setCreatedDateTime(LocalDateTime.now());
        repository.save(product);

        return mapper.mapToDto(product);
    }


    public ProductDto update(ProductDto productDto) {
        validator.validateProductForUpdate(productDto);

        Product product = mapper.mapToEntity(productDto);
        Product productToUpdate = repository.findById(product.getId())
                .orElseThrow(ProductNotFoundException::new);
        productToUpdate = updateFields(product, productToUpdate);
        productToUpdate.setUpdatedDateTime(LocalDateTime.now());

        return mapper.mapToDto(productToUpdate);
    }

    public void delete(UUID id) {
        validator.validateExistence(id);

        repository.deleteById(id);
    }

    public Product findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public void adjustCountInStock(Product product, int count) {
        product.setCountInStock(product.getCountInStock() + count);

        repository.save(product);
    }

    private Product updateFields(Product product, Product productToUpdate) {
        return productToUpdate.toBuilder()
                .name(product.getName())
                .description(product.getDescription())
                .type(product.getType())
                .countInStock(product.getCountInStock())
                .build();
    }

    private List<ProductDto> getSuggestions(Product product) {
        Double price = product.getPrice();
        List<ProductDto> suggestions = repository.findByTypeAndPriceBetween(product.getType(),
                        price - 0.2 * price, price + 0.2 * price).stream()
                .filter(p -> p.getId() != product.getId())
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
        suggestions.forEach(p -> p.setName(p.getName() + " (Suggested)"));

        return suggestions;
    }


}
