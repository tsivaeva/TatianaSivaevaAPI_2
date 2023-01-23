
import org.junit.jupiter.api.*;
import org.example.RespDataCreateBoard;
import org.example.RespDataDeleteBoard;
import org.example.RespDataGetBoard;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LifecycleOfTheBoard {

    private static String name = "TestFromIdea2301";
    private static String idBoard;
    private static String idName;
    private static String url = "https://api.trello.com/1/boards/";
    private String urlGetBoard = url + idBoard + "/memberships";
    private String urlUpdateDeleteBoard = url + idBoard;

    private static final String  updateName = "TestFrom UpdateName";
    private static final String requestUpdateBody = "{\n" +
            "  \"name\": \"" + updateName + "\"\n}";

    @BeforeAll
    public static void setup() {

    }

    @Test
    @Order(1)

    public void CreateBoard() {

        Specification.installSpetification(Specification.requestSpec(url), Specification.responseSpec200());

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";

        var responce = given()
                .queryParam("name", name)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .post(url)
                .then().extract().body().as(RespDataCreateBoard.class);

        idBoard = responce.id;
        idName = responce.name;
        Assertions.assertNotNull(idBoard);
        Assertions.assertNotNull(idName);

        System.out.println("\nCreateBoard idBoard " + idBoard);
        System.out.println("CreateBoard name " + idName);
    }

    @Test
    @Order(2)
    public void GetBoard() {
        System.out.println("GetBoard idBoard from url Before run" + idBoard);
        Specification.installSpetification(Specification.requestSpec(urlGetBoard), Specification.responseSpec200());

        String token = "ATTAdc25aa5bf5b283df35d9f926c9f4fbe959349d004d718c67c296fdb99abe353d8E81F928";
        String api_key = "702228c97231235e3680ac588adde576";
        var responce = given()
                // .queryParam("id", idBoard)
                .queryParam("key", api_key)
                .queryParam("token", token)
                .when()
                .get(urlGetBoard)
                .then().extract().jsonPath().getList("", RespDataGetBoard.class);
        Assertions.assertEquals("", "");

        Assertions.assertNotNull(responce.get(0).id);
        Assertions.assertNotNull(responce.get(0).idMember);

        System.out.println("\nGetBoard id " + responce.get(0).id);
        System.out.println("GetBoard idMember " + responce.get(0).idMember);
    }

    @Test
    @Order(3)
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

        System.out.println("\nUpdateBoard idBoard " + responce.id);
        System.out.println("UpdateBoard name " + responce.name);

        Assertions.assertEquals(responce.id,idBoard);
        Assertions.assertEquals(responce.name,updateName);
    }

    @Test
    @Order(4)
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

        Assertions.assertNull(responce._value);
        System.out.println("\nDeleteBoard " + responce._value);
    }
}
