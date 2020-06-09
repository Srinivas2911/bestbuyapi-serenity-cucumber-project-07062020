package com.bestbuyapi.crudtest;

import com.bestbuyapi.steps.ServicesSteps;
import com.bestbuyapi.testbase.TestBase;
import com.bestbuyapi.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/*
Created by SP
*/
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ServicesCRUDTest extends TestBase {

    static String name = "Championship Festival" + TestUtils.getRandomValue();
    static String serviceId;

    @Steps
    ServicesSteps servicesSteps;

    @WithTag("ServicesCrudTest")
    @Title("Creating a new Service in the list")
    @Test
    public void test001() {
        ValidatableResponse response = servicesSteps.createNewService(name)
                .statusCode(201).log().all();
        serviceId = response.extract().body().jsonPath().getString("id");
        assertThat(response.extract().body().jsonPath().getString("id"), equalTo(serviceId));
        System.out.println("My created serviceId is " + serviceId);

    }

    @WithTag("ServicesCrudTest")
    @Title("Verify service was added to the list")
    @Test
    public void test002() {
        ValidatableResponse response =
                servicesSteps.getServiceInfoByID(serviceId).statusCode(200);
        assertThat(response.extract().body().jsonPath().getString("id"), equalTo(serviceId));

    }

    @WithTag("ServicesCrudTest")
    @Title("Updating the service information and verifying ")
    @Test
    public void test003() {

        name = name + TestUtils.getRandomValue();

        ValidatableResponse response =
                servicesSteps.updatingTheServiceByID(serviceId,name).statusCode(200);
        assertThat(response.extract().body().path("name"), equalTo(name));
    }

    @WithTag("ServicesCrudTest")
    @Title("Deleting the category information and verify if the category is deleted ")
    @Test
    public void test004() {
        servicesSteps.deleteServiceByID(serviceId).statusCode(200);
        servicesSteps.getServiceInfoByID(serviceId).statusCode(404);
    }
}
