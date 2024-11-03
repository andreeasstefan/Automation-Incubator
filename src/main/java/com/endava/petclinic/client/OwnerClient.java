package com.endava.petclinic.client;
import com.endava.petclinic.filters.AuthenticationFilter;
import com.endava.petclinic.filters.LogFilter;
import com.endava.petclinic.model.Owner;

import static com.endava.petclinic.util.EnvReader.getBasePath;
import static com.endava.petclinic.util.EnvReader.getBaseUri;
import static com.endava.petclinic.util.EnvReader.getPort;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;


public class OwnerClient extends BaseClient{

    public Response createOwner(Owner owner){
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(owner)
                .post("api/owners");
    }

    public Response getOwnerById(Long ownerId){
        return getBasicRestConfig()
                .pathParam("ownerId",ownerId)
                .get("api/owners/{ownerId}");
    }

    public Response getOwnerList(){
        return getBasicRestConfig()
                .get("api/owners/");
    }

    public Response DeleteOwnerById(Long ownerId){
        return getBasicRestConfig()
                .pathParam("ownerId",ownerId)
                .delete("api/owners/{ownerId}");
    }

    public Response UpdateOwnerById(Long ownerId, Owner owner) {
        return getBasicRestConfig()
                .pathParam("ownerId", ownerId)
                .body(owner)
                .contentType(ContentType.JSON)
                .put("api/owners/{ownerId}");
    }
}
