package qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;

public class AtlassianHelper extends HelperBase {
    public AtlassianHelper(WebDriver wd) {
        super(wd);
    }

    public void initChangePhoto() {
        new Actions(wd)
                .moveToElement(wd.findElement(By.cssSelector("[data-test-selector='profile-avatar']")))
                .pause(3).click(wd.findElement(By.cssSelector(".css-fbv3aj"))).perform();
        click(By.xpath("//*[@role='menuitem'][1]"));
    }

    public void uploadPhoto() throws InterruptedException {
        attachFile(By.cssSelector("[id=image-input]"),
                new File("/Users/workspace/my/Foto/1.JPG"));

        waitForElementAndClick(By.xpath("//*[contains(.,'Upload')]/.."), 10);
        Thread.sleep(5000);
    }
}
