package utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializers {

    public static WebDriver driver;

    public void openBrowserAndLauchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        intializePageObjects();
    }

    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    public static void tearDown() {
        driver.quit();
    }


    public static byte[] takeScreenshot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES); // the output is bytes
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName
                    + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static void moveToElement(WebElement element) {
        Actions moveTo = new Actions(driver);
        moveTo.moveToElement(element);
    }

    public static void selectingDDQualification(String desiredQualification) {
        List<WebElement> qualificationDD = driver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[2]/ul/li[1]/ul/li[4]/ul/li/a"));
        for (WebElement qualification : qualificationDD) {
            String text = qualification.getText();
            System.out.println(text);
            if (qualification.getText().equals(desiredQualification)) {
                text = qualification.getText();
                qualification.click();
                break;
            }
        }
    }

    public static void adminSubTabs(String value) {
        click(qualificationsPage.adminTab);
        List<WebElement> list = driver.findElements(By.xpath("//*[@id=\"wrapper\"]/div[2]/ul/li[1]/ul/li"));
        for (WebElement subTab : list) {
            String text = subTab.getText();
            if (text.equals(value)) {
                subTab.click();
                break;
            }
        }
    }


    public static void selectDdValue(WebElement element, int index) {
        Select select = new Select(element);
        int size = select.getOptions().size();
        if (size > index) {
            select.selectByIndex(index);
        }
    }

    public static void selectDdValue(WebElement element, String textToSelect) {
        Select select = new Select(element);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().equals(textToSelect)) {
                select.selectByVisibleText(textToSelect);
                break;
            }
        }
    }

    public static void selectDay(List<WebElement> listOfDays, String desiredDay) {
        for (WebElement day : listOfDays) {
            String dayText = day.getText();
            if (dayText.equals(desiredDay)) {
                day.click();
                break;
            }
        }
    }

    public static void handlingCalendar(List<WebElement> listOfDays, String desiredDay, WebElement monthElement, String month, WebElement yearElement, String year) {
        for (WebElement day : listOfDays) {
            String dayText = day.getText();
            if (dayText.equals(desiredDay)) {
                day.click();
                break;
            }
        }
        selectDdValue(monthElement, month);
        selectDdValue(yearElement, year);
    }

    public static void chooseDate(List<WebElement> dates, String dateNumber) {
        for (WebElement date : dates) {
            String dateTest = date.getText();
            if (dateTest.equals(dateNumber)) {
                date.click();
                break;
            }
        }
    }
}

