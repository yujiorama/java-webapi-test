import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.HeaderConfig;
import com.jayway.restassured.config.JsonConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.path.json.config.JsonPathConfig;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static com.jayway.restassured.config.JsonConfig.jsonConfig;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static org.assertj.core.api.Assertions.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

public class WebApiTest {

    private String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    public void example1() {
        get(baseUrl + "/lotto.json")
        .then()
        .body("lotto.lottoId", equalTo(5));
    }

    @Test
    public void example1Alternative() {
        get(baseUrl + "/lotto.json")
        .then()
        .body("lotto.winners.winnerId", hasItems(23, 54));
    }

    @Test
    public void floatAsFloat() {
        get(baseUrl + "/price.json")
        .then()
        .body("price", is(12.12f));
    }

    @Test
    public void floatAsBigDecimal() {
        given()
        .config(newConfig().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
        .when()
        .get(baseUrl + "/price.json")
        .then()
        .body("price", is(new BigDecimal("12.12")));
    }

    @Test
    public void validateProductSuccess() {
        get(baseUrl + "/products/all.json")
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("products-schema.json"));
    }

    @Test
    public void emptyJson() {
        get(baseUrl + "/empty.json")
        .then()
        .body("$", hasItems(1, 2, 3));
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
