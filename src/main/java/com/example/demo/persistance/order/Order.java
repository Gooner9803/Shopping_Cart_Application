package com.example.demo.persistance.order;

import com.example.demo.persistance.user.User;
import com.example.demo.persistance.product.Product;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Order {

    @Id
    @GeneratedValue
    @Column(updatable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @OneToOne
    private User customer;

    @OneToOne
    private Product product;

    private Integer orderedQuantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
