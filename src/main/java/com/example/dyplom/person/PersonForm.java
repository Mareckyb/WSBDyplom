package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class PersonForm {

    @Autowired
    private PersonRepository personRepository;
    private PersonService personService;

    Long id;
    String username;
    String userRealName;
    String email;

    boolean isValid;
    //boolean userRealNameValid;


    Set<Authority> authorities;


    public PersonForm (Person person){

        this.id = person.id;
        this.username = person.username;
        this.userRealName= person.userRealName;
        this.email= person.email;
        this.authorities=person.getAuthorities();
    }

    @AssertTrue(message = "Login already exists")
    public boolean isValid() {
       PersonRepository personRepository = this.personRepository;
       Person findPerson = personRepository.findByUsername(this.username);
       if (findPerson.id == null || findPerson.id.equals(this.id)) return true;
       else return false;
    }

    //@AssertTrue(message = "User Real Name is too short")
    //public boolean isUserRealNameValid() {
    //    return this.userRealName.length() >= 3;
    //}
}
