package com.server.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "BIGINT(11)")
    private Long id;

    @Column(name = "created")
    private Date created;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "last_updated")
    private Date lastUpdated;

    @Column(name = "last_updated_user")
    private String lastUpdatedUser;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "deleted_user")
    private String deletedUser;

    protected BaseEntity() {
        Date now = new Date();
        setCreated(now);
        setLastUpdated(now);
        setCreatedUser("system");
        setLastUpdatedUser("system");
    }
}