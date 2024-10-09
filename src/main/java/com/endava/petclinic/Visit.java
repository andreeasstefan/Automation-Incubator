package com.endava.petclinic;

import java.util.Objects;

public class Visit {

    private int id;
    private String date;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id && Objects.equals(date, visit.date) && Objects.equals(description, visit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, description);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Visit(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public Visit() {
    }
}
