package qa.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.model.List;

public class ListCreationTest extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("dmitriy.konde@gmail.com", "Pinhas123$");
        }
        if(!app.board().isOnTheBoardsPage()){
            app.board().returnToHomePage();
        }
        if(!app.board().isThereABoard()){
            app.board().createBoard();
        }
    }

    @Test
    public void testListCreation(){
        app.board().selectFirstBoard();
        app.list().addNew();
        app.list().typeName(new List().withName("autoTest"));
        app.list().saveEdit();

    }
}
