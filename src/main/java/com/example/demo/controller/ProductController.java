package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Returns all products.")
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Returns product by id and suggestions that have same type as the product " +
            "or the difference of their prices is 20%.")
    @GetMapping("/{id}")
    public ResponseEntity<List<ProductDto>> getByIdAndReceiveSuggestions(@PathVariable UUID id){
        return ResponseEntity.ok().body(service.getByIdAndReceiveSuggestions(id));
    }

    @Operation(summary = "Creates and returns new product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creates new product while all fields are valid."),
            @ApiResponse(responseCode = "400", description = "Product's fields are not valid.")
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.ok().body(service.create(productDto));
    }

    @Operation(summary = "Updates product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updates product if all giving values are valid."),
            @ApiResponse(responseCode = "400", description = "Product's fields are not valid.")
    })
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductDto productDto){
        return ResponseEntity.ok().body(service.update(productDto));
    }

    @Operation(summary = "Deletes product.")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);

        return ResponseEntity.ok().build();
    }
}
