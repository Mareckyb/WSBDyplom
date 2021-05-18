package com.example.dyplom.projects;

import com.example.dyplom.authority.Authority;
import com.example.dyplom.issues.IssueRepository;
import com.example.dyplom.person.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) { this.projectRepository = projectRepository;}

    @GetMapping("/")
    ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("project/index");
        modelAndView.addObject("projects", projectRepository.findAll());
        return modelAndView;
    }



    @GetMapping("/create")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("project", new Project());
        return modelAndView;
    }


    @PostMapping(value = "/save")
    ModelAndView save(@Valid @ModelAttribute Project project, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("project/create");
            modelAndView.addObject("project", project);
            return modelAndView;
        }

        projectRepository.save(project);
        modelAndView.setViewName("redirect:/project/");

        return modelAndView;
    }


}
