package com.dikkulah.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlogDto implements Serializable {
    private UUID id;
    private String blogHeader;
    private String blogContent;
    private byte[] imageData;
    private String email;
    private String creationDate = new Date().toString();
}
