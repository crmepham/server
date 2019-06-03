package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="database_configuration")
public class DatabaseConfiguration extends BaseEntity
{
    @Column(name = "type")
    private String type;

    @Column(name = "host")
    private String host;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "port")
    private String port;

    @Column(name = "delete_existing_data")
    private boolean deleteExistingData;

    @Column(name = "drop_tables")
    private boolean dropTables;

    @Column(name = "add_if_not_exists")
    private boolean addIfNotExists;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "notes")
    private String notes;
}
