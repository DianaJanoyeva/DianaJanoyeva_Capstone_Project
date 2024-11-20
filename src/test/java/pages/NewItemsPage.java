package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class NewItemsPage {

    public NewItemsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h3[@class='text-2xl font-bold text-left text-black' and text()='New Item']")
    public WebElement newItemLabel;

    @FindBy(xpath = "//input[@class='font-base block w-full sm:text-sm border-gray-200 rounded-md text-black focus:ring-primary-400 focus:border-primary-400' and @type='text']")
    public WebElement nameInputField;

    @FindBy(xpath = "//input[@class='font-base block w-full sm:text-sm border-gray-200 rounded-md text-black focus:ring-primary-400 focus:border-primary-400 v-money3' and @type='tel']")
    public WebElement priceInputField;

    @FindBy(xpath = "//input[@type='text' and @class='w-full absolute inset-0 outline-none appearance-none box-border border-0 text-sm font-sans bg-white rounded-md pl-3.5']")
    public WebElement unitDropdown;

    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement descriptionTextField;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveItemButton;

    @FindBy(xpath = "//div[@class='p-4']")
    public WebElement flashMessage;


}
