package com.example.dyplom.issues;

import com.example.dyplom.enums.State;
import com.example.dyplom.person.Person;
import com.example.dyplom.projects.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    State state = State.TODO;

    @ManyToOne
    @JoinColumn(name="assignee_id")
    Person assignee;

    @ManyToOne(optional = false)
    @JoinColumn(name="project_id", nullable = false)
    Project project;


}
