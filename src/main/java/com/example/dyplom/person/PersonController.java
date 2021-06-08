package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import com.example.dyplom.authority.AuthorityRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final AuthorityRepository authorityRepository;
    private final PersonService personService;
    private final PersonRepository personRepository;

    public PersonController(AuthorityRepository authorityRepository, PersonService personService, PersonRepository personRepository) {
        this.personService = personService;
        this.authorityRepository= authorityRepository;
        this.personRepository=personRepository;
    }

    @GetMapping("/")
    @Secured("ROLE_USERS_TAB")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("people/index");
        modelAndView.addObject("people", personService.findAllUsers());
        return modelAndView;
    }

    @GetMapping("/create")
    @Secured("ROLE_CREATE_USER")
    ModelAndView create() {
        List<Authority> authorities = authorityRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("people/create");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject("person", new Person());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_CREATE_USER")
    ModelAndView edit(@PathVariable Long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return index();
        }
        PersonForm personForm = new PersonForm(person, personRepository);
        ModelAndView modelAndView = new ModelAndView("people/show");
        modelAndView.addObject("authorities", authorityRepository.findAll());
        modelAndView.addObject("personForm",personForm);

        return modelAndView;
    }



    @PostMapping(value = "/save")
    @Secured("ROLE_CREATE_USER")
    ModelAndView saveUser(@Valid @ModelAttribute Person person, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("people/create");
            modelAndView.addObject("authorities", authorityRepository.findAll());
            modelAndView.addObject("person", person);
            return modelAndView;
        }

        personService.savePerson(person);
        modelAndView.setViewName("redirect:/people/");

        return modelAndView;
    }


    @PostMapping(value = "/saveedited")
    @Secured("ROLE_CREATE_USER")
    ModelAndView saveEditedUser(@Valid @ModelAttribute PersonForm personForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("people/show");
            //modelAndView.addObject("authorities", authorityRepository.findAll());
            modelAndView.addObject("personForm", personForm);
            modelAndView.addObject("authorities", personForm.getAuthorities());
            return modelAndView;
        }

        personService.savePerson(personForm);
        modelAndView.setViewName("redirect:/people/");

        return modelAndView;
    }

    @PostMapping(value = "/saveCurrentUser")
    ModelAndView saveCurrentUser(@Valid @ModelAttribute PersonForm personForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("people/showlite");
            //modelAndView.addObject("authorities", authorityRepository.findAll());
            modelAndView.addObject("personForm", personForm);
            //modelAndView.addObject("authorities", personForm.getAuthorities());

            return modelAndView;
        }

        personService.saveCurrentUser(personForm);
        modelAndView.setViewName("redirect:/issue/");

        return modelAndView;
    }



    @GetMapping("delete/{id}")
    @Secured ("ROLE_DELETE_USER")
    ModelAndView deleteUser (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return index();
        }
        if (person.getEnabled().equals(true)) {
        person.setEnabled(false);
        }
        else person.setEnabled(true);

        personRepository.save(person);
        modelAndView.setViewName("redirect:/people/");

        return modelAndView;
    }

    @GetMapping("/changePass/{id}")
    @Secured("ROLE_CREATE_USER")
        ModelAndView changePassword(@PathVariable Long id) {
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setId(id);
        ModelAndView modelAndView = new ModelAndView("people/changePass");
        modelAndView.addObject("passwordForm",passwordForm);
        return modelAndView;
    }


    @PostMapping(value = "/changePass/{id}")
    ModelAndView changePass(@Valid @ModelAttribute PasswordForm passwordForm,@PathVariable("id") Long id,  BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("people/changePass");
            modelAndView.addObject("passwordForm", passwordForm);
            return modelAndView;
        }
        personService.updatePassword(passwordForm);
        modelAndView.setViewName("redirect:/people/");
        return modelAndView;
    }

    @GetMapping("/editlogged/")
    ModelAndView editLogged() {
        Long id = personService.getLoggedUserId(personService.getLoggedUserName());
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            return index();
        }
        PersonForm personForm = new PersonForm(person, personRepository);
        ModelAndView modelAndView = new ModelAndView("people/showlite");
        modelAndView.addObject("personForm",personForm);

        return modelAndView;
    }

    @GetMapping("/changePasswordLogged")
    ModelAndView changePasswordLogged() {
        Long id = personService.getLoggedUserId(personService.getLoggedUserName());

        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setId(id);
        ModelAndView modelAndView = new ModelAndView("people/changePass");
        modelAndView.addObject("passwordForm",passwordForm);
        return modelAndView;
    }




}