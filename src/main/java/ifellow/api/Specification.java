package ifellow.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.*;

public class Specification {
    public static RequestSpecification baseRequestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .log(BODY)
                .build();
    }
    public static ResponseSpecification baseResponseSpecSuccess(){
        return new ResponseSpecBuilder()
                .log(ALL)
                .build();
    }
}