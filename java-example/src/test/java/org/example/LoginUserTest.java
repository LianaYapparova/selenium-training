package org.example;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginUserTest extends BaseTest {
  private static final Faker faker = new Faker();

  @Test
  public void loginUserTest() {
    driver.get("http://localhost/litecart/en/");
    driver.findElement(By.cssSelector("[name=login_form] tr:nth-child(5) a")).click();
    driver.findElement(By.name("firstname")).sendKeys(faker.name().firstName());
    driver.findElement(By.name("lastname")).sendKeys(faker.name().lastName());
    driver.findElement(By.name("address1")).sendKeys(faker.address().fullAddress());
    driver.findElement(By.name("postcode")).sendKeys(faker.number().digits(5));
    driver.findElement(By.name("city")).sendKeys(faker.address().city());
    WebElement selectElement = driver.findElement(By.name("country_code"));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].selectedIndex = 224; arguments[0].dispatchEvent(new Event('change'))", selectElement);
    WebElement selectStateElement = driver.findElement(By.name("zone_code"));
    js.executeScript("arguments[0].selectedIndex = 5; arguments[0].dispatchEvent(new Event('change'))", selectStateElement);
    String email = faker.internet().emailAddress();
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("phone")).sendKeys(faker.number().digits(10));
    String password = faker.number().digits(9);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("confirmed_password")).sendKeys(password);
    driver.findElement(By.name("create_account")).click();
    driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
    driver.findElement(By.name("email")).sendKeys(email);
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
    driver.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
  }

}
