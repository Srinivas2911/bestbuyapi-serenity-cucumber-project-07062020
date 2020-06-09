package com.bestbuyapi.cucumber.stepdefs;

/*
Created by SP
*/

import com.bestbuyapi.steps.ProductSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ProductsStepdefs {

    @Steps
    ProductSteps productSteps;

    static String nameOfProduct;
    static long productid;
    static float price;
    static String upc1;

    @When("^User sends a GET request to the product endpoint,they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheProductEndpointTheyMustGetBackAValidStatusCode() {
        productSteps.getAllProducts().statusCode(200).log().all();
    }

    @When("^I create a new product by providing the information name \"([^\"]*)\" type \"([^\"]*)\" upc \"([^\"]*)\" price \"([^\"]*)\"description \"([^\"]*)\" model \"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypeUpcPriceDescriptionModel(String name, String type, String upc, double price, String description, String model) {
        nameOfProduct = name + TestUtils.getRandomValue();
        ValidatableResponse response =
                productSteps.createNewProduct(nameOfProduct, type, upc, price, description, model)
                        .statusCode(201).log().all();
        productid = response.extract().body().jsonPath().getLong("id");
        System.out.println(productid);
    }


    @Then("^I verify that product is created with name \"([^\"]*)\"$")
    public void iVerifyThatProductIsCreatedWithName(String name) {
        ValidatableResponse response =
                productSteps.getProductInfoByID(productid)
                        .statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(nameOfProduct));
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(productid));


    }

    @And("^I verify that the product with \"([^\"]*)\" is created$")
    public void iVerifyThatTheProductWithIsCreated(String id) {
        ValidatableResponse response =
                productSteps.getProductInfoByID(productid)
                        .statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(productid));

    }

    @When("^I get the product created with \"([^\"]*)\"$")
    public void iGetTheProductCreatedWith(String id)  {
        productSteps.getProductInfoByID(productid).statusCode(200);
    }

    @Then("^I verify that the product with \"([^\"]*)\" is obtained$")
    public void iVerifyThatTheProductWithIsObtained(String id) {
        ValidatableResponse response =
                productSteps.getProductInfoByID(productid)
                        .statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().getLong("id"), equalTo(productid));

    }

    @When("^I update the product with name upc price$")
    public void iUpdateTheProductWithNameUpcPrice() {
        nameOfProduct = nameOfProduct + "updated";
        upc1 = TestUtils.getRandomValue();
        price = (float) 89.99;
        productSteps.updatingTheProductByID(productid, nameOfProduct, null, upc1, price, null, null);

    }

    @Then("^I verify that the information is updated in the product$")
    public void iVerifyThatTheInformationIsUpdatedInTheProduct() {
        ValidatableResponse response =
                productSteps.getProductInfoByID(productid).statusCode(200);
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(nameOfProduct));
        assertThat(response.extract().body().jsonPath().get("price"), equalTo(price));
        assertThat(response.extract().body().jsonPath().get("upc"), equalTo(upc1));

    }


    @When("^I delete the product created with \"([^\"]*)\"$")
    public void iDeleteTheProductCreatedWith(String id) {
        productSteps.deleteProductbyID(productid).statusCode(200);
    }

    @Then("^I verify that the product is deleted and get a status is 404$")
    public void iVerifyThatTheProductIsDeletedAndGetAStatusIs() {
        productSteps.getProductInfoByID(productid).statusCode(404);
    }


}

