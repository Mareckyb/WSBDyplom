package com.example.dyplom.person;

import com.example.dyplom.authority.Authority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@UniqueUsername
@ValidPasswords

public class Person {
    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Column(nullable=false, unique = true, length = 100)
    @Size(min=2, max=100)
    String username;

    @NotEmpty
    @Column(nullable = false)
    String password;

    @Transient
    String repeatedPassword;

    @NotEmpty
    @Size(min=3, max=100)
    @Column(unique = false , length = 100)
    String userRealName;

    @Column(nullable = true)
    Date dateCreated;

    @Column(nullable = false)
    @ColumnDefault(value = "true")
    Boolean enabled = true;

    @Column(nullable=true)
    String email;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable (name = "person_authorities",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn (name = "authority_id"))
    Set <Authority> authorities;



    public Person (String username, String password, String userRealName) {
        this.username = username;
        this.password = password;
        this.userRealName = userRealName;
        this.dateCreated = new Date();
    }


}
