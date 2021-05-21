package com.example.dyplom.projects;

import com.example.dyplom.person.Person;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("project", new Project());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    @Secured("ROLE_MANAGE_PROJECT")
    ModelAndView edit(@PathVariable Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return index();
        }
        ModelAndView modelAndView = new ModelAndView("project/create");
        modelAndView.addObject("project", project);
        return modelAndView;
    }


    @PostMapping(value = "/save")
    @Secured("ROLE_MANAGE_PROJECT")
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


    @GetMapping("delete/{id}")
    @Secured ("ROLE_MANAGE_PROJECT")
    ModelAndView deleteProject (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null) {
            return index();
        }
        projectRepository.delete(project);
        modelAndView.setViewName("redirect:/project/");

        return modelAndView;
    }


}
