package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import com.example.dyplom.authority.AuthorityRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class PersonService {

    private final AuthorityRepository authorityRepository;
    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonService(AuthorityRepository authorityRepository, PersonRepository personRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authorityRepository = authorityRepository;
        this.personRepository = personRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void prepareAdminUser(){

        if  (personRepository.findByUsername("admin") !=null) {
            return;
        }

        Person person = new Person("admin","123456","Administrator");
        List<Authority> authorities = authorityRepository.findAll();
        person.setAuthorities(new HashSet<>(authorities));
        savePerson(person);
    }


    void savePerson(Person person) {
        String hashedPassword = bCryptPasswordEncoder.encode(person.password);
        person.setPassword(hashedPassword);
        if (person.dateCreated== null) person.setDateCreated(new Date());

        personRepository.save(person);
    }

    public void savePerson(PersonForm personForm){
        Person person = personRepository.findById(personForm.id).orElse(null);
        person.username=personForm.username;
        person.userRealName= personForm.userRealName;
        person.email= personForm.email;
        person.setAuthorities(personForm.getAuthorities());

        personRepository.save(person);
        getLoggedUserName();
        getLoggedUserId(getLoggedUserName());

    }

    public void saveCurrentUser(PersonForm personForm){
        Person person = personRepository.findById(personForm.id).orElse(null);
        //person.username=personForm.username;
        person.userRealName= personForm.userRealName;
        person.email= personForm.email;
        //person.setAuthorities(personForm.getAuthorities());

        personRepository.save(person);

    }

    public void updatePassword(PasswordForm passwordForm) {
        Person person = personRepository.findById(passwordForm.id).orElse(null);
        person.password = bCryptPasswordEncoder.encode(passwordForm.password);
        personRepository.save(person);

    }

    String getLoggedUserName()
    {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println("Zalogowany user: "+loggedUser.getUsername());
        return loggedUser.getUsername();
    }

    Long getLoggedUserId(String username){
        Person person = new Person();
        person = personRepository.findByUsername(username);

        System.out.println("Id zalogowanego użytkownika: "+person.getId());
        return person.getId();
    }

    Person getUserByUsername(String username){
        return personRepository.findByUsername(username);
    }


    List<Person> findAllUsers()
    {  return personRepository.findAll();    }

}
