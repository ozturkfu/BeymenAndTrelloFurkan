package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ListPage {

    public ListPage() {

    }

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

    public String createList(String key, String token, String boardId, String listName) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .queryParam("key", key)
                .queryParam("token", token)
                .queryParam("name", listName)
                .queryParam("idBoard", boardId)
                .when()
                .post("/lists");

        response.then().statusCode(200);

        return response.jsonPath().getString("id");
    }
}



