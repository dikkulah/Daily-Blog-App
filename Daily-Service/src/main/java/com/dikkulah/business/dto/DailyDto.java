package com.dikkulah.business.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

//lombok
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DailyDto implements Serializable {

    private String id;

    @NotEmpty(message = "{daily.header.validation.constraints.NotNull.message}")
    private String dailyHeader;

    @NotEmpty(message = "{daily.content.validation.constraints.NotNull.message}")
    private String dailyContent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdDate;

    @NotEmpty(message = "{daily.email.validation.constraints.NotNull.message}")
    @Email(message = "{daily.email.regex.validation.message}")
    private String email;
}
