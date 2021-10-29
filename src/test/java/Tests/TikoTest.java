package Tests;

import CaseMethods.Case1ToGetOffer;
import Utilities.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;


public class TikoTest extends BaseTest {

    Case1ToGetOffer case1ToGetOffer;

    @Test
    public void case1() throws IOException {
        case1ToGetOffer = new Case1ToGetOffer(driver);
        case1ToGetOffer.toGetOffer();
    }
}
