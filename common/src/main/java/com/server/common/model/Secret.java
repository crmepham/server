package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="secret")
public class Secret extends BaseEntity
{
    @Column(name = "context")
    private String context;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "type")
    private String type;

    /**
     * Gets the type.
     *
     * @return type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Gets the context.
     *
     * @return context
     */
    public String getContext()
    {
        return context;
    }

    /**
     * Sets the context.
     *
     * @param context the context
     */
    public void setContext(String context)
    {
        this.context = context;
    }

    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the description
     */
    public void setDescription(String description)
    {
        this.description = description;
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
