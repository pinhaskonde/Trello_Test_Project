package qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.model.Board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTests extends TestBase {
    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("dmitriy.konde@gmail.com", "Pinhas123$");
        }
    }

    @DataProvider
    public Iterator<Object[]> validBoards() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"a"});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validBoardsCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/boards.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Board().withBoardName(split[0])});
            line = reader.readLine();
        }

        return list.iterator();
    }


    @Test(dataProvider = "validBoards")
    public void boardCreationTestDataProvider1(String boardName) throws InterruptedException {
        // String boardName = "qa28Board_" + System.currentTimeMillis();
        Thread.sleep(3000);
        int before = app.board().getBoardsCount();

        app.board().clickOnPlusButton();
        app.board().selectCreateBoard();
        app.board().fillBoardCreation(new Board().withBoardName("New board "+ String.valueOf(Math.random())));
        app.board().confirmBoardCreation();
        app.board().waitForAddListButtonInTheBoard();
        String title = app.board().getTitle();
        Assert.assertEquals(title, boardName);

        app.board().returnToHomePage();
        Thread.sleep(3000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test(dataProvider = "validBoardsCSV")
    public void boardCreationTestDataProviderCSV(Board board) throws InterruptedException {
        // String boardName = "qa28Board_" + System.currentTimeMillis();
        Thread.sleep(4000);
        int before = app.board().getBoardsCount();

        app.board().clickOnPlusButton();
        app.board().selectCreateBoard();
        app.board().fillBoardCreation(board);
        app.board().confirmBoardCreation();
        app.board().waitForAddListButtonInTheBoard();
        String title = app.board().getTitle();
        // Assert.assertEquals(title, boardName);

        app.board().returnToHomePage();
        Thread.sleep(4000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

    @Test
    public void boardCreationTest() throws InterruptedException {
        String boardName = "qa_Board";
        Thread.sleep(3000);
        int before = app.board().getBoardsCount();

        app.board().clickOnPlusButton();
        app.board().selectCreateBoard();
        app.board().fillBoardCreation(new Board().withBoardName(boardName));
        app.board().confirmBoardCreation();
        app.board().waitForAddListButtonInTheBoard();
        String title = app.board().getTitle();
        Assert.assertEquals(title, boardName);

        app.board().returnToHomePage();
        Thread.sleep(3000);
        int after = app.board().getBoardsCount();
        Assert.assertEquals(after, before + 1);
    }

}
