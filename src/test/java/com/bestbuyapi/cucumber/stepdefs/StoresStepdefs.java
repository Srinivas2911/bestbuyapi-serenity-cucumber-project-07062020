package com.bestbuyapi.cucumber.stepdefs;/*
Created by SP
*/

import com.bestbuyapi.steps.StoresSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class StoresStepdefs {


    @Steps
    StoresSteps storesSteps;
    static String storeName;
    static String storeType;
    static long storeid;

    @When("^User sends a GET request to the stores endpoint,they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheStoresEndpointTheyMustGetBackAValidStatusCode() {
        storesSteps.getAllStores().statusCode(200).log().all();
    }

    @When("^I create a new Store by providing the information name \"([^\"]*)\" type \"([^\"]*)\" address \"([^\"]*)\" address2 \"([^\"]*)\" city \"([^\"]*)\" state \"([^\"]*)\" zip \"([^\"]*)\" lat \"([^\"]*)\" lng \"([^\"]*)\" hours \"([^\"]*)\"$")
    public void iCreateANewStoreByProvidingTheInformationNameTypeAddressAddressCityStateZipLatLngHours(String name, String type, String address, String address2, String city, String state, String zip, double lat, double lng, String hours) {
        storeName = name + TestUtils.getRandomValueInt();
        storeType = type + TestUtils.getRandomValueInt();
        ValidatableResponse response =
                storesSteps.createNewStore(storeName, storeType, address, address2, city, state, zip, lat, lng, hours).statusCode(201).log().all();
        storeid = response.extract().body().jsonPath().getLong("id");
        System.out.println(storeid);

    }

    @Then("^I verify that Store is created with name \"([^\"]*)\"$")
    public void iVerifyThatStoreIsCreatedWithName(String arg0) {
        ValidatableResponse response =
                storesSteps.getStoreById(storeid).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(storeName));
        assertThat(response.extract().body().jsonPath().get("type"), equalTo(storeType));

    }

    @And("^I verify that the Store with \"([^\"]*)\" is created$")
    public void iVerifyThatTheStoreWithIsCreated(String arg0) {
        ValidatableResponse response =
                storesSteps.getStoreById(storeid).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(storeid));
    }


    @When("^I get the Store created with \"([^\"]*)\"$")
    public void iGetTheStoreCreatedWith(String id) {
        storesSteps.getStoreById(storeid).statusCode(200).log().all();

    }

    @Then("^I verify that the Store with \"([^\"]*)\" is obtained$")
    public void iVerifyThatTheStoreWithIsObtained(String id) {
        ValidatableResponse response =
                storesSteps.getStoreById(storeid).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(storeid));
    }

    @When("^I update the store with name type$")
    public void iUpdateTheStoreWithNameType() {

        storeName = storeName+ "Updated";
        storeType = storeType+"Updated";

        storesSteps.updateStore(storeid, storeName, storeType, null, null, null, null, null, 0, 0, null)
                .statusCode(200).log().all();
    }

    @Then("^I verify that the information is updated in the store$")
    public void iVerifyThatTheInformationIsUpdatedInTheStore() {
        ValidatableResponse response =
                storesSteps.getStoreById(storeid).statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(storeName));
        assertThat(response.extract().body().jsonPath().get("type"), equalTo(storeType));

    }

    @When("^I delete the Store created with \"([^\"]*)\"$")
    public void iDeleteTheStoreCreatedWith(String id) {
        storesSteps.deleteStore(storeid).statusCode(200).log().all();
    }


    @Then("^I verify that the Store is deleted and get a status 404$")
    public void iVerifyThatTheStoreIsDeletedAndGetAStatus() {
        storesSteps.getStoreById(storeid).statusCode(404).log().all();
    }
}
