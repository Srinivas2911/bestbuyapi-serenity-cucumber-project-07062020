package com.bestbuyapi.steps;

/*
Created by SP
*/

import com.bestbuyapi.constant.EndPoints;
import com.bestbuyapi.model.CategoriesPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CategoriesSteps {

    @Step("Creating a new Category With name:{0}, id:{1} ")
    public ValidatableResponse createNewCategory(String name, String id) {
        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(id);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(categoriesPojo)
                .post(EndPoints.POST_A_CATEGORY)
                .then().log().all();
    }

    @Step("Getting the information of the category created at categoryID:{0}")
    public ValidatableResponse getCategoryInfoByID(String categoryId) {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", categoryId)
                .when()
                .get(EndPoints.GET_SINGLE_CATEGORY_BY_ID)
                .then()
                .log().all();
    }
    @Step("Updating the Category With name:{1}, id:{2} ")
    public ValidatableResponse updatingTheCategoryByID(String categoryId, String name) {

        CategoriesPojo categoriesPojo = new CategoriesPojo();
        categoriesPojo.setName(name);
        categoriesPojo.setId(categoryId);

        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", categoryId)
                .when()
                .body(categoriesPojo)
                .patch(EndPoints.UPDATE_CATEGORY_BY_ID )
                .then().log().all();
    }
    @Step("Deleting the Category information by productId:{0} ")
    public ValidatableResponse deleteCategorybyID(String categoryId) {
        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", categoryId)
                .when()
                .delete(EndPoints.DELETE_CATEGORY_BY_ID)
                .then().log().all();
    }

    @Step("Getting all Categories information ")
    public ValidatableResponse getAllCategories() {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_CATEGORIES)
                .then().log().all();
    }

}
