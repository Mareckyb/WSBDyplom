package com.example.dyplom.projects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProjectNameUniquenessValidator implements ConstraintValidator<UniqueProjectName, Project> {

    private final ProjectRepository projectRepository;

    public ProjectNameUniquenessValidator(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void initialize(UniqueProjectName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Project project, ConstraintValidatorContext ctx) {

         Project foundProject = projectRepository.findByProjectname(project.getProjectname());

        if (foundProject==null) {return true;}

        boolean projectnameIsUnique = project.getId()!=null && foundProject.getId().equals(project.getId());

        if (!projectnameIsUnique) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("projectname")
                    .addConstraintViolation();
        }

        return projectnameIsUnique;
    }

 }

