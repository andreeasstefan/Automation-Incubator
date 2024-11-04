package com.endava.petclinic.pets;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreatePetTest extends TestBaseClass {


    @Test
    public void shouldCreatePet() {
        //GIVEN
        Owner owner = fixture.createOwner()
                .getOwner();

        Pet pet = testDataProvider.getPet();
        pet.setOwner(owner);
        //WHEN
        Response response = PetClient.createPet(pet);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));
    }

    @Test
    public void shouldFailToCreatePetGivenEmptyDate() {
        //GIVEN
        Pet pet = testDataProvider.getPet();
        pet.setBirthDate(null);
        //WHEN
        Response response = PetClient.createPet(pet);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_BAD_REQUEST);

    }


}
