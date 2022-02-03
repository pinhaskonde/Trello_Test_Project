package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {

    @DataProvider
    public Iterator<Object[] > validLoginDataClassDP(){
        List<Object[]> list= new ArrayList<>();
        list.add(new Object[]{"pinhas@gmail.com","Pinhas123$"});

        return list.iterator();
    }

    @DataProvider
    public Iterator <Object[]> dataFileCSV() throws IOException {
        List<Object[]> list= new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(";");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line= reader.readLine();

        }
        return list.iterator();
    }
}
