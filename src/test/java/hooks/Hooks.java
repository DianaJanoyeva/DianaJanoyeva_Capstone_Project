package hooks;

import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

import java.time.Duration;

public class Hooks {


    @Before()
    public void beforeScenario(){
        System.out.println("We are running before each scenario");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @After()
    public void afterScenario(Scenario scenario){
        System.out.println("We are running after each scenario");

        if(scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }

//    @BeforeStep
//    public void beforeStep(){
//        System.out.println("This line will get printed before each step");
//    }
//
//    @AfterStep
//    public void afterStep() throws InterruptedException {
//        System.out.println("This line will get printed after each step");
//        Thread.sleep(1000);
//    }



    //This is a note code for using order as parameter in the annotation
//    @Before(order=2)
//    public void beforeScenario(){
//        System.out.println("We are running before each scenario");
//        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//        System.out.println("This is running as order 1 in before method");
//    }
//
//    @Before(order=1)
//    public void beforeScenario2(){
//        System.out.println("This is running as order 2 in before method");
//    }

//We can use tag inside the annotation parameter
//    @Before("@regression")
//    public void beforeScenario(){
//        System.out.println("We are running before each scenario");
//        Driver.getDriver().manage().window().maximize();
//        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//    }



}
