package com.endava.petclinic;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true) // orice proprietate care este in strig-ul de json, dar nu este in clasa o sa fie ignorata
public class Owner {

    private Long id; // nu folosim primitive ca sunt clase model
    private String firstName; // fix ca in json
    private String lastName;
    private String address;
    private String city;
    private String telephone;

    public Owner() { // avem nevoie de constructor default pentru a face deserializare
    }

    public Owner(final String firstName, final String lastName, final String address, final String city, final String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
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
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
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
        return //Objects.equals(id, owner.id) && // nu avem nevoie sa facem validare pe id deoarce nu o sa fie niciodata egal
                Objects.equals(firstName, owner.firstName) && Objects.equals(lastName, owner.lastName) && Objects.equals(address,
                owner.address) && Objects.equals(city, owner.city) && Objects.equals(telephone, owner.telephone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(//id , // nu avem nevoie de id deoarce nu avem nevoie sa fie egale id-urile
                 firstName, lastName, address, city, telephone);
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString( this); //this se refara la oboctul pe care este apelata metode toString
        } catch (JsonProcessingException e) {
            return super.toString(); // metoda asta merge pentru orice clasa indiferent pentru cate filduri
            // this se refera la obictul curent
            // super se refera la parinte
        }

    }
}
