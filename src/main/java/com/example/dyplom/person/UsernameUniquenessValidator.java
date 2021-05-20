package com.example.dyplom.person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniquenessValidator  implements ConstraintValidator<UniqueUsername, Person> {

    private final PersonRepository personRepository;

    public UsernameUniquenessValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Person person, ConstraintValidatorContext ctx) {
        //Logika walidacji pola username

        Person foundPerson = personRepository.findByUsername(person.getUsername());

        if (foundPerson==null) {return true;}

        boolean usernameIsUnique = person.getId()!=null && foundPerson.getId().equals(person.getId());

        if (!usernameIsUnique) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("username")
                    .addConstraintViolation();
        }

        return usernameIsUnique;
    }
}
