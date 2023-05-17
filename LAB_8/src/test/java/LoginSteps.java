import io.cucumber.java.ParameterType;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LoginSteps {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }


    @Given("User is on login page")
    public void user_is_on_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("User puts {username} and {password}")
    public void userPutsUsernameAndPassword(String username, String password) {
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys(password);
    }

    @And("Clicks login")
    public void clicks_login() {
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();
    }

    @Then("He should see {result}")
    public void heShouldSeeResult(String expectedResult) {
        assertEquals(driver.findElement(By.cssSelector("*[data-test=\"error\"]")).getText(), expectedResult);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
    @ParameterType(".*")
    public String username(String username) {
        return username;
    }

    @ParameterType(".*")
    public String password(String password) {
        return password;
    }

    @ParameterType(".*")
    public String result(String result) {
        return result;
    }
}