package e2e.pages;

import io.qameta.allure.Step;
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
        getWait().forClickable(loginButton);
    }

    @Step("login with user: test@gmail.com")
    public void login() {
        emailInput.sendKeys("test@gmail.com");
        passwordInput.sendKeys("test@gmail.com");
        click(loginButton);
    }

    @Step("confirm successful login")
    public void confirmSuccessfulLogin() {
        getWait().forInvisibility(loginForm);
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
