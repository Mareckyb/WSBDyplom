package com.example.dyplom.projects;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }


     void saveProject(Project project){
          projectRepository.save(project);
    }
}
