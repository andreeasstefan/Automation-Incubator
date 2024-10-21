package com.endava.petclinic;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.Type;
import com.endava.petclinic.model.Visit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FirtstTest {

    @Test
    public void firstTest(){

        given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .log().all()
        .when()
                .get("api/owners")
                .prettyPeek()
        .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void createOwner(){

        Owner owner = new Owner(null,"Andreea", "Stefan", "sos x", "Bucuresti","0761379553");

        given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
        .when()
                .post("api/owners")
                .prettyPeek() // printeaza response
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", notNullValue())
                .body("id", notNullValue());
                //.body("firstName", is(owner.getFirstName()))
                //.body("lastName", is(owner.getLastName()));
                //.body("addrees", is(owner.getAddress()))
                //.body("city",is(owner.getCity()))
                //.body("telephone", is(owner.getTelephone()));
                //.body("pets",not( empty() ));
    }

    @Test
    public void getOwnerById(){
        given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .pathParam("ownerId",1)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get("/api/owners/{ownerId}")
                .prettyPeek()
        .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void deleteOwnerId(){
        given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .pathParam("ownerId",5)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .delete("/api/owners/{ownerId}")
                .prettyPeek()
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void createOwner2(){

        //GIVEN
        Owner owner = new Owner(null,"Andreea", "Stefan", "sos x", "Bucuresti","0761379553");
        System.out.println(owner.toString());
        //WHEN
        Response response = given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(owner)
                .log().all()
        .when()
                .post("api/owners")
                .prettyPeek();
        //THEN
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", notNullValue())
                .body("id", notNullValue())
                .body("firstName", is(owner.getFirstName()))
                .body("lastName", is(owner.getLastName()))
                //.body("addrees", is(owner.getAddress()))
                .body("city",is(owner.getCity()))
                .body("telephone", is(owner.getTelephone()));
                //.body("pets",not( empty() ));
        Owner actualOwner = response.as(Owner.class);
        assertThat( actualOwner, is(owner));
    }


    //HOMEWORK
    //3.Test the add pet API

    @Test
    public void createPet() throws Exception {
        Owner owner = new Owner(1L, "Andreea", "Stefan", "110 W. Liberty St.", "Madison", "6085551023");
        Type type = new Type(1, "cat");
        List<Visit> visits = new ArrayList<>();
        Pet pet = new Pet( 2L, "Minnie", "2024/09/07", type, owner); //visits);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonBody = objectMapper.writeValueAsString(pet);
        given()
                .baseUri("http://jnet.go.ro/")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .log().all()
                .when()
                .post("api/pets")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("Minnie"))
                .body("owner.firstName", equalTo("Andreea"))
                .body("type.name", equalTo("cat"));
    }

    //4.Test the get pet list API
    @Test
    public void getPetById(){
        given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .pathParam("petId",1)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/pets/{petId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    //4.Test the create visit API
    @Test
    public void createVisit() throws Exception {
        Owner owner = new Owner(1L, "Andreea", "Stefan", "110 W. Liberty St.", "Madison", "6085551023");
        Type type = new Type(1, "cat");
        Pet pet = new Pet(1L, "Minnie", "2024/09/07", type, owner); //visits);
        Visit visit = new Visit(1, "2024/10/01", "description", pet);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonBody = objectMapper.writeValueAsString(visit);
        given()
                .baseUri("http://jnet.go.ro/")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .log().all()
                .body(jsonBody)
                .when()
                .post("api/visits")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("description", equalTo("description"))
                .body("date", equalTo("2024/10/01"));

    }



    // Test the get visit list API
    @Test
    public void getVisitById(){
        given().baseUri("http://jnet.go.ro/")
                //.port(8080)
                .basePath("petclinic")
                .pathParam("visitId",1)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/visits/{visitId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


    }



