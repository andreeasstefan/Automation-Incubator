package com.endava.petclinic.fixture;

import com.endava.petclinic.TestData.TestDataProvide;
import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.client.VisitClient;
import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.Visit;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.http.HttpStatus;

public class PetClinicFixture {
    private OwnerClient ownerClient = new OwnerClient();
    private PetClient petClient = new PetClient();
    private VisitClient visitClient = new VisitClient();
    private TestDataProvide dataProvider = new TestDataProvide();

    @Getter
    private Owner owner;
    @Getter
    private Pet pet;
    @Getter
    private Visit visit;

    public PetClinicFixture createOwner() {

        owner = dataProvider.getOwner();
        Response response = ownerClient.createOwner(owner);
        response.then().statusCode(HttpStatus.SC_CREATED);

        long id = response.body().jsonPath().getLong("id");
        owner.setId(id);

        return this;
    }

    public PetClinicFixture createPet() {

        if (owner == null || owner.getId() == null) {
            throw new IllegalStateException("Owner must be created before creating a pet");
        }

        pet = dataProvider.getPet();
        pet.setOwner(owner);

        Response responsePet = petClient.createPet(pet);
        responsePet.then().statusCode(HttpStatus.SC_CREATED);

        long petId = responsePet.body().jsonPath().getLong("id");
        pet.setId(petId);

        return this;
    }

    public PetClinicFixture createVisit() {

        if (owner == null || owner.getId() == null || pet == null || pet.getId() == null) {
            throw new IllegalStateException("Owner and pet must be created before creating a visit");
        }

        visit = dataProvider.getVisit();
        visit.setPet(pet);

        Response responseVisit = VisitClient.createVisit(visit);
        responseVisit.then().statusCode(HttpStatus.SC_CREATED);

        long visitId = responseVisit.body().jsonPath().getLong("id");
        visit.setId((int) visitId);

        return this;
    }
}
