package com.example.demo.persistance.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Product{

    @Id
    @GeneratedValue
    @Column(updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private Double price;

    private Integer countInStock;

    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDateTime;

    @DateTimeFormat
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updatedDateTime;
}
