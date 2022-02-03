package qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardDeletionTests extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("dmitriy.konde@gmail.com", "Pinhas123$");
        }
        if(!app.board().isOnTheBoardsPage()){
            app.board().returnToHomePage();
        }
    }

    @Test
    public void testBoardDeletion() throws InterruptedException {
        Thread.sleep(3000);
        int before = app.board().getBoardsCount();
        app.board().selectFirstBoard();
        app.board().openMenu();
        app.board().deleteBoard();
        app.board().returnToHomePage();
        Thread.sleep(3000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before - 1);
    }
}



