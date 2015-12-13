package hello;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.parsing.Parser;
import com.jayway.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    private String baseUrl = "http://localhost:8080/";

    @BeforeClass
    public static void beforeClass() {
        RestAssured.registerParser("text/json", Parser.JSON);
    }

    @Test
    public void title() {
        String nextTitleLink =
                given().
                    param("param_name", "param_value").
                when().
                    get(baseUrl + "/title.json").
                then().
                    contentType(ContentType.JSON).
                    body("title", is("My Title")).
                extract().
                    path("_links.next.href");
        assertThat(nextTitleLink).isEqualTo("/title?page=2");
    }

    @Test
    public void title2() {
        Response response = given().
                param("param_name", "param_value").
                when().
                get(baseUrl + "/title.json").
                then().
                contentType(ContentType.JSON).
                body("title", is("My Title")).
                extract().
                response();
        String nextTitleLink = response.path("_links.next.href");
        assertThat(nextTitleLink).isEqualTo("/title?page=2");
        String headerValue = response.header("headerName");
        assertThat(headerValue).isNull();
    }

}
