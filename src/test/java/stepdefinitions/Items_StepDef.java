package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ItemsPage;
import pages.NewItemsPage;
import pages.SettingsPage;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.sql.*;
import java.time.Duration;


public class Items_StepDef {

    WebDriver driver = Driver.getDriver();
    SettingsPage settingsPage = new SettingsPage();
    ItemsPage itemsPage = new ItemsPage();
    NewItemsPage newItemsPage = new NewItemsPage();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    @When("User clicks on the Items Menu Link")
    public void user_clicks_on_the_items_menu_link() {
        settingsPage.itemsButton.click();
    }
    @Then("User should be navigated to the Items page")
    public void user_should_be_navigated_to_the_items_page() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.itemsLabel));
        String itemsPageUrl = "http://crater.primetech-apps.com/admin/items";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(itemsPageUrl,currentUrl);
        Assert.assertTrue(itemsPage.itemsLabel.isDisplayed());
    }
    @When("User clicks on the + Add Item button")
    public void user_clicks_on_the_add_item_button() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.addItemButton));
        itemsPage.addItemButton.click();
    }
    @Then("User should be navigated to the New Item page")
    public void user_should_be_navigated_to_the_new_item_page() {
        wait.until(ExpectedConditions.visibilityOf(newItemsPage.newItemLabel));
        Assert.assertTrue(newItemsPage.newItemLabel.isDisplayed());
    }
    @When("User enters {string} in the Name input field")
    public void user_enters_in_the_name_input_field(String name) {
        SeleniumUtils.sendkeysWithActionsClass(newItemsPage.nameInputField, name);
    }
    @And("User enters {string} in the Price input field")
    public void user_enters_in_the_price_input_field(String price) {
        SeleniumUtils.sendkeysWithActionsClass(newItemsPage.priceInputField, price);
    }
    @And("User selects {string} as one of the Unit")
    public void user_selects_as_one_of_the_unit(String unit) {
        SeleniumUtils.sendkeysWithActionsClass(newItemsPage.unitDropdown, unit);
        newItemsPage.unitDropdown.sendKeys(Keys.ENTER);
    }
    @And("User enters {string} in the Description text field")
    public void user_enters_in_the_description_text_field(String description) {
        newItemsPage.descriptionTextField.sendKeys(description);
    }
    @When("User clicks on the Save Item button")
    public void user_clicks_on_the_save_item_button() {
        newItemsPage.saveItemButton.click();
    }
    @Then("User should see a flash message {string} with a close button to the right")
    public void user_should_see_a_flash_message_with_a_close_button_to_the_right(String string) {
        wait.until(ExpectedConditions.visibilityOf(newItemsPage.flashMessage));
        System.out.println("The Message is: " + newItemsPage.flashMessage.getText());
        Assert.assertTrue(newItemsPage.flashMessage.isDisplayed());
    }
    @And("the flash message should disappear within {int} seconds or less")
    public void the_flash_message_should_disappear_within_seconds_or_less(Integer int1) {
        boolean flashMessageIsGone = wait.until(ExpectedConditions.invisibilityOf(newItemsPage.flashMessage));
        Assert.assertTrue(flashMessageIsGone);
    }
    @And("User should be navigated back to the Items page")
    public void user_should_be_navigated_back_to_the_items_page() {
        wait.until(ExpectedConditions.visibilityOf(itemsPage.itemsLabel));
        String itemsPageUrl = "http://crater.primetech-apps.com/admin/items";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(itemsPageUrl, currentUrl);
        Assert.assertTrue(itemsPage.itemsLabel.isDisplayed());
    }
    @And("User should be able to view item added within the Items list")
    public void user_should_be_able_to_view_item_added_within_the_items_list() {
        String nameOfItem = "Cell phone";
//        for(WebElement item : itemsPage.itemsList){
//            System.out.println(item.getText());
//        }
        Assert.assertTrue(SeleniumUtils.isItemInTable(itemsPage.itemsList, nameOfItem));
    }
    @And("User should be able to see the entered information in the application database")
    public void user_should_be_able_to_see_the_entered_information_in_the_application_database() {
        String query = "SELECT i.name, i.description, i.price, i.unit_id, u.name AS 'unit' " +
                "FROM items i INNER JOIN units u " +
                "ON i.unit_id = u.id " +
                "WHERE i.name = 'Cell phone' AND i.description = 'This cell phone is for Diana as a gift.' " +
                "AND i.price = '50000' AND u.name = 'Dollars';";



        String dbUrl = "jdbc:mysql://stack-overflow.cfse9bqqndon.us-east-1.rds.amazonaws.com/CraterDBS";
        String userName = "craterdbuser";
        String password = "ptschool2023";

        String expectedName = "Cell phone";
        String expectedPrice = "50000";
        String expectedUnit = "23";
        String expectedDescription = "This cell phone is for Diana as a gift.";

        //Creating a connection
        try (Connection connection = DriverManager.getConnection(dbUrl, userName, password);
             //Creating a statement
             Statement statement = connection.createStatement();
             //Executing the query
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                String actualName = resultSet.getString("name");
                String actualUnit = resultSet.getString("unit_id");
                String actualPrice = resultSet.getString("price");
                String actualDescription = resultSet.getString("description");

                Assert.assertEquals("Name mismatch", expectedName, actualName);
                Assert.assertEquals("Unit mismatch", expectedUnit, actualUnit);
                Assert.assertEquals("Price mismatch", expectedPrice, actualPrice);
                Assert.assertEquals("Description mismatch", expectedDescription, actualDescription);

            } else {
                Assert.fail("No matching record found in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Assert.fail("Database verification failed: " + e.getMessage());
        }
    }

}
