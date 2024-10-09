package com.endava.petclinic;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class FirtstTest {

    @Test
    public void firstTest(){

        given().baseUri("http://jnet.go.ro/petclinic")
                //.port(8080)
                .basePath("petclinic")
                .log().all()
        .when()
                .get("api/owners")
                .prettyPeek() // printeaza response
        .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void createOwner(){

        Owner owner = new Owner("Andreea", "Stefan", "sos x", "Bucuresti","0761379553");

        given().baseUri("http://jnet.go.ro/petclinic")
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
                .body("id", notNullValue())
                .body("firstName", is(owner.getFirstName()))
                .body("lastName", is(owner.getLastName()))
                .body("addrees", is(owner.getAddress()))
                .body("city",is(owner.getCity()))
                .body("telephone", is(owner.getTelephone()))
                .body("pets",not( empty() ));
    }

    @Test
    public void getOwnerById(){
        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .pathParam("ownerId",5)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get("api/owners/(ownerId)")
                .prettyPeek()
        .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void deleteOwnerId(){
        given().baseUri("http://bhdtest.endava.com")
                .port(8080)
                .basePath("petclinic")
                .pathParam("ownerId",5)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .delete("api/owners/(ownerId)")
                .prettyPeek()
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void createOwner2(){

        //GIVEN
        Owner owner = new Owner("Andreea", "Stefan", "sos x", "Bucuresti","0761379553");
        System.out.println(owner.toString());
        //WHEN
        Response response = given().baseUri("http://bhdtest.endava.com")
                .port(8080)
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
                .body("addrees", is(owner.getAddress()))
                .body("city",is(owner.getCity()))
                .body("telephone", is(owner.getTelephone()))
                .body("pets",not( empty() ));
        Owner actualOwner = response.as(Owner.class);
        assertThat( actualOwner, is(owner));
    }


    //HOMEWORK
    //3.Test the add pet API

    @Test
    public void createPet(){

        Pet pet = new Pet("minnie","Andreea","bulldog");
        given().baseUri("http://jnet.go.ro/petclinic")
                //.port(8080)
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(pet)
                .log().all()
                .when()
                .post("api/pets")
                .prettyPeek() // printeaza response
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .header("Location", notNullValue())
                .body("id", notNullValue());
    }

    //4.Test the get pet list API
    @Test
    public void getPetById(){
        given().basePath("http://jnet.go.ro/petclinic")
                .port(8080)
                .basePath("petclinic")
                .pathParam("PetId",2)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/pets/{petId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header("Location", notNullValue())
                .body("id",notNullValue())
                .body("type",notNullValue());
    }

    //4.Test the create visit API
    @Test
    public void createVisit(){
        Visit visit = new Visit("2024-10-09", "consultation");
        given().baseUri("http://jnet.go.ro/petclinic")
                .basePath("petclinic")
                .contentType(ContentType.JSON)
                .body(visit)
                .log().all()
                .when()
                .post("/api/visits")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    // Test the get visit list API
    @Test
    public void getVisitById(){
        given().basePath("http://jnet.go.ro/petclinic")
                .port(8080)
                .basePath("petclinic")
                .pathParam("VisitId",1)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("/api/visit/{visitId}")
                .prettyPeek()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header("Location", notNullValue())
                .body("id",notNullValue())
                .body("date",notNullValue());
    }


    }



