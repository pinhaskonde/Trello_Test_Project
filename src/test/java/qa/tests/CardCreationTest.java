package qa.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qa.model.Card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardCreationTest extends TestBase{
    @DataProvider
    public Iterator<Object[]> validCards(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"a", "green"});
        list.add(new Object[]{"123457878797879797", "red"});

        return  list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validCardsCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/cards.csv"))) ;
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(",");
            list.add(new Object[]{new Card().withName(split[0]).withColor(split[1])});
            line = reader.readLine();
        }

        return  list.iterator();
    }

    @BeforeMethod
    public void preconditions() throws InterruptedException {
        if (!app.session().isAvatarPresent()) {
            app.session().login("dmitriy.konde@gmail.com", "Pinhas123$");
        }
        if(!app.board().isOnTheBoardsPage()){
            app.board().returnToHomePage();
        }

        Thread.sleep(3000);
        if(!app.board().isThereABoard()){
            app.board().createBoard();
        }

    }

    @Test
    public void testCardCreation(){
        app.board().selectFirstBoard();
        if(!app.list().isThereAList()){
            app.list().create();}
        app.card().addNew();
        app.card().fillForm(new Card().withName("withLable").withColor("yellow"));
        app.card().confirmCreation();
      //  app.card().addNew();
        app.card().fillForm(new Card().withName("withoutLable"));
        app.card().confirmCreation();

    }

    @Test(dataProvider = "validCards")
    public void testCardCreationDP(String name, String color){
        app.board().selectFirstBoard();
        if(!app.list().isThereAList()){
            app.list().create();}
        app.card().addNew();
        app.card().fillForm(new Card().withName(name).withColor(color));
        app.card().confirmCreation();


    }

    @Test(dataProvider = "validCardsCSV")
    public void testCardCreationDPCSV(Card card){
        app.board().selectFirstBoard();
        if(!app.list().isThereAList()){
            app.list().create();}
        app.card().addNew();
        app.card().fillForm(card);
        app.card().confirmCreation();
    }
}
