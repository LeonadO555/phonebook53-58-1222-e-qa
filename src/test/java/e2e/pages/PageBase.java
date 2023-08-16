package e2e.pages;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import util.Wait;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PageBase {
    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Wait getWait() {
        return new Wait(driver);
    }

    public void click(WebElement element) {
        element.isDisplayed();
        element.click();
    }

    public void selectDropdownText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void takeAndCompareScreenshot(String actualScreenshotName, WebElement element) throws IOException {
        String expectedImageFilePath = "expectedScreenshot/" + actualScreenshotName + ".png";
        String tmpFilePath = "expectedScreenshot/tmp" + actualScreenshotName + ".png";
        File tmp;
        if (element == null) {
            tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        } else {
            tmp = element.getScreenshotAs(OutputType.FILE);
        }

        Files.copy(tmp.toPath(), new File(tmpFilePath).toPath(), StandardCopyOption.REPLACE_EXISTING);

        File expectedImageFile = new File(expectedImageFilePath);
        if (!expectedImageFile.exists()) {
            throw new RuntimeException("Expected image file does not exist" + expectedImageFilePath);
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        double maxDiffPercent = 0.01 * screenWidth * screenHeight;
        // ProcessBuilder pb = new ProcessBuilder("C:\Program Files\ImageMagick\compare.exe", "-metric", "AE", expectedImageFilePath, tmpFilePath, "null:");
        ProcessBuilder pb = new ProcessBuilder("compare", "-metric", "AE", expectedImageFilePath, tmpFilePath, "null:");
        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        double difference = 0;
        while ((line = reader.readLine()) != null) {
            difference = Integer.parseInt(line.trim());
        }
        reader.close();
        process.destroy();

        if (difference > maxDiffPercent) {
            throw new RuntimeException(expectedImageFilePath + "not equal" + tmpFilePath + "difference:" + difference);
        }

        Files.deleteIfExists(new File(tmpFilePath).toPath());
    }
}
