package com.example.dyplom.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor

public class PersonForm {

    @NotBlank
    Long id;

    @NotBlank
    String username;

    @NotBlank
    String userRealName;

    String email;

    public PersonForm (Person person){
        this.id = person.id;
        this.username = person.username;
        this.userRealName= person.userRealName;
        this.email= person.email;
    }
}
