package com.bestbuyapi.cucumber;

/*
Created by SP
*/

import com.bestbuyapi.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/com/bestbuyapi/resources/feature/")

public class CucumberRunner extends TestBase {

}
