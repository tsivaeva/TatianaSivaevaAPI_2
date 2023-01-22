
import org.example.RespDataCreateGetBoard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LifecycleOfTheBoard {
    private static String nameBoard = "TestFromIdea";
    private static final String endpointCreateBoard = "https://api.trello.com/1/boards/";
    private static final String endpointGetBoard = "https://api.trello.com/1/boards/?${nameBoard}";
    private static String idBoard;

    @BeforeAll
    public static void setup() {
        Specification.installSpetification(Specification.requestSpec(endpointGetBoard), Specification.responseSpec200());
    }

    @Test
    public void CreateBoard() {

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        var responce = given()
                .basePath(nameBoard)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .post(endpointCreateBoard)
                .then().extract().body().as(RespDataCreateGetBoard.class);

        idBoard = responce.getId();
        System.out.println(idBoard);
    }

    @Test
    public void GetBoard() {
        System.out.println("before" + idBoard);

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        var responce = given()
                // .queryParam("id", idBoard)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .get(endpointGetBoard)
                .then().extract().body().as(RespDataCreateGetBoard.class);

        System.out.println(responce.getShortUrl());
    }
}
