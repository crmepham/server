package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo extends BaseEntity {

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "context")
    private String context;

    @Column(name = "pinned")
    private Boolean pinned;

    public String getInstruction() {
        return instruction;
    }

    public String getContext() {
        return context;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public void setPinned(Boolean pinned) {
        this.pinned = pinned;
    }
}