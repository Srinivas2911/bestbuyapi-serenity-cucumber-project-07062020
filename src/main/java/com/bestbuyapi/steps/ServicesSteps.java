package com.bestbuyapi.steps;

/*
Created by SP
*/

import com.bestbuyapi.constant.EndPoints;
import com.bestbuyapi.model.ServicesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ServicesSteps {

    @Step("Creating a new Service With name:{0}")
    public ValidatableResponse createNewService(String name) {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(servicesPojo)
                .post(EndPoints.POST_A_SERVICE)
                .then().log().all();
    }

    @Step("Getting the information of the service created at categoryID:{0}")
    public ValidatableResponse getServiceInfoByID(String serviceId) {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", serviceId)
                .when()
                .get(EndPoints.GET_SINGLE_SERVICE_BY_ID)
                .then()
                .log().all();
    }
    @Step("Updating the service With name:{1} ")
    public ValidatableResponse updatingTheServiceByID(String serviceId, String name) {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", serviceId)
                .when()
                .body(servicesPojo)
                .patch(EndPoints.UPDATE_SERVICE_BY_ID )
                .then().log().all();
    }
    @Step("Deleting the Category information by productId:{0} ")
    public ValidatableResponse deleteServiceByID(String serviceId) {
        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", serviceId)
                .when()
                .delete(EndPoints.DELETE_SERVICE_BY_ID)
                .then().log().all();
    }

    @Step("Getting all Services information ")
    public ValidatableResponse getAllServices() {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_SERVICES)
                .then().log().all();
    }

}
