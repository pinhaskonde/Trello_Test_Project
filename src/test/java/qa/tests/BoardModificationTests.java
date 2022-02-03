package qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BoardModificationTests extends  TestBase {
   @BeforeMethod
    public void preconditions() throws InterruptedException {
       if (!app.session().isAvatarPresent()) {
           app.session().login("dmitriy.konde@gmail.com", "Pinhas123$");
       }
       if(app.board().isThereABoard()){
           app.board().createBoard();
       }
   }

   @Test
    public void boardNameModificationTest(){
       int before = app.board().getBoardsCount();
       app.board().selectFirstBoard();

       app.board().editBoardName("Edited board");
       app.board().returnToHomePage();
       
       
       int after = app.board().getBoardsCount();

       Assert.assertEquals(after, before);
   }
}
