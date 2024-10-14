package com.endava.petclinic;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

public class Pet {
    private int id;
    private String name;
    private String birthDate;
    private Owner owner;
    private Type type;
    //private List<Visit> visits;

    public Pet( int id, String name, String birthDate,Type type,Owner owner) {
        this.name = name;
        this.birthDate = birthDate;
        this.owner = owner;
        this.type = type;
        this.id = id;
        //this.visits = visits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

//    public List<Visit> getVisits() {
//        return visits;
//    }

//    public void setVisits(List<Visit> visits) {
//        this.visits = visits;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return //id == pet.id &&
         Objects.equals(name, pet.name) && Objects.equals(birthDate, pet.birthDate) && Objects.equals(owner, pet.owner) && Objects.equals(type, pet.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, owner, type);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", owner=" + owner +
                ", type=" + type +
                ", id=" + id +
                '}';
    }

}
