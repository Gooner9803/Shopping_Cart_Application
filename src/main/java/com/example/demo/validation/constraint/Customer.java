package com.example.demo.validation.constraint;

import com.example.demo.validation.validator.CustomerValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = CustomerValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Customer {

    String message() default "The user should have role of customer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
