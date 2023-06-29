package e2e.pages;

import enums.Languages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends PageBase {
    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@fill-rule='evenodd']")
    WebElement logoIcon;

    @FindBy(xpath = "//*[@class ='collapse navbar-collapse']//*[@href = '/']")
    WebElement contactButton;

    @FindBy(xpath = "//*[@href='/contacts']")
    WebElement addNewContactButton;

    @FindBy(xpath = "//*[@id='langSelect']")
    WebElement languageDropdown;

    @FindBy(xpath = "//*[@ng-reflect-router-link='/account']")
    WebElement accountButton;

    @FindBy(xpath = "//*[@type='submit'][2]")
    WebElement logoutButton;

    public void waitForLoading() {
        getWait().forVisibility(logoIcon);
        getWait().forVisibility(contactButton);
        getWait().forVisibility(addNewContactButton);
        getWait().forVisibility(languageDropdown);
        getWait().forVisibility(accountButton);
        getWait().forVisibility(logoutButton);
    }

    public void clickOnLogoIcon() {
        click(logoIcon);
    }

    public void openContactPage() {
        click(contactButton);
    }

    public void openAddNewContactDialog() {
        click(addNewContactButton);
    }

    public void selectLanguage(Languages language) {
        selectDropdownText(languageDropdown, language.value);
    }

    public void openAccountPage() {
        click(accountButton);
    }

    public void logout() {
        click(logoutButton);
    }
}
