package com.endava.petclinic.visits;

import com.endava.petclinic.TestBaseClass;
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
        Owner owner = fixture.createOwner()
                .getOwner();

        Pet pet = fixture.createPet()
                .getPet();

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
