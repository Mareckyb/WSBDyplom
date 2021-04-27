package com.example.dyplom.config;

import com.example.dyplom.authority.Authority;
import com.example.dyplom.authority.AuthorityRepository;
import com.example.dyplom.enums.AuthorityName;
import com.example.dyplom.person.PersonService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class Bootstrap implements InitializingBean {

    private final AuthorityRepository authorityRepository;
    private final PersonService personService;

    public Bootstrap(AuthorityRepository authorityRepository, PersonService personService) {
        this.authorityRepository = authorityRepository;
        this.personService = personService;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Rozpoczynamy przygotowywanie aplikacji...");

        prepareAuthorities();

        personService.prepareAdminUser();
    }

    private void prepareAuthorities() {
        for (AuthorityName name : AuthorityName.values()) {
            Authority existingAuthority = authorityRepository.findByName(name);
            if (existingAuthority == null) {
                Authority authority = new Authority(name);
                authorityRepository.save(authority);
            }
        }
    }
}
