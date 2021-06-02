package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;
import java.util.Set;

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

    Set<Authority> authorities;

    public PersonForm (Person person){
        this.id = person.id;
        this.username = person.username;
        this.userRealName= person.userRealName;
        this.email= person.email;
        this.authorities=person.getAuthorities();
    }
}
