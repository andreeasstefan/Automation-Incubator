package com.endava.petclinic.owners;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.TestBaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOwnerTest extends TestBaseClass {

    @Test
    public void shouldCreateOwner() {
        //GIVEN
        //Owner owner = new Owner("Andreea", "Stefan", "sos x", "Bucuresti","0761379553");
        Owner owner = testDataProvider.getOwner();
        //owner.setAddress("aaaaaaa");
        //WHEN
        Response response = ownerClient.createOwner(owner);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_CREATED)
                .body("id", is(notNullValue()));
    }

    @Test
    public void shouldFailToCreateOwnerGivenEmptyFirstName() {
        //GIVEN
        // Owner owner = new Owner("", "Stefan", "sos x", "Bucuresti","0761379553");
        Owner owner = testDataProvider.getOwner();
        owner.setFirstName("");
        //WHEN
        Response response = ownerClient.createOwner(owner);
        //THEN
        response.then().statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void shouldFailToCreateOwnerGivenFewDigitsTelephone() {
        //GIVEN
        // Owner owner = new Owner("", "Stefan", "sos x", "Bucuresti","0761379553");
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(0, 0));
        //WHEN
        Response response = ownerClient.createOwner(owner);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @Test
    public void shouldFailToCreateOwnerGivenManyDigitsTelephone() {
        //GIVEN
        // Owner owner = new Owner("", "Stefan", "sos x", "Bucuresti","0761379553");
        Owner owner = testDataProvider.getOwner();
        owner.setTelephone(testDataProvider.getNumberWithDigits(11, 100));
        //WHEN
        Response response = ownerClient.createOwner(owner);
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_BAD_REQUEST);

    }
}
