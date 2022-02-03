package qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.fw.HelperBase;
import qa.model.List;

public class ListHelper extends HelperBase {
    public ListHelper(WebDriver wd) {
        super(wd);
    }

    public void addNew() {
        click(By.cssSelector(".js-add-list"));
    }

    public void typeName(List list) {
        type(By.name("name"), list.getName());
    }

    public void saveEdit() {
        click(By.cssSelector(".js-save-edit"));
    }

    public boolean isThereAList() {
        return isElementPresent(By.cssSelector(".list"));
    }

    public void create() {
        addNew();
        typeName(new List().withName("autoTest"));
        saveEdit();

    }
}
