package com.endava.petclinic.client;

import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.Visit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.endava.petclinic.util.EnvReader.getBasePath;
import static com.endava.petclinic.util.EnvReader.getBaseUri;
import static io.restassured.RestAssured.given;

public class VisitClient extends BaseClient {


    public static Response createVisit(Visit visit){
        return getBasicRestConfig()
                .contentType(ContentType.JSON)
                .body(visit)
                .post("api/visits");
    }
    public Response getVisitById(Long visitId){
        return getBasicRestConfig()
                .pathParam("visitId",visitId)
                .get("api/visits/{visitId}");
    }

    public static Response getVisitList(){
        return getBasicRestConfig()
                .get("api/visits");
    }

    public static Response DeleteVisitById(Long visitId){
        return getBasicRestConfig()
                .pathParam("visitId",visitId)
                .delete("api/visits/{visitId}");
    }

    public Response UpdateVisitById(Long visitId, Visit visit) {
        return getBasicRestConfig()
                .pathParam("visitId", visitId)
                .body(visit)
                .contentType(ContentType.JSON)
                .put("api/visits/{visitId}");
    }
}
