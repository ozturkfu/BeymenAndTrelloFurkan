package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListPage {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private String listId;

    public ListPage(String listId) {
        this.listId = listId;
    }

    public void addCard(String key, String token, String cardName) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", cardName)
                .queryParam("idList", listId)
                .when()
                .post("/cards");

        response.then().statusCode(200);

        System.out.println("Card added to list " + listId + ": " + cardName);
    }

    public void getCards(String key, String token) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("/lists/" + listId + "/cards");

        response.then().statusCode(200);

        System.out.println("Cards in list " + listId + ": " + response.body().asString());
    }
}

    /* public CardPage[] listCards(String key, String token, String boardId) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("/boards/{boardId}/cards", boardId);

        response.then().statusCode(200);

        System.out.println("Cards in board " + boardId + ":");
        System.out.println(response.body().asString());

        CardPage[] cardPages = response.as(CardPage[].class);

        System.out.println("Lists in board " + boardId + ":");
        for (CardPage cardPage : cardPages) {
            System.out.println("Card ID: " + cardPage.getId() + ", Name: " + cardPage.getName());
        }

        return cardPages;
    }
} */
