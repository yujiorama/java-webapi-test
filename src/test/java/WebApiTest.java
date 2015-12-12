import com.jayway.restassured.RestAssured;
import com.jayway.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
}
