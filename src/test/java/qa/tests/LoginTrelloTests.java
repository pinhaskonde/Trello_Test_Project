package qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qa.model.User;

public class LoginTrelloTests extends TestBase {

    @BeforeMethod
    public void preconditions() {
        if (app.session().isAvatarPresent()) {
            app.session().logout();
        }
    }

    @Test
    public void loginTest() throws InterruptedException {
        //  logger.info("login test started");

        app.session().clickOnLoginButton();
        //    app.getSession().fillLoginForm(new User ("dmitriy.konde@gmail.com", "Pinhas123$"));
        app.session().fillLoginForm(new User()
                .withEmail("dmitriy.konde@gmail.com")
                .withPassword("Pinhas123$"));
        app.session().confirmLogin();

        Assert.assertTrue(app.session().isAvatarPresentWait());

        //    logger.info("login test stopped");
    }

    @Test
    public void logoutTest() throws InterruptedException {
        app.session().clickOnLoginButton();
        app.session().fillLoginForm(new User()
                .withEmail("dmitriy.konde@gmail.com")
                .withPassword("Pinhas123$"));
        app.session().confirmLogin();

        app.session().logout();
        Assert.assertTrue(app.session().checkUserLoggedOut());
    }

    @Test
    public void negativeLoginWithoutPasswordTest() throws InterruptedException {

        app.session().clickOnLoginButton();
        //    app.getSession().fillLoginForm(new User ("rochman.elena@gmail.com", "12345.com"));
        app.session().fillLoginForm(new User()
                .withEmail("dmitriy.konde@gmail.com"));
        app.session().confirmLogin();
        Assert.assertTrue(app.session().isErrorPresent(), "Error is not present");


    }


}
