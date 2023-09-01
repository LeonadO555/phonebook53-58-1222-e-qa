package e2e;

import e2e.pages.LoginPage;
import enums.UserCredentials;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;

    @Test
    public void userCanLoginWithValidData() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();
    }

    @Test
    public void userCanNotLoginWithInvalidEmail() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.INVALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmUnsuccessfulLogin();
    }

    @Test
    public void userCanNotLoginWithInvalidPassword() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.INVALID_PASSWORD);
        loginPage.confirmUnsuccessfulLogin();
    }

    @Test
    public void userCanNotLoginWithInvalidData() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.INVALID_EMAIL, UserCredentials.INVALID_PASSWORD);
        loginPage.confirmUnsuccessfulLogin();
    }
}