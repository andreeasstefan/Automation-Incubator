package com.endava.petclinic;

import java.util.List;
import java.util.Objects;

public class Pet {

    private int id;
    private String name;
    private String birthDate;
    private String owner;
    private String type;
    private List<Visit> visits;

    public Pet(String name, String owner, String type) {
        this.name = name;
        this.owner = owner;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public Pet() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && Objects.equals(name, pet.name) && Objects.equals(birthDate, pet.birthDate) && Objects.equals(owner, pet.owner) && Objects.equals(type, pet.type) && Objects.equals(visits, pet.visits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, owner, type, visits);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", owner=" + owner +
                ", type=" + type +
                ", visits=" + visits +
                '}';
    }

}
