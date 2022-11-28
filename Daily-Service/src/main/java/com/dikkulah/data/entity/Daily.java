package com.dikkulah.data.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

//lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Daily implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NotEmpty(message = "Header cannot be empty.")
    private String dailyHeader;
    @NotEmpty(message = "Content cannot be empty.")
    private String dailyContent;

    @NotEmpty(message = "{daily.email.validation.constraints.NotNull.message}")
    @Email(message = "{daily.email.regex.validation.message}")
    private String email;

    @CreatedDate
    private String creationDate = new Date().toString();


}
