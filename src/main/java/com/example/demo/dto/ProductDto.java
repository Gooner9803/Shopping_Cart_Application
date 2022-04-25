package com.example.demo.dto;

import com.example.demo.persistance.product.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {

    @NotNull
    private UUID id;

    @NotNull
    @Pattern( regexp = "^[a-zA-Z0-9 .&-]{2,25}$", message = "The length of name should be between 2 and 25 " +
            "and can contain letters, numbers and some special characters(.&-)!")
    private String name;

    @NotNull
    @Size(min = 5, max = 100)
    private String description;

    @NotNull
    private ProductType type;

    @NotNull
    private Double price;

    @NotNull
    private Integer countInStock;


    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}
