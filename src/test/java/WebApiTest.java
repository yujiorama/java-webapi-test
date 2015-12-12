import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.JsonConfig;
import com.jayway.restassured.config.RestAssuredConfig;
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
    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertThat(classUnderTest.someLibraryMethod()).isEqualTo(true);
    }

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    public void example1() {
        get("/lotto.json")
        .then()
        .body("lotto.lottoId", equalTo(5));
    }

    @Test
    public void example1Alternative() {
        get("/lotto.json")
        .then()
        .body("lotto.winners.winnerId", hasItems(23, 54));
    }

    @Test
    public void floatAsFloat() {
        get("/price.json")
        .then()
        .body("price", is(12.12f));
    }

    @Test
    public void floatAsBigDecimal() {
        given()
        .config(newConfig().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
        .when()
        .get("/price.json")
        .then()
        .body("price", is(new BigDecimal("12.12")));
    }

    @Test
    public void validateProductSuccess() {
        get("/products/all.json")
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath("products-schema.json"));
    }

}
