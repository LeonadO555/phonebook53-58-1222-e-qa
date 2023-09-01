package e2e.pages;

import enums.UserCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='login-form']")
    WebElement loginForm;

    @FindBy(xpath = "//*[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//*[@name='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@type='submit']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@routerlink='/user/registration']")
    WebElement registerNewAccountLink;

    @FindBy(xpath = "//*[@routerlink='/user/forgot-password']")
    WebElement forgotPasswordLink;

    public void waitForLoading() {
        getWait().forVisibility(loginForm);
        getWait().forVisibility(emailInput);
        getWait().forVisibility(passwordInput);
//        getWait().forClickable(loginButton);
    }

    public void login(UserCredentials email, UserCredentials password) {
        emailInput.sendKeys(email.value);
        passwordInput.sendKeys(password.value);
        click(loginButton);
    }

    public void confirmSuccessfulLogin() {
        getWait().forInvisibility(loginForm);
    }

    public void confirmUnSuccessfulLogin() {
        getWait().forVisibility(loginForm);
    }

    public void clickOnRegistrationLink() {
        click(registerNewAccountLink);
    }

    public void clickOnForgotPasswordLink() {
        click(forgotPasswordLink);
    }

    public void takeScreenshotLoginButton() throws IOException {
        takeAndCompareScreenshot("loginButton", loginButton);
    }
}
