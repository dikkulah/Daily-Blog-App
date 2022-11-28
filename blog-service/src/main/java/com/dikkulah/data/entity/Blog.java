package com.dikkulah.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

//lombok
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Blog implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotEmpty(message = "Header cannot be empty.")
    private String blogHeader;
    @NotEmpty(message = "Content cannot be empty.")
    private String blogContent;

    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    private String email;
    @CreatedDate
    private String creationDate = new Date().toString();


}
