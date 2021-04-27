package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Person {
    @Id
    @GeneratedValue
    Long id;
    @Column(nullable=false, unique = true, length = 100)
    String login;

    @Column(nullable = false)
    String password;
    @Column(unique = false , length = 100)
    String userRealName;
    @Column(nullable = false)
    Date dateCreated;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enabled = true;


    @ManyToMany
    @JoinTable (name = "person_authorities",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn (name = "authority_id"))
    Set <Authority> authorities;



    public Person (String login, String password, String userRealName) {
        this.login = login;
        this.password = password;
        this.userRealName = userRealName;
        this.dateCreated = new Date();
    }


}
