package com.example.dyplom.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
    Person findByUsernameAndEnabled(String username, Boolean enabled);
}
