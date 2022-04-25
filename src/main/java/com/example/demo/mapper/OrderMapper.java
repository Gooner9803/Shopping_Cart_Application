package com.example.demo.mapper;

import com.example.demo.dto.OrderDto;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistance.User;
import com.example.demo.persistance.order.Order;
import com.example.demo.persistance.product.Product;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    default Order mapToEntity(OrderDto orderDto, User customer, Product product){
        return Order.builder()
                .id(orderDto.getId())
                .customer(customer)
                .product(product)
                .orderedQuantity(orderDto.getOrderedQuantity())
                .status(orderDto.getStatus()).build();
    }

    default OrderDto mapToDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .customerId(getCustomerId(order.getCustomer()))
                .productId(getProductId(order.getProduct()))
                .orderedQuantity(order.getOrderedQuantity())
                .status(order.getStatus()).build();
    }

    default UUID getCustomerId(User customer){
        if(customer == null){
            throw new UserNotFoundException();
        }
        return customer.getId();
    }

    default UUID getProductId(Product product){
        if(product == null){
            throw new ProductNotFoundException();
        }
        return product.getId();
    }

}
