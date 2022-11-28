package com.dikkulah.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter
@MappedSuperclass
abstract public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="system_created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date systemCreatedDate;

    @Column(name="created_by")
    @CreatedBy
    private String createdBy;

    @Column(name="created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name="last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name="last_modified_date")
    @LastModifiedDate
    private Date lastModifiedDate;
}
