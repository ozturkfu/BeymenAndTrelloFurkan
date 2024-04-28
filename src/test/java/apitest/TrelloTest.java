package apitest;

import api.BoardPage;
import api.CardPage;
import api.ListPage;
import org.testng.annotations.Test;
import utils.Config;

import java.util.Random;

public class TrelloTest {

    private static final String BOARD_NAME = "RestAndTest";
    private static final String LIST_NAME = "Passed";

    @Test
    public void testTrelloApis() {

        String key = Config.getKey();
        String token = Config.getToken();

        BoardPage boardPage = new BoardPage();
        String boardId = boardPage.createBoard(key, token, BOARD_NAME);
        System.out.println("Board created with ID: " + boardId);

        ListPage listPage = new ListPage();
        String listId = listPage.createList(key, token, boardId, LIST_NAME);
        System.out.println("List created with ID: " + listId);

        CardPage cardPage = new CardPage();
        String cardId1 = cardPage.createCard(key, token, listId, "Task 1");
        String cardId2 = cardPage.createCard(key, token, listId, "Task 2");
        System.out.println("Cards created with IDs: " + cardId1 + ", " + cardId2);

        String randomCardId = new Random().nextBoolean() ? cardId1 : cardId2;
        cardPage.updateCard(key, token, randomCardId, "Updated Task");
        System.out.println("Card updated: " + randomCardId);

        cardPage.deleteCard(key, token, cardId1);
        cardPage.deleteCard(key, token, cardId2);
        System.out.println("All cards deleted");

        boardPage.deleteBoard(key, token, boardId);
        System.out.println("Board deleted");
    }
}
