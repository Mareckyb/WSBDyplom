package com.example.dyplom.projects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    public class Project {

        @Id
        @GeneratedValue
        Long id;

        @Column(nullable = false, unique=true)
        String name;

        @Column(nullable=true, length = 500)
        String description;

    }

