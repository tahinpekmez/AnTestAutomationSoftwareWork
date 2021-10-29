package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BaseCaseMethods extends BasePage{

    Select select;

    public BaseCaseMethods(WebDriver driver) {
        super(driver);
    }

    public int selectRandomListArgByXpath(String xpath){
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        int theListLength = elements.size();
        return randomNumber(theListLength, 1);
    }


    public void selectRandomListArg(String xpath){
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        elements.get(randomNumber(elements.size())).click();
    }


    protected String readStringTxtFileAndAddToList(String fileName) throws IOException {

        BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
        List <String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }

        return listOfLines.get(randomNumber(listOfLines.size()));
    }


    protected void deleteAllCharById(String id){
        findElementById(id).sendKeys(Keys.CONTROL + "A");
        findElementById(id).sendKeys(Keys.DELETE);
    }


    String result;
    public String houseProperties(String xpath){
        List <WebElement> radioButton = driver.findElements(By.xpath(xpath + "/input"));
        int listSize = radioButton.size();
        for(int i = 1; i<=listSize; ++i){
            if(findElementByXpath(xpath + "[" + i + "]/input").isSelected()){
                result = findElementByXpath(xpath + "[" + i + "]/div/div[2]").getText();
            }
        }
        return result;

    }


    String value;
    public String floorNumber(String xpath){
        List <WebElement> radioButton = driver.findElements(By.xpath(xpath));
        int listSize = radioButton.size();
        for(int i = 1; i<=listSize; ++i){
            if(findElementByXpath(xpath + "[" + i + "]").isSelected()){
                value = findElementByXpath(xpath + "[" + i + "]").getText();
            }
        }
        return value;

    }

}
