package com.bestbuyapi.crudtest;

/*
Created by SP
*/

import com.bestbuyapi.steps.CategoriesSteps;
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

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CategoriesCRUDTest extends TestBase {

    static String name = "JOHNNY" + TestUtils.getRandomValue();
    static String id = "WALKER"+TestUtils.getRandomValueInt();
    static String categoryId;

    @Steps
    CategoriesSteps categoriesSteps;

    @WithTag("CategoriesCrudTest")
    @Title("Creating a new category in the list")
    @Test
    public void test001() {
        ValidatableResponse response = categoriesSteps.createNewCategory(name, id)
                .statusCode(201).log().all();
        categoryId = response.extract().body().path("id");
        assertThat(response.extract().body().path("id"), equalTo(categoryId));
        System.out.println("My created categoryId is " + categoryId);

    }

    @WithTag("CategoriesCrudTest")
    @Title("Verify category was added to the list")
    @Test
    public void test002() {
        ValidatableResponse response =
                categoriesSteps.getCategoryInfoByID(categoryId).statusCode(200);
        assertThat(response.extract().body().path("id"), equalTo(categoryId));

    }

    @WithTag("CategoriesCrudTest")
    @Title("Updating the category information and verifying ")
    @Test
    public void test003() {

        name = name + TestUtils.getRandomValue();

        ValidatableResponse response =
                categoriesSteps.updatingTheCategoryByID(categoryId,name).statusCode(200);
        assertThat(response.extract().body().path("name"), equalTo(name));
    }

    @WithTag("CategoriesCrudTest")
    @Title("Deleting the category information and verify if the category is deleted ")
    @Test
    public void test004() {
        categoriesSteps.deleteCategorybyID(categoryId).statusCode(200);
        categoriesSteps.getCategoryInfoByID(categoryId).statusCode(404);
    }

}
