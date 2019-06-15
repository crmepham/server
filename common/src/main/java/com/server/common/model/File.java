package com.server.common.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="file")
public class File extends BaseEntity {

    @Column(name = "external_reference")
    private String externalReference;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "filename")
    private String filename;

    @Column(name = "path_suffix")
    private String pathSuffix;

    @Column(name = "absolute_path")
    private String absolutePath;

    @Column(name = "extension")
    private String extension;

    @Column(name = "short_reference")
    private String shortReference;

    @OneToMany(mappedBy="fileId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<FileProperty> properties;

    public File() {}

    public File(String externalReference, String title, String type) {
        this.externalReference = externalReference;
        this.title = title;
        this.type = type;
        this.setCreated(new Date());
        this.setCreatedUser("system");
        this.setLastUpdated(new Date());
        this.setLastUpdatedUser("system");
    }

    public FileProperty getFileProperty(String name) {

        return properties.stream().filter(p -> p.getName().equals(name)).findFirst().get();
    }
}