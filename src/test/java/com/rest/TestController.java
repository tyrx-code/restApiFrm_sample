package com.rest;

import com.rest.helpers.ApiHelpers;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestController {
    String cats, dogs;

    @BeforeClass
    public void setup(){
        Map<String, String> endp = ApiHelpers.getEndpoints();
        cats = endp.get("cats_uri");
        dogs = endp.get("dogs_uri");
    }
    @Test
    public void Check_200_Happy_Path_Cats(){
        baseURI = cats;
        given().when().get().then().statusCode(200).log().all();
    }//End of Check_200_Happy_Path_Cats method

    @Test
    public void Check_200_Happy_Path_Dogs(){
        baseURI = dogs;
        given().when().get().then().statusCode(200).log().all();
    }//End of Check_200_Happy_Path_Dogs method

    @Test
    public void Check_200_Cats_Facts(){
        Response resp = ApiHelpers.getCats(cats,"/facts");
        System.out.println("Response is: "+ resp.toString());

        Check_Basic_Metrics(resp);
    }//End of Check_200_Happy_Path_Dogs method

    public void Check_Basic_Metrics(Response resp){
        Assert.assertEquals(resp.statusCode(),200);
        Assert.assertEquals(resp.contentType(),"application/json");
        Assert.assertTrue(resp.getTime() < 1500);
    }//End of Check_Basic_Metrics method.

}//End of TestController Test.
