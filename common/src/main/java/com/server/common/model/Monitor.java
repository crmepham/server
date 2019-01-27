package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="monitor")
public class Monitor extends BaseEntity
{

    @Column(name = "name")
    private String name;

    @Column(name = "uri")
    private String uri;

    @Column(name = "note")
    private String note;

    @Column(name = "reachable")
    private Boolean reachable;

    @Column(name = "notify")
    private Boolean notify;

    /**
     * Gets the notify.
     *
     * @return notify
     */
    public Boolean getNotify()
    {
        return notify;
    }

    /**
     * Sets the notify.
     *
     * @param notify the notify
     */
    public void setNotify(Boolean notify)
    {
        this.notify = notify;
    }

    /**
     * Gets the reachable.
     *
     * @return reachable
     */
    public Boolean getReachable()
    {
        return reachable;
    }

    /**
     * Sets the reachable.
     *
     * @param reachable the reachable
     */
    public void setReachable(Boolean reachable)
    {
        this.reachable = reachable;
    }

    /**
     * Gets the name.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the uri.
     *
     * @return uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * Sets the uri.
     *
     * @param uri the uri
     */
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
     * Gets the note.
     *
     * @return note
     */
    public String getNote()
    {
        return note;
    }

    /**
     * Sets the note.
     *
     * @param note the note
     */
    public void setNote(String note)
    {
        this.note = note;
    }
}
