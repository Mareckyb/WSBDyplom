package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import com.example.dyplom.authority.AuthorityRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        person.setDateCreated(new Date());

        personRepository.save(person);
    }

    List<Person> findAllUsers()
    {  return personRepository.findAll();    }

}
