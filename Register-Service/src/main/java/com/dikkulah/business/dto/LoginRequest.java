package com.dikkulah.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @Email(message = "{daily.email.regex.validation.message}")
    @NotEmpty(message = "{daily.email.validation.constraints.NotNull.message}")
    private String email;
    @NotEmpty(message = "{daily.password.validation.constraints.NotNull.message}")
    @Size(min = 7, max = 12, message = "{daily.password.pattern.validation.constraints.NotNull.message}")
    private String password;
}
