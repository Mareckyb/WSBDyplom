package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.validation.constraints.AssertTrue;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class PersonForm {

    private PersonRepository personRepository;
   // private PersonService personService;

    Long id;
    String username;
    String userRealName;
    String email;

    boolean Valid;
    boolean userRealNameValid;


    Set<Authority> authorities;


    public PersonForm (Person person, PersonRepository personRepository){

        this.id = person.id;
        this.username = person.username;
        this.userRealName= person.userRealName;
        this.email= person.email;
        this.authorities=person.getAuthorities();
        this.personRepository=personRepository;
    }


    @AssertTrue(message = "Login already exists or login is empty")
    public boolean isValid() {
        if (this.username == null || this.username =="" ) return false;

        Person findPerson;
        try {
            findPerson = personRepository.findByUsername(this.username);
            if (findPerson.id == null || findPerson.id.equals(this.id)) return true;
            else return false;
        }
        catch(NullPointerException e){
            return true;
        }
      // if (findPerson.id == null || findPerson.id.equals(this.id)) return true;
      // else return false;
    }

    @AssertTrue(message = "User Real Name is too short")
    public boolean isUserRealNameValid() {
    return this.userRealName.length() >= 3;
    }
}
