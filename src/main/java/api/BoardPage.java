package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BoardPage {
    public BoardPage() {

    }
    private String boardId;

    public BoardPage(String boardId) {
        this.boardId = boardId;
    }

    public void getBoardInfo(String key, String token) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("/boards/" + boardId);

        response.then().statusCode(200);

        System.out.println("Board Info: " + response.body().asString());
    }

    public ListPage[] getLists(String key, String token) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .get("/boards/" + boardId + "/lists");

        response.then().statusCode(200);

        ListPage[] listPages = response.as(ListPage[].class);

        System.out.println("Lists in board " + boardId + ":");
        for (ListPage listPage : listPages) {
            System.out.println("List ID: " + listPage.getId() + ", Name: " + listPage.getName());
        }

        return listPages;
    }

    public String createBoard(String key, String token, String boardName) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .contentType(ContentType.JSON)
                .queryParam("name", boardName)
                .when()
                .post("/boards");

        response.then().statusCode(200);

        return response.jsonPath().getString("id");
    }

    public void deleteBoard(String key, String token, String boardId) {
        String baseUrl = "https://api.trello.com/1";
        Response response = RestAssured
                .given()
                .baseUri(baseUrl)
                .queryParam("key", key)
                .queryParam("token", token)
                .when()
                .delete("/boards/{boardId}", boardId);

        response.then().statusCode(200);
    }
}

