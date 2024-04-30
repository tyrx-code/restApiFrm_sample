package com.rest.helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApiHelpers {

    public static Map<String, String> getEndpoints() {
        Properties properties = new Properties();
        Map<String, String> uris = new HashMap<>();

        try (InputStream input = ApiHelpers.class.getResourceAsStream("/config.properties")) {
            if (input!=null) {
                properties.load(input);
            }
        } catch (IOException ex) {
            System.out.println("======================PROPERTIES FILE NOT FOUND======================");
            ex.printStackTrace();
        }//End of catch

        uris.put("dogs_uri", properties.getProperty("BASE_URL1"));
        uris.put("cats_uri", properties.getProperty("BASE_URL2"));
        uris.put("reqs_uri", properties.getProperty("BASE_URL3"));

        return uris;
    }//End of getEndpoints method.

    public static Response getCats(String endp, String path){
        RestAssured.baseURI = endp;
        return RestAssured
                .given()
                .when().get(path).then().extract().response();
    }//End of getCats method

}//End of ApiHelpers class.
