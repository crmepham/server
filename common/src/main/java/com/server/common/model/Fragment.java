package com.server.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Entity
@Table(name="fragment")
public class Fragment extends BaseEntity {

    @Column(name = "external_reference")
    private String externalReference;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "parameters")
    private String parameters;

    @Column(name = "query")
    private String query;

    @Column(name = "design")
    private String design;

    @Column(name = "uri")
    private String uri;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "item_order")
    private Integer itemOrder;

    @Transient
    private Collection<Map<String, String>> resultParameters;

    @Transient
    private Collection<String> errors = new ArrayList<String>();

    public Collection<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public Collection<Map<String, String>> getResultParameters() {
        return resultParameters;
    }

    public void setResultParameters(Collection<Map<String, String>> resultParameters) {
        this.resultParameters = resultParameters;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
