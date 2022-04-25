package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.persistance.order.Order;
import com.example.demo.persistance.order.OrderStatus;
import com.example.demo.repository.OrderRepository;
import com.example.demo.validation.OrderValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderValidator validator;

    public OrderDto place(OrderDto orderDto){
        validator.validateOrder(orderDto);
        Order order = mapper.mapToEntity(orderDto,validator.validateAndGetCustomerId(orderDto.getCustomerId()),
                validator.validateAndGetProductId(orderDto.getProductId()));
        repository.save(order);

        return mapper.mapToDto(order);
    }

    public OrderDto reply(UUID id, OrderStatus status){
        Order order = repository.findById(id)
                        .orElseThrow(OrderNotFoundException::new);
        validator.validateOrder(mapper.mapToDto(order));
        validator.validateStatusForReplying(status);
        order.setStatus(status);
        repository.save(order);

        return mapper.mapToDto(order);
    }
}
