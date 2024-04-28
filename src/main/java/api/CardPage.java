package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CardPage {

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

    public void getCardInfo(String key, String token) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("/cards/{cardId}", cardId);

        response.then().statusCode(200);

        System.out.println("Card Info: " + response.body().asString());
    }

    public void updateCard(String key, String token, String newName) {
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

        System.out.println("Card updated: " + newName);
    }

    public void deleteCard(String key, String token) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .delete("/cards/{cardId}", cardId);

        response.then().statusCode(200);

        System.out.println("Card deleted");
    }

}
