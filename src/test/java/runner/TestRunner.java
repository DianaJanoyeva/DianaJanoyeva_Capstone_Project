package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)//You telling junit to run the TestRunner class as a cucumber test
@CucumberOptions(features = "src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        plugin = {"pretty","html:target/FinalCapstone-report.html",
                "json:target/primetech-report.json"},

        //tags = "(@smoke) and (not @saucedemo)"
        tags = "@crater and @regression"


        //strict was decomissioned from 7.0.0 but it forces the test to fail if the step is not defined in the step definition
        //monochrome = true -- is to prettify the console output
)
public class TestRunner {



}


/**
 * This class will allow us to execute and run our feature files/step definitions
 */
