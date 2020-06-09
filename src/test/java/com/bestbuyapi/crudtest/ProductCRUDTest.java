package com.bestbuyapi.crudtest;

/*
Created by SP
*/

import com.bestbuyapi.steps.ProductSteps;
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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ProductCRUDTest extends TestBase {

    static String name = "JVM New Mobile" + TestUtils.getRandomValue();
    static String type = "iOS5656";
    static String upc = "36445464656";
    static double price = 99.99;
    static String description = "This is the latest high-tech design in mobile phones";
    static String model = "JVM13243UK";
    static long productid;

    @Steps
    ProductSteps productSteps;

    @WithTag("ProductCrudTest")
    @Title("Creating a new Product in the list")
    @Test
    public void test001() {
        ValidatableResponse response = productSteps.createNewProduct(name, type, upc, price, description, model)
                .statusCode(201).log().all();
        productid = response.extract().body().jsonPath().getLong("id");
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(productid));
        System.out.println("My created productid is " + productid);

    }

    @WithTag("ProductCrudTest")
    @Title("Verify product was added to the list")
    @Test
    public void test002() {
        ValidatableResponse response =
                productSteps.getProductInfoByID(productid).statusCode(200);
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(productid));

    }

    @WithTag("ProductCrudTest")
    @Title("Updating the product information and verifying ")
    @Test
    public void test003() {

        name = name + TestUtils.getRandomValue();
        price = 89.99;
        upc = upc + TestUtils.getRandomValueInt();

        ValidatableResponse response =
                productSteps.updatingTheProductByID(productid, name, type, upc, price, description, model).statusCode(200);
        assertThat(response.extract().body().path("name"), equalTo(name));
        assertThat(response.extract().body().path("price"), equalTo((float) price));
        assertThat(response.extract().body().path("upc"), equalTo(upc));
    }

    @WithTag("ProductCrudTest")
    @Title("Deleting the product information and verify if the product is deleted ")
    @Test
    public void test004() {
        productSteps.deleteProductbyID(productid).statusCode(200);
        productSteps.getProductInfoByID(productid).statusCode(404);
    }


}