package com.bestbuyapi.crudtest;

import com.bestbuyapi.steps.StoresSteps;
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

public class StoresCRUDTest extends TestBase {

    static String name = "Automation Store";
    static String type = "Rest Assured Tools";
    static String address = "260 Rockland Avenue";
    static String address2 = "Uxbridge";
    static String city = "London";
    static String state = "West";
    static String zip = "525255";
    static double lat = 45.958785;
    static double lng = -90.445596;
    static String hours = "Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6";

    static long storeid;

    @Steps
    StoresSteps storesSteps;

    @WithTag("StoresCrudTest")
    @Title("Creating a new Store")
    @Test
    public void test001() {
        ValidatableResponse response =
                storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours)
                        .statusCode(201).log().all();
        storeid = response.extract().response().body().jsonPath().getLong("id");
        assertThat(response.extract().response().body().jsonPath().getLong("id"), equalTo(storeid));
        System.out.println("My created storeid is " + storeid);

    }

    @WithTag("StoresCrudTest")
    @Title("Getting a created Store")
    @Test
    public void test002() {
        ValidatableResponse response =
                storesSteps.getStoreById(storeid).statusCode(200);
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(storeid));

    }

    @WithTag("StoresCrudTest")
    @Title("This test will update the store information and verify the updated information")
    @Test

    public void test003() {

        name = name + TestUtils.getRandomValue();
        type = type + TestUtils.getRandomValue();

        ValidatableResponse response = storesSteps.updateStore(storeid, name, type, address, address2, city, state, zip, lat, lng, hours)
                .statusCode(200);
        assertThat(response.extract().body().path("name"), equalTo(name));
        assertThat(response.extract().body().path("type"), equalTo(type));
    }

    @WithTag("StoresCrudTest")
    @Title("This test will delete the store and verify the store is deleted ")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeid).statusCode(200);
        storesSteps.getStoreById(storeid).statusCode(404);
    }
}
