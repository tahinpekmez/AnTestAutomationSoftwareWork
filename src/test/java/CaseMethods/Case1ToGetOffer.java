package CaseMethods;

import Utilities.BaseCaseMethods;
import org.openqa.selenium.WebDriver;
import java.io.IOException;


public class Case1ToGetOffer extends BaseCaseMethods {

    Case1OfferSteps case1OfferSteps = new Case1OfferSteps(driver);

    public Case1ToGetOffer(WebDriver driver){
        super(driver);
    }

    public void toGetOffer() throws IOException {
        case1OfferSteps.mainPageSteps();
        case1OfferSteps.houseInfo();

    }

}
