package com.example.dyplom.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;

@Getter
@Setter
@NoArgsConstructor

public class PasswordForm {

    Long id;
    String password;
    String repeatedPassword;
    boolean isValid;


    @AssertTrue(message = "Passwords are not the same")
    public boolean isValid() {

        if (password ==null || password.length()<3)
            return false;
        if (repeatedPassword == null || repeatedPassword.length()<3)
            return false;
        else
            return this.password.equals(this.repeatedPassword);
    }
}
