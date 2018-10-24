package com.server.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="menu")
public class Menu extends BaseEntity {

    @Column(name = "external_reference")
    private String externalReference;

    @Column(name = "uri")
    private String uri;

    @Column(name = "title")
    private String title;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "item_order")
    private Integer itemOrder;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Menu parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Menu> children;

    public Menu() {
        super();
    }

    public Menu(String externalReference, String uri) {
        this();
        this.externalReference = externalReference;
        this.uri = uri;
    }

    /**
     * Gets the externalReference.
     *
     * @return externalReference
     */
    public String getExternalReference()
    {
        return externalReference;
    }

    /**
     * Sets the externalReference.
     *
     * @param externalReference the externalReference
     */
    public void setExternalReference(String externalReference)
    {
        this.externalReference = externalReference;
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
     * Gets the title.
     *
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * Gets the visible.
     *
     * @return visible
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Sets the visible.
     *
     * @param visible the visible
     */
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    /**
     * Gets the itemOrder.
     *
     * @return itemOrder
     */
    public Integer getItemOrder()
    {
        return itemOrder;
    }

    /**
     * Sets the itemOrder.
     *
     * @param itemOrder the itemOrder
     */
    public void setItemOrder(Integer itemOrder)
    {
        this.itemOrder = itemOrder;
    }

    /**
     * Gets the parent.
     *
     * @return parent
     */
    public Menu getParent()
    {
        return parent;
    }

    /**
     * Sets the parent.
     *
     * @param parent the parent
     */
    public void setParent(Menu parent)
    {
        this.parent = parent;
    }

    /**
     * Gets the children.
     *
     * @return children
     */
    public List<Menu> getChildren()
    {
        return children;
    }

    /**
     * Sets the children.
     *
     * @param children the children
     */
    public void setChildren(List<Menu> children)
    {
        this.children = children;
    }
}