package e2e;

import e2e.pages.LoginPage;
import enums.UserCredentials;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    LoginPage loginPage; //инициализируем переменную и типизируем ее классом
    // (т.е в переменной loginPage будет лежать все, что в классе LoginPage - все методы)

    @Test
    public void userCanLoginWithValidData() {
        loginPage = new LoginPage(app.driver); // переменная loginPage берет на себя новый экземпляр класса
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmSuccessfulLogin();
    }

    @Test
    public void userCanNotLoginWithInvalidEmail() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.INVALID_EMAIL, UserCredentials.VALID_PASSWORD);
        loginPage.confirmUnSuccessfulLogin();
    }

    @Test
    public void userCanNotLoginWithInvalidPassword() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.VALID_EMAIL, UserCredentials.INVALID_PASSWORD);
        loginPage.confirmUnSuccessfulLogin();
    }

    @Test
    public void userCanNotLoginWithInvalidData() {
        loginPage = new LoginPage(app.driver);
        loginPage.login(UserCredentials.INVALID_EMAIL, UserCredentials.INVALID_PASSWORD);
        loginPage.confirmUnSuccessfulLogin();
    }
}
