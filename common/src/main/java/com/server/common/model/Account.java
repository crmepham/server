package com.server.common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="account")
public class Account extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy="accountId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<AccountTransaction> transactions = new ArrayList<>();

    public Account() {}

    public Account(String name) {
        super();
        this.name = name;
    }

    public double getTotal(@NonNull final String type) {
        if (transactions == null) {
            return 0;
        }
        final List<AccountTransaction> list = transactions.stream().filter(t -> type.equalsIgnoreCase(t.getType())).collect(Collectors.toList());
        if (list.isEmpty()) {
            return 0;
        }

        double total = 0;
        for (AccountTransaction transaction : list) {
            total += transaction.getValue() * transaction.getPercent() / 100.0;
        }

        return total;
    }

    public List<AccountTransaction> getTransactions(@NonNull final String type) {
        return transactions.stream().filter(t -> type.equalsIgnoreCase(t.getType())).collect(Collectors.toList());
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

    /**
     * Gets the transactions.
     *
     * @return transactions
     */
    public Collection<AccountTransaction> getTransactions()
    {
        return transactions;
    }

    /**
     * Sets the transactions.
     *
     * @param transactions the transactions
     */
    public void setTransactions(Collection<AccountTransaction> transactions)
    {
        this.transactions = transactions;
    }
}