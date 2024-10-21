package com.endava.petclinic.client;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.endava.petclinic.util.EnvReader.getBasePath;
import static com.endava.petclinic.util.EnvReader.getBaseUri;
import static io.restassured.RestAssured.given;

public class PetClient {

    public static Response createPet(Pet pet){
        return given().baseUri(getBaseUri())
                //.port(getPort())
                .basePath(getBasePath())
                .contentType(ContentType.JSON)
                .body(pet)
                .post("api/pets");
    }
    public Response getPetById(Long petId){
        return given().baseUri(getBaseUri())
                //.port(getPort())
                .basePath(getBasePath())
                .pathParam("petId",petId)
                .get("api/pets/{petId}");
    }

    public Response getPetList(){
        return given().baseUri(getBaseUri())
                //.port(getPort())
                .basePath(getBasePath())
                .get("api/pets/");
    }

    public Response DeletePetById(Long petId){
        return given().baseUri(getBaseUri())
                //.port(getPort())
                .basePath(getBasePath())
                .pathParam("petId",petId)
                .delete("api/pets/{petId}");
    }

    public Response UpdatePetById(Long petId, Pet pet) {
        return given().baseUri(getBaseUri())
                //.port(getPort())
                .basePath(getBasePath())
                .pathParam("petId", petId)
                .body(pet)
                .contentType(ContentType.JSON)
                .put("api/pets/{petId}");
    }
}
