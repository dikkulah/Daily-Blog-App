package com.dikkulah.data.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

//lombok
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Log4j2

//Entity
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements Serializable {
    public static final long serialVersionUID = 1L;


    @Column(name = "email", length = 100, nullable = false, unique = true)
    @Email(message = "{daily.email.regex.validation.message}")
    @NotEmpty(message = "{daily.email.validation.constraints.NotNull.message}")
    private String email;

    @Size(min = 2, max = 50)
    private String name;
    @Size(min = 2, max = 50)
    private String surname;

    @NotEmpty(message = "{daily.password.validation.constraints.NotNull.message}")
    private String password;

}
