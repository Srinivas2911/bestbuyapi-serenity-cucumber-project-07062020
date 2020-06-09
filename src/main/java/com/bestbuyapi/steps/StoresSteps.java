package com.bestbuyapi.steps;

/*
Created by SP
*/

import com.bestbuyapi.constant.EndPoints;
import com.bestbuyapi.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StoresSteps {


    @Step("Creating a new store with name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{4}, zip:{5}, lat:{6}, lng:{7}, hours:{8}")

    public ValidatableResponse createNewStore(String name, String type, String address, String address2,
                                              String city, String state, String zip, double lat, double lng,
                                              String hours) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(storesPojo)
                .post(EndPoints.POST_A_STORE)
                .then();
    }

    @Step("Getting the information of the store created by id:{0}")
    public ValidatableResponse getStoreById(long storeid) {
        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", storeid)
                .when()
                .get(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then().log().all();
    }

    @Step("Updating Store information with  name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{5}, zip:{6}, lat:{7}, lng:{8}, hours:{9}")

    public ValidatableResponse updateStore(long storeid, String name, String type, String address, String address2,
                                           String city, String state, String zip, double lat, double lng,
                                           String hours) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .pathParam("id", storeid)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then();


    }

    @Step("Deleting the store with store Id : {0} ")

    public ValidatableResponse deleteStore(long storeid) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", storeid)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then().log().all();

    }

    @Step("Getting all Stores information ")
    public ValidatableResponse getAllStores() {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_STORES)
                .then().log().all();
    }

    @Step("Updating Store information with  name:{0}, type:{1}")

    public ValidatableResponse updateStoreInfo(long storeid, String name, String type) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .pathParam("id", storeid)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(EndPoints.UPDATE_STORE_BY_ID)
                .then();

    }

}
