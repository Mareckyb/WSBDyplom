package com.example.dyplom.projects;

import com.example.dyplom.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String project);
}
