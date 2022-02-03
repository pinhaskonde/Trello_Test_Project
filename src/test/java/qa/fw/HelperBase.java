package qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void waitForElementAndClick(By locator, int time) {
        new WebDriverWait(wd, time).until(ExpectedConditions.elementToBeClickable(locator)).click();
        // wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            wd.findElement(locator).clear();
            wd.findElement(locator).sendKeys(text);
        }
    }

    public void openTabAndSwitchToIt() throws InterruptedException {
        ((JavascriptExecutor) wd).executeScript("window.open()");

        List<String> tabs = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs.get(1));
        wd.navigate().to("https://google.com");
        Thread.sleep(10000);
    }

    public void attachFile(By locator, File file) {
        // if(file.!=null){
        wd.findElement(locator).sendKeys(file.getAbsolutePath());
        //   }
    }

    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public boolean isElementDisplayed(By locator) {
        return wd.findElement(locator).isDisplayed();
    }

    public boolean waitForElementPresent(By locator, int timeOut) {
        return new WebDriverWait(wd, timeOut)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).size() > 0;
    }

    public boolean waitForElementPresentTryCatch(By locator, int timeOut) {
        try {
            waitForElement(locator, timeOut);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }

    }

    public void waitForElement(By locator, int timeOut) {
        new WebDriverWait(wd, timeOut)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickOnPlusButton() {
//        click(By.xpath("//*[@data-test-id='header-create-menu-button']"));
        click(By.xpath("//button[@aria-label='Создайте рабочее пространство']//span//span//span"));
    }

    public void returnToHomePage() {
        waitForElement(By.cssSelector("div[class$='_2ft40Nx3NZII2i']"), 30);
        click(By.cssSelector("div[class$='_2ft40Nx3NZII2i']"));
    }

    public void confirmAction() {
        click(By.cssSelector(".js-confirm"));
    }

    public void selectFromDropDownByIndex(By locator, int index) {
        new Select(wd.findElement(locator)).selectByIndex(index);

    }

    public void selectFromDropDownByText(By locator, String text) {
        new Select(wd.findElement(locator)).selectByVisibleText(text);
    }

}
