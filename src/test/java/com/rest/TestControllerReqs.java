package com.rest;

import com.rest.helpers.ApiHelpers;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TestControllerReqs {

    @BeforeClass
    public void setup(){
        Map<String, String> endp = ApiHelpers.getEndpoints();
        baseURI = endp.get("reqs_uri");
    }

    @Test
    public void Check_200_Reqres_Single_User(){
        given().when().get().then().statusCode(200).log().all();
    }//End of Check_200_Happy_Path_Cats method

}//End of TestControllerReqs Class.
