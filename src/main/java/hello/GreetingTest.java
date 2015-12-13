package hello;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class GreetingTest {

    private String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    public void greeting() {
        given()
        .parameters("firstName", "John", "lastName", "Doe")
        .header("content-type", ContentType.TEXT)
        .when()
        .post(baseUrl + "/greeting")
        .then()
        .body("greeting.firstName", is("John"))
        .body("greeting.lastName", is("Doe"))
        .body(hasXPath("/greeting/firstName", containsString("Jo")));
    }
}
