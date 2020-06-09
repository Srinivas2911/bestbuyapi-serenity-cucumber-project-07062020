package com.bestbuyapi.cucumber.stepdefs;/*
Created by SP
*/

import com.bestbuyapi.steps.ServicesSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ServicesStepdefs {

    @Steps
    ServicesSteps servicesSteps;

    static String serviceName;
    static String serviceId;

    @When("^User sends a GET request to the services endpoint,they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheServicesEndpointTheyMustGetBackAValidStatusCode() {
        servicesSteps.getAllServices().statusCode(200).log().all();
    }

    @When("^I create a new service by providing the information name \"([^\"]*)\"$")
    public void iCreateANewServiceByProvidingTheInformationName(String name) {
        serviceName = name + TestUtils.getRandomValue();
        ValidatableResponse response = servicesSteps.createNewService(serviceName)
                .statusCode(201).log().all();
        serviceId = response.extract().body().jsonPath().getString("id");
        System.out.println("My created serviceId is " + serviceId);

    }

    @Then("^I verify that the service with \"([^\"]*)\" is created$")
    public void iVerifyThatTheServiceWithIsCreated(String name) {
        ValidatableResponse response =
                servicesSteps.getServiceInfoByID(serviceId).statusCode(200);
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(serviceName));
        //assertThat(response.extract().body().jsonPath().getString("id"), equalTo(serviceId));
    }

    @When("^I update the service with name$")
    public void iUpdateTheServiceWithName() {
        serviceName = serviceName + "Updated";
        servicesSteps.updatingTheServiceByID(serviceId, serviceName).statusCode(200).log().all();

    }

    @Then("^I verify that the information is updated in the services$")
    public void iVerifyThatTheInformationIsUpdatedInTheServices() {
        ValidatableResponse response =
                servicesSteps.getServiceInfoByID(serviceId);
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(serviceName));

    }

    @When("^I delete the service created with \"([^\"]*)\"$")
    public void iDeleteTheServiceCreatedWith(String arg0) {
        servicesSteps.deleteServiceByID(serviceId).statusCode(200);
    }

    @Then("^I verify that the service is deleted and get a status is 404$")
    public void iVerifyThatTheServiceIsDeletedAndGetAStatusIs() {
        servicesSteps.deleteServiceByID(serviceId).statusCode(404);

    }

    @When("^I get the service created with \"([^\"]*)\"$")
    public void iGetTheServiceCreatedWith(String arg0) {
        servicesSteps.getServiceInfoByID(serviceId).statusCode(200).log().all();
    }

    @Then("^I verify that the services with \"([^\"]*)\" is obtained$")
    public void iVerifyThatTheServicesWithIsObtained(String id) {
        ValidatableResponse response =
        servicesSteps.getServiceInfoByID(serviceId)
                        .statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getString("id"), equalTo(serviceId));

    }
}
