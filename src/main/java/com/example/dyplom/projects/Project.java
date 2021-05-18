package com.example.dyplom.projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @UniqueProjectName
    public class Project {

        @Id
        @GeneratedValue
        Long id;


        @NotEmpty
        @Column(nullable = false, unique=true)
        String projectname;

        @Column(nullable=true, length = 500)
        String description;

    }

