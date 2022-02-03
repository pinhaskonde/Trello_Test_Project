package qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import qa.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SessionHelper extends HelperBase {
    Properties properties;

    public SessionHelper(WebDriver wd, Properties properties) {
        super(wd);
        this.properties = properties;
    }

    public void login(String email, String password) throws InterruptedException {
        click(By.xpath("//a[@href='/login']"));
        type(By.xpath("//*[@name='user']"), email);
        Thread.sleep(2000);
        click(By.xpath("//input[@id='login']"));
        type(By.xpath("//*[@name='password']"), password);
        click(By.xpath("//button[@id='login-submit']"));
    }

    public void logout() {
        clickOnAvatar();
        selectLogoutFromDropDownList();
        confirmLogout();


    }

    public boolean checkUserLoggedOut() {
        return wd.getCurrentUrl().equals("https://trello.com/logged-out");
    }

    private void confirmLogout() {
        click(By.cssSelector("#logout-submit"));
    }

    public void selectLogoutFromDropDownList() {
        click(By.cssSelector("[data-test-id$=logout]"));
    }

    public void clickOnAvatar() {
        click(By.cssSelector("[data-test-id=header-member-menu-button]"));
    }

    public void confirmLogin() {
        click(By.xpath("//button[@id='login-submit']"));
    }

    public void fillLoginForm(User user) throws InterruptedException {
        type(By.xpath("//*[@name='user']"), user.getEmail());
        Thread.sleep(2000);
        click(By.xpath("//input[@id='login']"));
        type(By.xpath("//*[@name='password']"), user.getPassword());
    }

    public void clickOnLoginButton() {
        click(By.xpath("//a[@href='/login']"));
    }

    public boolean isAvatarPresent() {
        return isElementPresent(By.xpath("//*[@data-test-id='header-member-menu-button']"));
    }

    public boolean isAvatarPresentWait() {
        return waitForElementPresentTryCatch(By.xpath("//ul[@class = 'boards-page-board-section-list']"), 20);
    }

    public boolean isErrorPresent() {
        return isElementPresent(By.cssSelector("#password-error"));
    }

    public void openUsersProfile() {
        click(By.cssSelector("[data-test-id='header-member-menu-profile']"));

    }


    public void goToAtlassianAccount() {
        click(By.cssSelector("[href$='manage-profile']"));
        ArrayList<String> tabs = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs.get(1));

    }

    public void returnToTrelloFromAtlassian() {
        List<String> tabs = new ArrayList<>(wd.getWindowHandles());
        wd.close();
        wd.switchTo().window(tabs.get(0));

    }
}
