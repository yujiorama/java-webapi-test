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

public class StoreTest {

    private String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    public void store() {
        when()
        .get(baseUrl + "/store.json")
        .then()
        .body("store.book.findAll { it.price < 10 }.title", hasItems("Sayings of the Century", "Moby Dick"))
        .body("store.book.author.collect { it.length() }.sum()", greaterThan(50))
        .body("store.book.author*.length().sum()", greaterThan(50));

    }

}
