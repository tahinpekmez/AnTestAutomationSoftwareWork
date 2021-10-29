package CaseMethods;

import Utilities.BaseCaseMethods;
import Utilities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Case1ToGetOffer extends BaseCaseMethods {

    Case1OfferSteps case1OfferSteps = new Case1OfferSteps(driver);

    public Case1ToGetOffer(WebDriver driver){
        super(driver);
    }

    public void toGetOffer() throws IOException {
//        navigateToURL(configuration.getProperty("site_url"));
//        Assert.assertEquals(configuration.getProperty("site_url"), driver.getCurrentUrl()); // verify the page
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(configuration.getProperty("offerButtonXpath"))));
//        Assert.assertTrue(findElementByXpath(configuration.getProperty("offerButtonXpath")).isDisplayed());
//        fillInputFieldByXpath(configuration.getProperty("codePostXpath"), readStringTxtFileAndAddToList("./Report/postCodes.txt"));
//        clickElementByXpath(configuration.getProperty("offerButtonXpath"));
//        wait.until(ExpectedConditions.urlToBe(configuration.getProperty("site_url_offer")));
        case1OfferSteps.mainPageSteps();
        case1OfferSteps.address();
        case1OfferSteps.houseInfo();





    }

}
