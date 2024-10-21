package com.endava.petclinic.owners;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.TestBaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteOwnerTest extends TestBaseClass {

    @Test
    public void shouldDeleteOwner(){
        //GIVEN
        //Owner owner = new Owner("John", "Cena", "TX", "Texas", "0444567");
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long OwnerId = createOwnerResponse.body().jsonPath().getLong("id");
        //WHEN
        Response response = ownerClient.DeleteOwnerById(OwnerId);
        //THEN
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
