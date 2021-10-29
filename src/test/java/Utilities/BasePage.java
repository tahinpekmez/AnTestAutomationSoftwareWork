package Utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Configuration configuration;
    protected Select select;
    protected Actions action;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 30);
        this.configuration = new Configuration();


    }

    protected void refresh() {
        driver.navigate().refresh();
    }

    //Navigate Methods
    protected void navigateToURL(String URL) {
        try {
            waitForLoad(driver);
            driver.navigate().to(URL);
        } catch (Exception e) {
            refresh();
            waitForLoad(driver);
            driver.navigate().to(URL);
        }
    }


    // Js methods
    protected void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 1px dashed red;");
        } catch (Exception e) {
            refresh();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 1px dashed red;");
        }
    }




    //Switch Methods
    protected void switchToTab(int index) {
        try {
            waitForNewWindow(driver, 10);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0)).close();
            driver.switchTo().window(tabs.get(index));
        } catch (Exception e) {
            refresh();
            waitForNewWindow(driver, 10);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0)).close();
            driver.switchTo().window(tabs.get(index));
        }

    }






    protected void closeTab(int tab) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tab)).close();
    }



    protected void printAllFrame() {
        waitForNewWindow(driver, 10);
        List<WebElement> elements = driver.findElements(By.name("iframe"));
        for (WebElement element : elements) {
            System.out.println(element);
        }
    }






    protected void clickAllInList(List<WebElement> elements) {
        for (WebElement element : elements) {
            element.click();
        }
    }



    public boolean CheckImage() {
        WebElement ImageFile = driver.findElement(By.xpath("//img[@id='imgurunresmi']"));
        return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
    }



    protected void switchToIframeWithIndex(int index) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        }
    }



    protected void switchToIframeWithElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        }
    }



    protected void switchToIframe() {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findElementByClassName("fancybox-iframe")));

        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findElementByClassName("fancybox-iframe")));
        }
    }

    protected void switchToIframeByClass(String className) {
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findElementByClassName(className)));

        } catch (Exception e) {
            refresh();
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(findElementByClassName(className)));
        }
    }



    protected void switchToIframeDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            refresh();
            driver.switchTo().defaultContent();
        }
    }




    protected void selectComboBox(WebElement element, String dataValue) {
        select = new Select(element);
        select.selectByValue(dataValue);
    }

    protected void selectComboBoxByValue(String id, String dataValue) {
        select = new Select(findElementById(id));
        select.selectByValue(dataValue);
    }

    protected void selectComboBoxWithText(WebElement element, String Text) {
        select = new Select(element);
        select.selectByVisibleText(Text);
    }

    protected void selectComboBoxId(String id, String text) {
        select = new Select(findElementById(id));
        select.selectByVisibleText(text);
    }

    protected void selectComboBoxName(String name, String text) {
        select = new Select(findElementByName(name));
        select.selectByVisibleText(text);
    }



    protected void selectComboBoxId(String id, int index) {
        select = new Select(findElementById(id));
        select.selectByIndex(index);
    }


    protected void selectComboBoxById(String id, String text) {
        select = new Select(findElementById(id));
        select.selectByVisibleText(text);
    }


    protected void selectComboBoxByIdByValue(String id, String value) {
        select = new Select(findElementById(id));
        select.selectByValue(value);
    }

    protected void selectComboBoxByName(String name, String text) {
        select = new Select(findElementByName(name));
        select.selectByVisibleText(text);
    }

    protected void selectComboBoxIdIndex(String id, int index) {
        select = new Select(findElementById(id));
        select.selectByIndex(index);
    }

    protected void selectComboBoxIdLastChild(String id) {
        select = new Select(findElementById(id));
        List<WebElement> l = select.getOptions();
        select.selectByIndex(l.size() - 1);
    }


    //Random methods
    protected String randomEmail() {
        String mail = RandomStringUtils.randomAlphabetic(5);
        return mail.toLowerCase() + "@gmail.com";
    }

    protected String randomName() {
        String name = RandomStringUtils.randomAlphabetic(7);
        return name.toLowerCase();
    }

    protected String randomNumberString(int bound) {
        Random random = new Random();
        int r = random.nextInt(bound);
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return decimalFormat.format(r);
    }


    // include bound number
    protected int randomNumber(int bound, int min) {
        Random random = new Random();
        return random.nextInt(bound - min + 1) + 1;
    }


    protected int randomNumberFromAnyIndex(int bound, int min) {
        return ThreadLocalRandom.current().nextInt(min, bound);
    }


    // max bound is bound - 1
    protected int randomNumber(int bound) {
        Random random = new Random();
        return random.nextInt(bound);
    }



    protected String randomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( 15, characters );
    }




    // Find Method
    protected WebElement findElementById(String id) {
        waitUntilPresenceOfElementId(id);
        return driver.findElement(By.id(id));
    }

    protected WebElement findElementByName(String name) {
        waitUntilPresenceOfElementName(name);
        return driver.findElement(By.name(name));
    }

    protected WebElement findElementByClassName(String className) {
        waitUntilPresenceOfElementClass(className);
        return driver.findElement(By.className(className));
    }

    protected WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));

    }

    protected WebElement findElementByCss(String css) {
         return driver.findElement(By.cssSelector(css));
    }




    //Click Method
    protected void clickElementById(String id) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableId(id);
        highlightElement(findElementById(id));
        findElementById(id).click();
    }


    protected void clickElementByClassName(String className) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableClass(className);
        highlightElement(findElementByClassName(className));
        findElementByClassName(className).click();
    }

    protected void clickElementByXpath(String xpath) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableXpath(xpath);
        highlightElement(findElementByXpath(xpath));
        findElementByXpath(xpath).click();
    }

    protected void clickElementByCSS(String css) {
        waitUntilJSIsReady();
        waitUntilElementIsClickableCSS(css);
        highlightElement(findElementByXpath(css));
        findElementByCss(css).click();
    }






    //fill method
    protected void fillInputFieldById(String id, String charSequence) {
        waitUntilJSIsReady();
        findElementById(id).sendKeys(charSequence);
    }


    protected void fillInputFieldByName(String name, String charSequence) {
        waitUntilJSIsReady();
        findElementByName(name).sendKeys(charSequence);
    }

    protected void fillInputFieldByClassName(String className, String charSequence) {
        waitUntilJSIsReady();
        findElementByClassName(className).sendKeys(charSequence);
    }



    protected void fillInputFieldByXpath(String xpath, String charSequence) {
        waitUntilJSIsReady();
        findElementByXpath(xpath).sendKeys(charSequence);
    }




    protected void hoverToElementByClassName(String className) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            action = new Actions(driver);
            action.moveToElement(findElementByClassName(className)).build().perform();
        }catch (Exception e){
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
            action = new Actions(driver);
            action.moveToElement(findElementByClassName(className)).build().perform();
        }
    }


    protected void hoverToElementByXpath(String xpath) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            action = new Actions(driver);
            action.moveToElement(findElementByXpath(xpath)).build().perform();
        }catch (Exception e){
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            action = new Actions(driver);
            action.moveToElement(findElementByXpath(xpath)).build().perform();
        }
    }


    protected void hoverToElementById(String id) {
        try {
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
            action = new Actions(driver);
            action.moveToElement(findElementById(id)).build().perform();
        }catch (Exception e){
            refresh();
            waitUntilJSIsReady();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(id)));
            action = new Actions(driver);
            action.moveToElement(findElementById(id)).build().perform();
        }
    }



    protected void hoverAndClick(WebElement element, Keys charSequence) {
        action = new Actions(driver);
        action.sendKeys(element, charSequence);
    }



    protected void HoverAndClick(WebElement element) {
        try {
            waitUntilJSIsReady();
            action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
        }catch (Exception e){
            refresh();
            waitUntilJSIsReady();
            action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
        }

    }
    public void scrollMoveToElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",element );
    }

    public void scrollToElementById(String id){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",findElementById(id));
    }

    public void scrollToElementByXpath(String xpath){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",findElementByXpath(xpath));
    }

    public void scrollToElementByName(String name){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",findElementById(name));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
    }

    public void scrollToElementByCSS(String css){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.cssSelector(css)));

    }

    //Wait Method
    protected void waitUntilElementIsClickableXpath(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByXpath(xpath)));
    }



    protected void waitUntilElementIsClickableClass(String className) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByClassName(className)));
    }

    protected void waitUntilElementIsClickableCSS(String css) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementByCss(css)));
    }



    protected void waitUntilElementIsClickableId(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(findElementById(id)));
    }




    protected void waitUntilPresenceOfElementId(String id) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));

        }catch (Exception e){
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        }
    }

    protected void waitUntilPresenceOfElementName(String name) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));

        }catch (Exception e){
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(name)));
        }
    }

    protected void waitUntilPresenceOfElementClass(String className) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        }catch (Exception e){
            refresh();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
        }
    }



    protected void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }




    boolean p = true;
    public void verifyWebElementIsNotPresenceByXpath(String xpath){

        try{
            findElementByXpath(xpath);
        }catch (NoSuchElementException e){
            p = false;
        }
        Assert.assertFalse(p);
    }


    public boolean verifyWebElementIsNotPresenceByXpathBoolean(String xpath){

        try{
            findElementByXpath(xpath);
        }catch (NoSuchElementException e){
            p = false;
        }
        return p;
    }



    public void verifyWebElementIsNotPresenceById(String id){
        try{
            findElementById(id);

        }catch (NoSuchElementException e){
            p = false;
        }
        Assert.assertFalse(p);
    }





    public void waitForNewWindow(WebDriver driver, int timeout) {
        try {
            boolean flag = false;
            int counter = 0;

            while (!flag) {

                try {
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

                    if (tabs.size() > 1) {
                        flag = true;
                        return;
                    }

                    sleep(1);

                    counter++;
                    if (counter > timeout) {
                        return;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        }catch (Exception e){
            refresh();

            boolean flag = false;
            int counter = 0;

            while (!flag) {

                try {
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    if (tabs.size() > 1) {
                        flag = true;
                        return;
                    }
                    sleep(1);
                    counter++;
                    if (counter > timeout) {
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }
        }
    }


    protected void waitUntilJSIsReady() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        sleep(200L);
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        sleep(200L);
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);

    }



    public void sleep(long millis){
        try{
            Thread.sleep(millis);

        }catch (Exception e){
            e.getCause();
        }
    }



}
