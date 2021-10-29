package CaseMethods;

import Utilities.BaseCaseMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import java.io.IOException;
import java.util.Objects;

public class Case1OfferSteps extends BaseCaseMethods {

    public Case1OfferSteps(WebDriver driver) {
        super(driver);
    }

    public void mainPageSteps() throws IOException {
        navigateToURL(configuration.getProperty("site_url"));
        Assert.assertEquals(configuration.getProperty("site_url"), driver.getCurrentUrl()); // verify the page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(configuration.getProperty("offerButtonXpath"))));
        Assert.assertTrue(findElementByXpath(configuration.getProperty("offerButtonXpath")).isDisplayed());
        fillInputFieldByXpath(configuration.getProperty("codePostXpath"), readStringTxtFileAndAddToList("./Report/postCodes.txt"));
        clickElementByXpath(configuration.getProperty("offerButtonXpath"));
        wait.until(ExpectedConditions.urlToBe(configuration.getProperty("site_url_offer")));
    }


    public void address() throws IOException {
        fillInputFieldById("street-input", configuration.getProperty("street"));
        fillInputFieldById("building-number-input", configuration.getProperty("number"));
        deleteAllCharById("postal-input");
        fillInputFieldById("postal-input", readStringTxtFileAndAddToList("./Report/postCodes.txt"));
        fillInputFieldById("block-input", "B");
        fillInputFieldById("door-number-input", configuration.getProperty("doorNumber"));
        int floorNumber = driver.findElements(By.xpath("//*[@id='floor-number-select']/option")).size();
        selectComboBoxId("floor-number-select", randomNumberFromAnyIndex(floorNumber, 1));
        String floorNo = floorNumber("//*[@id='floor-number-select']/option");
        clickElementByXpath("//button[@class='tiko-btn-primary tiko-btn-is-small right']");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section/div[2]/div/div[1]/div/div[2]/app-form-address-bar//address/div[3]/span[1]")));
        Assert.assertTrue(findElementByXpath("//section/div[2]/div/div[1]/div/div[2]/app-form-address-bar//address/div[3]/span[1]").getText().contains(floorNo));
        sleep(1);
    }


    public void houseInfo() {
        // step - 2
        selectRandomListArg("//*[@id='bedroom'][@class='radio-button-group']//div/app-row-flex//*[@class='row-flex-wrapper']/label");
        String bedroomNumber = houseProperties("//*[@id='bedroom'][@class='radio-button-group']//div/app-row-flex//*[@class='row-flex-wrapper']/label");
        selectRandomListArg("//*[@id='bathroom'][@class='radio-button-group']//div/app-row-flex//*[@class='row-flex-wrapper']/label");
        String bathroomNumber = houseProperties("//*[@id='bathroom'][@class='radio-button-group']//div/app-row-flex//*[@class='row-flex-wrapper']/label");
        selectRandomListArg("//*[@id='interiorExterior'][@class='radio-button-group ng-star-inserted']//div/app-row-flex//*[@class='row-flex-wrapper']/label");
        selectRandomListArg("//*[@id='realEstateType'][@class='radio-button-group']//div/app-row-flex//*[@class='row-flex-wrapper']/label");
        selectRandomListArg("//*[@class='row-flex-wrapper']//label//div[@class='checkbox-checked-square']");
        clickElementByXpath("//*[@class='tiko-btn-primary tiko-btn-is-small']");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='sales-reason-select']/option")));


        // step - 3
        int reasonOfSale = driver.findElements(By.xpath("//*[@id='sales-reason-select']/option")).size();
        selectComboBoxId("sales-reason-select", randomNumberFromAnyIndex(reasonOfSale, 1));
        int peopleNumberLiving = driver.findElements(By.xpath("//*[@id='when-to-move-select']/option")).size();
        selectComboBoxId("when-to-move-select", randomNumberFromAnyIndex(peopleNumberLiving, 1));
        String houseType = findElementByXpath("//app-form-address-bar/div/div/address/div[5]/div/span").getText();
        clickElementByXpath("//*[@class='tiko-btn-primary tiko-btn-is-small']");


        // step - 4
        fillInputFieldById("personal-name-input", configuration.getProperty("userNameAndSurname"));
        fillInputFieldById("personal-email-input", configuration.getProperty("userMail"));
        fillInputFieldById("personal-phone-input", configuration.getProperty("userPhone"));
        Assert.assertTrue(findElementByXpath("//*[@formcontrolname='agreementAccepted']").isDisplayed());
        Assert.assertTrue(findElementByXpath("//*[@formcontrolname='thirdPartyAccepted']").isDisplayed());
        clickElementByXpath("//*[@class='tiko-btn-primary tiko-btn-is-small']");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(configuration.getProperty("bathroomXpath"))));

        // verify of filled information
        Assert.assertEquals(driver.getCurrentUrl(), configuration.getProperty("site_url_exit"));
        Assert.assertEquals(findElementByXpath(configuration.getProperty("userNameAndSurnameXpath")).getText(), configuration.getProperty("userNameAndSurname"));
        Assert.assertEquals(findElementByXpath(configuration.getProperty("userMailXpath")).getText(), configuration.getProperty("userMail"));
        Assert.assertEquals(findElementByXpath(configuration.getProperty("userPhoneXpath")).getText(), configuration.getProperty("userPhone"));
        Assert.assertTrue(Objects.equals(findElementByXpath(configuration.getProperty("bedroomXpath")).getText(), "Más de 5") || Objects.equals(findElementByXpath(configuration.getProperty("bedroomXpath")).getText(), bedroomNumber));
        Assert.assertTrue(Objects.equals(findElementByXpath(configuration.getProperty("bathroomXpath")).getText(), "Más de 3") || Objects.equals(findElementByXpath(configuration.getProperty("bathroomXpath")).getText(), bathroomNumber));
        Assert.assertEquals(findElementByXpath("//app-form-success/app-form-address-bar/div/div/address/div[5]/div/span").getText(), houseType);




    }
}
