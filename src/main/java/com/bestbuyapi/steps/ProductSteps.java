package com.bestbuyapi.steps;

/*
Created by SP
*/


import com.bestbuyapi.constant.EndPoints;
import com.bestbuyapi.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

import static jnr.posix.WString.path;

public class ProductSteps {


    @Step("Creating a new product With name:{0}, type:{1}, upc:{2}, price:{3} description:{4},model:{5} ")
    public ValidatableResponse createNewProduct(String name, String type, String upc, double price, String description,
                                                String model) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post(EndPoints.POST_A_PRODUCT)
                .then().log().all();

    }

    @Step("Getting the information of the product Created with productId:{0}")
    public ValidatableResponse getProductInfoByID(long productId) {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .log().all();
    }

    @Step("Updating the product With name:{1}, type:{2}, upc:{2}, price:{4} description:{5},model:{6} ")
    public ValidatableResponse updatingTheProductByID(long productId, String name, String type, String upc, double price, String description,
                                                      String model) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", productId)
                .when()
                .body(productPojo)
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Deleting the Product information by productId:{0} ")
    public ValidatableResponse deleteProductbyID(long productID) {
        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", productID)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then().log().all();
    }

    @Step("Getting all Products information ")
    public ValidatableResponse getAllProducts() {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then().log().all();
    }

}