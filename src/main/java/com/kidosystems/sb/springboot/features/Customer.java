package com.kidosystems.sb.springboot.features;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private UUID guid;
    private String firstName;
    private String surname;

    public Customer() {
    }

    public Customer(final UUID guid,
                    final String firstName,
                    final String surname) {
        this.guid = guid;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Customer(final Long id,
                    final UUID guid,
                    final String firstName,
                    final String surname) {
        this.id = id;
        this.guid = guid;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public UUID getGuid() {
        return guid;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
