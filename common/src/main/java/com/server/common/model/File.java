package com.server.common.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<FileProperty> getProperties() {
        return properties;
    }

    public FileProperty getFileProperty(String name) {

        return properties.stream().filter(p -> p.getName().equals(name)).findFirst().get();
    }

    public void setProperties(Collection<FileProperty> properties) {
        this.properties = properties;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the filename.
     *
     * @return filename
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * Sets the filename.
     *
     * @param filename the filename
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    /**
     * Gets the pathSuffix.
     *
     * @return pathSuffix
     */
    public String getPathSuffix()
    {
        return pathSuffix;
    }

    /**
     * Sets the pathSuffix.
     *
     * @param pathSuffix the pathSuffix
     */
    public void setPathSuffix(String pathSuffix)
    {
        this.pathSuffix = pathSuffix;
    }

    /**
     * Gets the absolutePath.
     *
     * @return absolutePath
     */
    public String getAbsolutePath()
    {
        return absolutePath;
    }

    /**
     * Sets the absolutePath.
     *
     * @param absolutePath the absolutePath
     */
    public void setAbsolutePath(String absolutePath)
    {
        this.absolutePath = absolutePath;
    }

    /**
     * Gets the shortReference.
     *
     * @return shortReference
     */
    public String getShortReference()
    {
        return shortReference;
    }

    /**
     * Sets the shortReference.
     *
     * @param shortReference the shortReference
     */
    public void setShortReference(String shortReference)
    {
        this.shortReference = shortReference;
    }
}