package com.example.dyplom.issues;

import com.example.dyplom.enums.StateName;
import com.example.dyplom.enums.StateRepository;
import com.example.dyplom.enums.TypeOfIssue;
import com.example.dyplom.enums.TypeOfIssueRepository;
import com.example.dyplom.person.PersonRepository;
import com.example.dyplom.projects.Project;
import com.example.dyplom.projects.ProjectRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/issue")
public class IssueController {

    final IssueRepository issueRepository;
    final PersonRepository personRepository;
    final StateRepository stateRepository;
    final TypeOfIssueRepository typeOfIssueRepository;
    final ProjectRepository projectRepository;


    public IssueController(IssueRepository issueRepository, PersonRepository personRepository, StateRepository stateRepository, TypeOfIssueRepository typeOfIssueRepository, ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.personRepository = personRepository;
        this.stateRepository = stateRepository;
        this.typeOfIssueRepository = typeOfIssueRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    ModelAndView index(@ModelAttribute IssueFilter issueFilter) {
        ModelAndView modelAndView = new ModelAndView("issue/index");
        modelAndView.addObject("issues", issueRepository.findAll(issueFilter.buildQuery()));
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("people", personRepository.findAll());
        modelAndView.addObject("states", StateName.values());
        modelAndView.addObject("typeOfIssues", TypeOfIssue.values());

        modelAndView.addObject("filter", issueFilter);

        return modelAndView;
    }


    @GetMapping("/create")
    @Secured("ROLE_MANAGE_ISSUE")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("issue/create");
        modelAndView.addObject("state", stateRepository.findAll());
        modelAndView.addObject("type", typeOfIssueRepository.findAll());
        modelAndView.addObject("persons", personRepository.findAll());
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("issue", new Issue());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_MANAGE_ISSUE")
    ModelAndView edit(@PathVariable Long id) {
        Issue issue = issueRepository.findById(id).orElse(null);
        //if (issue == null) {
        //    return index();
        //}
        ModelAndView modelAndView = new ModelAndView("issue/create");
        modelAndView.addObject("state", stateRepository.findAll());
        modelAndView.addObject("type", typeOfIssueRepository.findAll());
        modelAndView.addObject("persons", personRepository.findAll());
        modelAndView.addObject("projects", projectRepository.findAll());
        modelAndView.addObject("issue", issue);
        return modelAndView;
    }


    @PostMapping(value = "/save")
    @Secured("ROLE_MANAGE_ISSUE")
    ModelAndView save(@Valid @ModelAttribute Issue issue, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("issue/create");
            modelAndView.addObject("state", stateRepository.findAll());
            modelAndView.addObject("type", typeOfIssueRepository.findAll());
            modelAndView.addObject("persons", personRepository.findAll());
            modelAndView.addObject("projects", projectRepository.findAll());
            modelAndView.addObject("issue", issue);
            return modelAndView;
        }
        issueRepository.save(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }

    @GetMapping("delete/{id}")
    @Secured ("ROLE_MANAGE_ISSUE")
    ModelAndView deleteIssue (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Issue issue = issueRepository.findById(id).orElse(null);
       // if (issue == null) {
       //     return index();
       // }
        issueRepository.delete(issue);
        modelAndView.setViewName("redirect:/issue/");
        return modelAndView;
    }





}
