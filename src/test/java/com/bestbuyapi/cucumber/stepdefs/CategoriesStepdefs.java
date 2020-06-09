package com.bestbuyapi.cucumber.stepdefs;/*
Created by SP
*/

import com.bestbuyapi.steps.CategoriesSteps;
import com.bestbuyapi.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.hamcrest.Matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class CategoriesStepdefs {

    @Steps
    CategoriesSteps categoriesSteps;

    static String categoryName = "Roger Moore Films"+ TestUtils.getRandomValueInt();
    static String categoryId = "Roger"+TestUtils.getRandomValueInt();

    @When("^User sends a GET request to the categories endpoint,they must get back a valid status code 200$")
    public void userSendsAGETRequestToTheCategoriesEndpointTheyMustGetBackAValidStatusCode() {
        categoriesSteps.getAllCategories().statusCode(200);
    }

    @When("^I create a new category by providing the information name \"([^\"]*)\" and id \"([^\"]*)\"$")
    public void iCreateANewCategoryByProvidingTheInformationNameAndId(String name, String id) {
        categoriesSteps.createNewCategory(categoryName,categoryId).statusCode(201);

    }

    @Then("^I verify that the Category with \"([^\"]*)\" is created$")
    public void iVerifyThatTheCategoryWithIsCreated(String id) {
        ValidatableResponse response =
                categoriesSteps.getCategoryInfoByID(categoryId)
                .statusCode(200).log().all();
        categoryId = response.extract().body().path("id");
        assertThat(response.extract().body().path("id"), equalTo(categoryId));
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(categoryName));
    }

    @When("^I get the Category created with \"([^\"]*)\"$")
    public void iGetTheCategoryCreatedWith(String name)  {
        categoriesSteps.getCategoryInfoByID(categoryId).statusCode(200);
    }

    @Then("^I verify that Category with \"([^\"]*)\" is obtained$")
    public void iVerifyThatCategoryWithIsObtained(String id)  {
            ValidatableResponse response =
                    categoriesSteps.getCategoryInfoByID(categoryId)
                            .statusCode(200).log().all();
            assertThat(response.extract().body().jsonPath().get("name"), equalTo(categoryName));
        }

    @When("^I update the Category with name$")
    public void iUpdateTheCategoryWithName() {
    categoryName = categoryName+"Updated";
    categoriesSteps.updatingTheCategoryByID(categoryId, categoryName).statusCode(200).log().all();

    }

    @Then("^I verify that the information is updated in the Category$")
    public void iVerifyThatTheInformationIsUpdatedInTheCategory() {
        ValidatableResponse response =
                categoriesSteps.getCategoryInfoByID(categoryId)
                        .statusCode(200).log().all();
        assertThat(response.extract().body().jsonPath().get("name"), equalTo(categoryName));

    }

    @When("^I delete the Category created with \"([^\"]*)\"$")
    public void iDeleteTheCategoryCreatedWith(String id)  {
    categoriesSteps.deleteCategorybyID(categoryId).statusCode(200).log().all();
    }

    @Then("^I verify that the Category is deleted and get the status 404$")
    public void iVerifyThatTheCategoryIsDeletedAndGetTheStatus() {
        categoriesSteps.getCategoryInfoByID(categoryId).statusCode(404);
    }
}
