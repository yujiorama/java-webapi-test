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

public class EmptyTest {

    private String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    public void emptyJson() {
        get(baseUrl + "/empty.json")
        .then()
        .body("$", hasItems(1, 2, 3));
    }

}
