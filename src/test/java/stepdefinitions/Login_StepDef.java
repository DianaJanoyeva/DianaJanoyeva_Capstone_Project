package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SettingsPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.SeleniumUtils;


public class Login_StepDef {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    SettingsPage settingsPage = new SettingsPage();

    @Given("User is navigated to crater login page")
    public void user_is_navigated_to_crater_login_page() {
        driver.get(ConfigurationReader.getPropertyValue("craterUrl"));
//        driver.get("");
//        driver.navigate().to("");
    }
    @When("User enters {string} in the Email input field")
    public void user_enters_in_the_email_input_field(String email) {
        SeleniumUtils.sendkeysWithActionsClass(loginPage.emailInput, email);
       // loginPage.emailInput.sendKeys("");
    }
    @And("User enters {string} for the password input field")
    public void user_enters_for_the_password_input_field(String password) {
        SeleniumUtils.sendkeysWithActionsClass(loginPage.passwordInput, password);
    }
    @And("User clicks on the Login button")
    public void user_clicks_on_the_login_button() {
        loginPage.loginButton.click();
    }
    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        String loginUrl = "http://crater.primetech-apps.com/login";
        String afterLoginUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login in is : " + afterLoginUrl);
        Assert.assertNotEquals(loginUrl , afterLoginUrl);
    }
    @And("User should see the {string} page being displayed")
    public void user_should_see_the_page_being_displayed(String string) {
        Assert.assertTrue(settingsPage.settingsLabel.isDisplayed());
        System.out.println("settings page url is " + driver.getCurrentUrl());
    }
}
