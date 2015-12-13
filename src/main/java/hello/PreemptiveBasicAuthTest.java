package hello;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PreemptiveBasicAuthTest {

    private String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    @Ignore
    public void auth() {
        given().
            auth().
            preemptive().
            basic("user", "pass").
        when().
            get(baseUrl).
        then().
            body("base", is(nullValue()));
    }
}
