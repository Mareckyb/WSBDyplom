package com.example.dyplom.projects;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProjectNameUniquenessValidator.class)
public @interface UniqueProjectName {
    String message() default "{projectname.unique.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
