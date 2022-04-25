package com.example.demo.validation;

import com.example.demo.dto.OrderDto;
import com.example.demo.persistance.User;
import com.example.demo.persistance.order.OrderStatus;
import com.example.demo.persistance.product.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.example.demo.persistance.order.OrderStatus.*;

@Component
@RequiredArgsConstructor
public class OrderValidator {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void validateOrder(OrderDto orderDto){
        validateId(orderDto.getId());
        validateAndGetCustomerId(orderDto.getCustomerId());
        validateAndGetProductId(orderDto.getProductId());
        validateOrderedQuantity(orderDto.getOrderedQuantity(), orderDto.getProductId());
        validateStatus(orderDto.getStatus());
    }

    private void validateId(UUID id){
        if(repository.existsById(id)){
            throw new IllegalArgumentException("There is order created with same id! ");
        }
    }

    public User validateAndGetCustomerId(UUID id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new IllegalArgumentException("There is no user registered with such id! ");
        } else {
            return user.get();
        }
    }

    public Product validateAndGetProductId(UUID id){
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new IllegalArgumentException("There is no product created with such id! ");
        } else{
            return product.get();
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

    private void validateStatus(OrderStatus status){
        if(status != NONE){
            throw new IllegalArgumentException("The status should be 'None'!");
        }
    }
}
