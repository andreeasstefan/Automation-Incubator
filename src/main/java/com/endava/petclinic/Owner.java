package com.endava.petclinic;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner {

    private Long id;
    private String firstName;
    private String lastName;
    private String addrees;
    private String city;
    private String telephone;
    private List<Pet> pets;

    public Owner() {
    }

    public Owner( Long id,final String firstName, final String lastName, final String addrees, final String city, final String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addrees = addrees;
        this.city = city;
        this.telephone = telephone;
        this.id = id ;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return addrees;
    }

    public void setAddress(final String address) {
        this.addrees = addrees;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Owner owner = (Owner) o;
        return //Objects.equals(id, owner.id) && //
                Objects.equals(firstName, owner.firstName) && Objects.equals(lastName, owner.lastName) && Objects.equals(addrees,
                owner.addrees) && Objects.equals(city, owner.city) && Objects.equals(telephone, owner.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(//id ,
                 firstName, lastName, addrees, city, telephone);
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString( this); //this se refara la oboctul pe care este apelata metode toString
        } catch (JsonProcessingException e) {
            return super.toString();
        }

    }
}
