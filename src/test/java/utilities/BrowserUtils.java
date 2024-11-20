package utilities;

import org.openqa.selenium.WebDriver;

public class BrowserUtils {

    /**
     * This method will refresh the browser
     * @param driver this is the driver object
     */
    public static void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }




}
