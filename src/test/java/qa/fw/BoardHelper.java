package qa.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import qa.model.Board;

public class BoardHelper extends HelperBase {

    public BoardHelper(WebDriver wd) {
        super(wd);
    }

    public String getTitle() {
        return wd.findElement(By.xpath("//h1")).getText();
    }

    public void confirmBoardCreation() {
        click(By.xpath("//*[@data-test-id='create-board-submit-button']"));
    }

    public void fillBoardCreation(Board board) {
        type(By.xpath("input[id$='1643854707632-create-team-org-display-name']"), board.getBoardName());
    }

    public void selectCreateBoard() {
        click(By.cssSelector("span[class='_1Z1iF7nN0vus8a'] span[class$='icon-add icon-sm _1LYx9mtQqsgQHT']"));
    }

    public void waitForAddListButtonInTheBoard() {
        waitForElement(By.cssSelector("button[data-test-id='create-board-submit-button']"), 15);
    }

    public void deleteBoard() {
        if (!isElementPresent(By.cssSelector(".js-open-more"))) {
            click(By.cssSelector(".icon-back"));
        }
        if (isElementPresent(By.cssSelector("[title='QR Code']"))) {
            click(By.cssSelector("button[aria-label='Close popover']"));
        }
        click(By.cssSelector(".js-open-more"));
        click(By.cssSelector(".js-close-board"));
        confirmAction();
        click(By.cssSelector(".js-delete"));
        confirmAction();
    }

    public void openMenu() {
        if (isElementDisplayed(By.cssSelector(".js-show-sidebar"))) {
            click(By.cssSelector(".js-show-sidebar"));
        }
    }

    public int getBoardsCount() {
        return wd.findElements(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li")).size() - 1;
    }

    public void selectFirstBoard() {
        click(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li"));
    }

//    public void clearBoardList() throws InterruptedException {
//        int boardsCount = getBoardsCount();
//        while (boardsCount > 1) {
//            selectFirstBoard();
//            openMenu();
//            deleteBoard();
//            returnToHomePage();
//            Thread.sleep(4000);
//            boardsCount = getBoardsCount();
//        }
//    }

    public boolean isThereABoard() {
        return getBoardsCount() > 0;
    }

    public void createBoard() {
        clickOnPlusButton();
        selectCreateBoard();
        fillBoardCreation(new Board().withBoardName("experiment"));
        confirmBoardCreation();
        returnToHomePage();
    }

    public void editBoardName(String name) {
        click(By.cssSelector("input[class$='list-name-input']"));
        wd.findElement(By.cssSelector("input[class$='list-name-input']")).sendKeys(name + Keys.ENTER);
    }

    public boolean isOnTheBoardsPage() {
        return isElementPresent(By.xpath("//*[contains(@class, 'boards-page-board-section-header-icon-default-image')]/../../../..//li"));
    }
}
