
import org.example.RespDataCreateBoard;
import org.example.RespDataDeleteBoard;
import org.example.RespDataGetBoard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LifecycleOfTheBoard {

    private static String name = "TestFromIdea";
    private static String idBoard;// = "63cd87f5fbf45f036074b8f5";
    private static String url = "https://api.trello.com/1/boards/";
    private String urlGetBoard = url + idBoard + "/memberships";
    private String urlUpdateDeleteBoard = url + idBoard;

    @BeforeAll
    public static void setup() {

    }

    @Test
    public void CreateBoard() {

        Specification.installSpetification(Specification.requestSpec(url), Specification.responseSpec200());

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        System.out.println(name);
        var responce = given()
                .queryParam("name", name)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .post(url)
                .then().extract().body().as(RespDataCreateBoard.class);
        idBoard = responce.id;
        System.out.println(idBoard);
    }

    @Test
    public void GetBoard() {
        Specification.installSpetification(Specification.requestSpec(urlGetBoard), Specification.responseSpec200());

        System.out.println("before" + idBoard);

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        var responce = given()
                // .queryParam("id", idBoard)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .get(urlGetBoard)
                .then().extract().body().as(RespDataGetBoard.class);
    }

    private static String requestUpdateBody = "{\n" +
            "  \"name\": \"TestFrom UpdateName\"\n}";

    @Test
    public void UpdateBoard() {

        Specification.installSpetification(Specification.requestSpec(urlUpdateDeleteBoard), Specification.responseSpec200());

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        System.out.println(name);
        var responce = given()
                .body(requestUpdateBody)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .put(urlUpdateDeleteBoard)
                .then().extract().body().as(RespDataCreateBoard.class);
        idBoard = responce.id;
        System.out.println(idBoard);
    }

    @Test
    public void DeleteBoard() {

        Specification.installSpetification(Specification.requestSpec(urlUpdateDeleteBoard), Specification.responseSpec200());

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        System.out.println(name);
        var responce = given()
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .delete(urlUpdateDeleteBoard)
                .then().extract().body().as(RespDataDeleteBoard.class);
    }
}
