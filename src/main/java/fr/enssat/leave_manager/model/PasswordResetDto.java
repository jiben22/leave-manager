package fr.enssat.leave_manager.model;

import fr.enssat.leave_manager.constraint.FieldMatch;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Les mots de passe doivent correspondre")
public class PasswordResetDto {

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{10,})", message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un nombre et un caractère spécial (!@#$%^&*). Il doit avoir une taille minimum de 10 caractères !")
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

}