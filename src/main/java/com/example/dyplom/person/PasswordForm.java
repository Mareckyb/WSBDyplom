package com.example.dyplom.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor

public class PasswordForm {


    Long id;
    @NotBlank
    String password;
    @NotBlank
    String repeatedPassword;
    boolean ValidPass;


    @AssertTrue(message = "Passwords are not the same")
    public boolean isValidPass() {

        if (password ==null || password.length()<3)
            return false;
        if (repeatedPassword == null || repeatedPassword.length()<3)
            return false;
        else
            return this.password.equals(this.repeatedPassword);
    }
}
