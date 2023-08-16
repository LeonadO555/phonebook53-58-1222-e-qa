package e2e;

import e2e.pages.LoginPage;
import enums.UserCredentials;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage;

    @Test

    public void userCanLoginWithValidData() {
        String email = "test@gmail.com";
        String password = "test@gmail.com";
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();
    }

    @Test
    public void userCanLoginWithInvalidEmail() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();
    }

    @Test
    public void userCanLoginWithInvalidPassword() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();
    }


}
