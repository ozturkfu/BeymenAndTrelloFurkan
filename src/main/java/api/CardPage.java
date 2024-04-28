package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CardPage {

    public CardPage() {

    }

    private String cardId;

    private String id1;
    private String name2;

    public String getId() {
        return id1;
    }

    public String getName() {
        return name2;
    }

    public CardPage(String cardId) {
        this.cardId = cardId;
    }

    public String createCard(String key, String token, String listId, String cardName) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", cardName)
                .queryParam("idList", listId)
                .contentType(ContentType.JSON)
                .when()
                .post("/cards");

        response.then().statusCode(200);

        return response.jsonPath().getString("id");
    }

    public void updateCard(String key, String token, String cardId, String newName) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", newName)
                .when()
                .put("/cards/{cardId}", cardId);

        response.then().statusCode(200);
    }

    public void deleteCard(String key, String token, String cardId) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .delete("/cards/{cardId}", cardId);

        response.then().statusCode(200);
    }
}


