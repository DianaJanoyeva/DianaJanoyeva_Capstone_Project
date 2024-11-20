package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class SeleniumUtils {

    /**
     * This method will select from a Drop-down using selectByValue from Selenium
     * @param dropDownElements the Select element will select tag from the html
     * @param valueElement the option value that we want to select
     */

    public static void selectByValueFromDropDown(WebElement dropDownElements, String valueElement){
        Select select = new Select(dropDownElements);
        select.selectByValue(valueElement);
    }

    /**
     * This method will drag and drop an element from a source to a target element using Action class
     * @param driver the driver object that was instantiated
     * @param elementStart the source element that we want to drag
     * @param elementTarget the target element that we want to drag the source element to
     */

    public static void dragAndDrop(WebDriver driver,WebElement elementStart, WebElement elementTarget){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(elementStart, elementTarget).build().perform();
    }

    public static void sendKeysUsingJavaScriptExecutor(String inputString, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + inputString +"')", element);
    }

    public static void sendkeysWithActionsClass(WebElement element,String input) {
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(element, input).build().perform();

    }

    /**
     * This method will verify if an item is available within a table
     * @param elementList
     * @param itemNameToSearchFor
     * @return
     */
    public static boolean isItemInTable(List<WebElement> elementList, String itemNameToSearchFor) {
        for (WebElement item : elementList) {
            if (item.getText().equals(itemNameToSearchFor)) {
                return true;
            }
        }
        return false;


    }

}



