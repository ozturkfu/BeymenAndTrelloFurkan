package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BoardPage {

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
}
