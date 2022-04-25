package com.example.demo.validation.validator;

import com.example.demo.validation.constraint.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {

    private Age constraintAnnotation;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        int age = Period.between(value, LocalDate.now()).getYears();

        return age >= constraintAnnotation.min() && age <= constraintAnnotation.max();
    }

    @Override
    public void initialize(Age constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }
}
