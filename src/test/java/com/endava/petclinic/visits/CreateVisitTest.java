package com.endava.petclinic.visits;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.client.VisitClient;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateVisitTest extends TestBaseClass {

    @Test
    public void shouldCreateVisit() {
        //GIVEN
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");
        owner.setId(ownerId);

        Pet pet = testDataProvider.getPet();
        pet.setOwner(owner);
        Response createPetResponse = petClient.createPet(pet);
        createPetResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long petId = createPetResponse.body().jsonPath().getLong("id");
        pet.setId(petId);

        Visit visit = testDataProvider.getVisit();
        visit.setPet(pet);
        // WHEN
        Response response = VisitClient.createVisit(visit);
        // THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));
    }

    @Test
    public void shouldFailToCreateVisitGivenEmptyDate() {
        //GIVEN
        Visit visit = testDataProvider.getVisit();
        visit.setDate(null);
        //WHEN
        Response response = VisitClient.createVisit(visit);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_BAD_REQUEST);

    }


    @Test
    public void shouldFailToCreateVisitGivenEmptyDescription() {
        //GIVEN
        Visit visit = testDataProvider.getVisit();
        visit.setDescription(null);
        //WHEN
        Response response = VisitClient.createVisit(visit);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_BAD_REQUEST);

    }
}
