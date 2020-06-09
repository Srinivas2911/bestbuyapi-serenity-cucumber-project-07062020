package com.bestbuyapi.testbase;

/*
Created by SP
*/

import com.bestbuyapi.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    // create member variable property reader

public static PropertyReader propertyReader;

    @BeforeClass
    public static void inIt() {

        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));


    }
}
