package com.example.dyplom.issues;

import com.example.dyplom.enums.StateName;
import com.example.dyplom.enums.TypeOfIssue;
import com.example.dyplom.person.Person;
import com.example.dyplom.projects.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Issue {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    StateName state = StateName.TODO;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    TypeOfIssue typeOfIssue = TypeOfIssue.TASK;

    @Column(nullable = false)
    @Size(max=500)
    String description;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    Person assignee;

    @ManyToOne(optional = false)
    @JoinColumn(name="project_id", nullable = false)
    Project project;




}
