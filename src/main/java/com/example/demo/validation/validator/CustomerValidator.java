package com.example.demo.validation.validator;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistance.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.BeanUtil;
import com.example.demo.validation.constraint.Customer;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.UUID;

import static com.example.demo.persistance.user.Role.CUSTOMER;


public class CustomerValidator implements ConstraintValidator<Customer, UUID> {

    private UserRepository repository;

    @Override
    public boolean isValid(UUID value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        User user = repository.findById(value)
                .orElseThrow(UserNotFoundException::new);
        if(user.getRole() == CUSTOMER){
            return true;
        }

        return false;
    }

    @Override
    public void initialize(Customer constraintAnnotation) {
        repository = BeanUtil.getBean(UserRepository.class);
    }
}