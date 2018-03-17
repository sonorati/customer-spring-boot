package com.kidosystems.sb.springboot.features;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.UUID;

public class CustomerResource {

    private UUID guid;
    private String firstName;
    private String surname;

    public CustomerResource() {
    }

    public CustomerResource(final UUID guid,
                            final String firstName,
                            final String surname) {
        this.guid = guid;
        this.firstName = firstName;
        this.surname = surname;
    }

    public UUID getGuid() {
        return guid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
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
