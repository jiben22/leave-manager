package fr.enssat.leave_manager.model;

import fr.enssat.leave_manager.constraint.FieldMatch;
import fr.enssat.leave_manager.constraint.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Les mots de passe doivent correspondre")
public class PasswordResetDto {
    @ValidPassword
    @NotEmpty
    private String password;
    @ValidPassword
    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    private String token;

}