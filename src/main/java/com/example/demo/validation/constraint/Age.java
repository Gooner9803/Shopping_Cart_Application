package com.example.demo.validation.constraint;

import com.example.demo.validation.validator.AgeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Age {

    int min();

    int max();

    String message() default "The age is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
