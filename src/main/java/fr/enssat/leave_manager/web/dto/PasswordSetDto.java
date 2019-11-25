package fr.enssat.leave_manager.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class PasswordSetDto {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String fistname;

    @NotEmpty
    private String lastname;

}