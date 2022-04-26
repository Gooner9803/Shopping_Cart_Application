package com.example.demo.validation;

import com.example.demo.dto.OrderDto;
import com.example.demo.persistance.order.OrderStatus;
import com.example.demo.persistance.product.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.example.demo.persistance.order.OrderStatus.*;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final OrderRepository repository;
    private final ProductRepository productRepository;

    public void validateOrderForPlacing(OrderDto orderDto){
        validateIfIdNotExists(orderDto.getId());
        validateOrderedQuantity(orderDto.getOrderedQuantity(), orderDto.getProductId());
        validateStatusForPlacing(orderDto.getStatus());
    }

    public void validateOrderForReplying(OrderDto orderDto){
        validateOrderedQuantity(orderDto.getOrderedQuantity(), orderDto.getProductId());
        validateStatusForReplying(orderDto.getStatus());
    }

    private void validateIfIdNotExists(UUID id){
        if(repository.existsById(id)){
            throw new IllegalArgumentException("There is order created with same id! ");
        }
    }

    private void validateStatusForPlacing(OrderStatus status){
        if(status != NONE){
            throw new IllegalArgumentException("The status should be 'None'!");
        }
    }

    public void validateStatusForReplying(OrderStatus status){
        if(status == REJECTED || status == ACCEPTED){
            throw new IllegalArgumentException("The status should be either 'ACCEPTED' or 'REJECTED'!");
        }
    }

    private void validateOrderedQuantity(Integer quantity, UUID productId){
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()){
            throw new IllegalArgumentException("There is no product created with such id! ");
        }
        if(product.get().getCountInStock()<quantity){
            throw new IllegalArgumentException("The ordered quantity can't be greater than quantity n stock.");
        }
    }
}
