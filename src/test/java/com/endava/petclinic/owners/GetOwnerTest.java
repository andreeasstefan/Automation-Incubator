package com.endava.petclinic.owners;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.TestBaseClass;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetOwnerTest extends TestBaseClass {

    @Test
    public void shoulGetOwnerList(){
        //GIVEN
        //Owner owner = new Owner("John", "Cena", "TX", "Texas", "0444567");
        Owner owner = testDataProvider.getOwner();
        Response createOwnerResponse = ownerClient.createOwner(owner);
        createOwnerResponse.then().statusCode(HttpStatus.SC_CREATED);
        Long ownerId = createOwnerResponse.body().jsonPath().getLong("id");
        //WHEN
        Response response =ownerClient.getOwnerList();
        //THEN
        response.prettyPeek().then().statusCode(HttpStatus.SC_OK)
                .body("find{ it -> it.id ==%s}.firstName", withArgs(ownerId),is(owner.getFirstName()));
//        Owner actualOwner = response.body().jsonPath().param("id", ownerId).getObject("find{ it -> it.id ==%s}", Owner.class);
//        assertThat(actualOwner, is(owner));


//        List<Owner> ownerList = response.body().jsonPath().getList( "", Owner.class) ;
//        assertThat(ownerList,hasItem(owner));


    }
}
