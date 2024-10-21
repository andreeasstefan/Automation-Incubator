package com.endava.petclinic.visits;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.client.VisitClient;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.Visit;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GetVisitTest extends TestBaseClass {

    @Test
    public void shoulGetVisitList() {
        // GIVEN
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
        Response createVisitResponse = VisitClient.createVisit(visit);
        createVisitResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long visitId = createVisitResponse.body().jsonPath().getLong("id");

        // WHEN
        Response response = VisitClient.getVisitList();
        response.prettyPrint();

        // THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_OK)
                .body("find { it.id == " + visitId + " }.description", is(visit.getDescription()));
    }
    }

