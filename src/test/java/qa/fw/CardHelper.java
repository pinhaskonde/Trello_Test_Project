package qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.model.Card;

public class CardHelper extends HelperBase {
    public CardHelper(WebDriver wd) {
        super(wd);
    }

    public void addNew() {
        waitForElementAndClick(By.cssSelector(".js-add-a-card"), 15);
    }

    public void fillForm(Card card) {
        type(By.cssSelector(".js-card-title"), card.getName());
        click(By.cssSelector(".js-cc-menu"));
        click(By.cssSelector(".js-label-selector"));
        selectLable(card.getColor());

    }

    private void selectLable(String color) {
        if (color != null) {
            click(By.cssSelector("[data-color=" + color + "]"));
        }
    }

    public void confirmCreation() {
        click(By.cssSelector(".confirm"));
    }
}
