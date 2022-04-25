package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.persistance.order.OrderStatus;
import com.example.demo.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @Operation(description = "Creates new order.")
    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<OrderDto> place(@Valid @RequestBody OrderDto orderDto){
        return ResponseEntity.ok().body(service.place(orderDto));
    }

    @Operation(description = "Replies order.")
    @PutMapping("/{id}")
    @PreAuthorize(("hasRole('ADMIN')"))
    public ResponseEntity<OrderDto> reply(@PathVariable UUID id, @RequestParam OrderStatus status){
        return ResponseEntity.ok().body(service.reply(id, status));
    }

}
