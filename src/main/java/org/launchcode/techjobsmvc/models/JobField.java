package org.launchcode.techjobsmvc.models;

import java.util.Objects;

// Class Declaration
public abstract class JobField {
    private int id;
    private static int nextId = 1;
    private String value;

    // Constructor assigns a unique ID
    public JobField() {
        id = nextId;
        nextId++;
    }

    // Constructor assigns given string value
    public JobField(String value) {
        this(); // Calls no-arg constructor to initialize ID
        this.value = value;
    }

    // Custom toString() method, returns data stored in 'value'.
    @Override
    public String toString() {
        return value;
    }

    // Custom equals and hashCode methods. Two objects are "equal" when their id fields match.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobField)) return false;
        JobField jobField = (JobField) o;
        return id == jobField.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Getters and setters.
    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
