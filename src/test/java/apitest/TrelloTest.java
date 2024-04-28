package apitest;

import api.BoardPage;
import api.CardPage;
import api.ListPage;
import org.testng.annotations.Test;
import utils.Config;

public class TrelloTest {

    private String boardId = "HXHZBlnZ";
    private String restListId = "662e43e1d275261fad6403f3";

    @Test
    public void testTrelloApis() {

        String key = Config.getKey();
        String token = Config.getToken();

        BoardPage boardPage = new BoardPage(boardId);
        boardPage.getBoardInfo(key, token);

        ListPage[] listPages = boardPage.getLists(key, token);

        ListPage listPage = new ListPage(restListId);
        // listPage.addCard(key, token, "New Project"); It can be checked by deleting the comment line.
        listPage.getCards(key,token);

        // CardPage[] cardPages = listPage.listCards(key,token,boardId);

        // cardIds wvxssMXW,pIO5NM4W,njwVJTra,zpGTShiM,dWIIhXi5,3OLHjkkj
        CardPage cardPage = new CardPage("3OLHjkkj");
        cardPage.getCardInfo(key, token);
        // cardPage.deleteCard(key,token);
        cardPage.updateCard(key,token, "RestTest");
    }
}
