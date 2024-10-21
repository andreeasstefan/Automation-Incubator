package com.endava.petclinic.pets;

import com.endava.petclinic.TestBaseClass;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.is;

public class GetPetTest extends TestBaseClass {

    @Test
    public void shoulGetPetList() {
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
        //WHEN
        Response response = petClient.getPetList();
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id ==%s}.Name", withArgs(petId), is(pet.getId()));
    }
}
