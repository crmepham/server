package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="file_property")
public class FileProperty extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "value")
    private String value;

    @Column(name = "file_id")
    private long fileId;

    public FileProperty() {

    }

    public FileProperty(String name, String title, String value, long fileId) {
        this.name = name;
        this.title = title;
        this.value = value;
        this.fileId = fileId;
        this.setCreated(new Date());
        this.setCreatedUser("system");
        this.setLastUpdated(new Date());
        this.setLastUpdatedUser("system");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the fileId.
     *
     * @return fileId
     */
    public long getFileId()
    {
        return fileId;
    }

    /**
     * Sets the fileId.
     *
     * @param fileId the fileId
     */
    public void setFileId(long fileId)
    {
        this.fileId = fileId;
    }
}
