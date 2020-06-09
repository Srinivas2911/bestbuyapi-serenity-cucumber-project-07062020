package com.bestbuyapi.constant;

/*
Created by SP
*/

public class EndPoints {

    /*
     * Defining end points for all the Products
     */
    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{id}";
    public static final String POST_A_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{id}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{id}";

   /*
           * Defining end points for all the Stores
     */

    public static final String GET_ALL_STORES = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{id}";
    public static final String POST_A_STORE = "/stores";
    public static final String UPDATE_STORE_BY_ID = "/stores/{id}";
    public static final String DELETE_STORE_BY_ID = "/stores/{id}";

    /*
     * Defining end points for all the Categories
     */

    public static final String GET_ALL_CATEGORIES = "/categories";
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{id}";
    public static final String POST_A_CATEGORY = "/categories";
    public static final String UPDATE_CATEGORY_BY_ID = "/categories/{id}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{id}";


    /*
     * Defining end points for all the Services
     */

    public static final String GET_ALL_SERVICES = "/services";
    public static final String GET_SINGLE_SERVICE_BY_ID = "/services/{id}";
    public static final String POST_A_SERVICE = "/services";
    public static final String UPDATE_SERVICE_BY_ID = "/services/{id}";
    public static final String DELETE_SERVICE_BY_ID = "/services/{id}";

}
