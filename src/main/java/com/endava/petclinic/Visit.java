package com.endava.petclinic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Visit {

    private int id;
    private String date;
    private String description;
    private Pet pet;

    public Visit(int id, String date, String description, Pet pet) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visit visit = (Visit) o;
        return id == visit.id && Objects.equals(date, visit.date) && Objects.equals(description, visit.description) && Objects.equals(pet, visit.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, description, pet);
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", pet=" + pet +
                '}';
    }

    public Visit() {
    }

}
