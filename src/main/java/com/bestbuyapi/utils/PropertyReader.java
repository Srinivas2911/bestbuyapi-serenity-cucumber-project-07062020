package com.bestbuyapi.utils;

/*
Created by SP
*/

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    /* Rules for implementing singleton design pattern
     *  ---> for restrction of the object of the class to save memory
     * 1. Make the contructor private
     *2. Create a static method to get the instance
     * 3. Create a static member variable
     */

    // Declare the Propertyreader private instance variable
    // volatile keyword in member variable to avoid multi threading issue
    private static volatile PropertyReader propInstance;

    // Create private constrtuctor because to prevent the Instantiation of class

    private PropertyReader() {

    }

    /**
     * create public method to return the instance of the Property Readerclass if it is not null.
     * synchronised keyword in method to avoid multi threading
     * @return
     */

    public static synchronized PropertyReader getInstance() {
        if (propInstance == null) {
            propInstance = new PropertyReader();
        }
        return propInstance;
    }


    /**
     *
     * @param propertyName
     * @return
     *
     * this method will get the properties from the property file
     */
    public String getProperty(String propertyName) {

        Properties prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/com/bestbuyapi/resources/propertyfile/application.properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {

            System.out.println("Property not found");
        }
        return null;
    }

}
