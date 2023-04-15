package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CampaignsTest {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, Duration.ofMillis(100));
  }

  @BeforeMethod
  public void login() {
    driver.get(" http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void productTest() {
    driver.get("http://localhost/litecart/en/");
    WebElement webElement = driver.findElement(By.id("box-campaigns")).findElements(By.cssSelector("li")).get(0);
    String nameProduct = webElement.findElement(By.className("name")).getText();
    WebElement regularPrice = webElement.findElement(By.className("regular-price"));
    String regularPriceProduct = regularPrice.getText();
    String colorPriceProduct = regularPrice.getCssValue("color");
    String lineThroughPriceProduct = regularPrice.getCssValue("text-decoration");
    String sizePriceProduct = regularPrice.getCssValue("font-size");

    WebElement campaignPrice = webElement.findElement(By.className("campaign-price"));
    String campaignPriceProduct = campaignPrice.getText();
    String colorCampaignPriceProduct = campaignPrice.getCssValue("color");
    String stylePriceProduct = campaignPrice.getCssValue("font-weight");
    String sizeCampaignPriceProduct = campaignPrice.getCssValue("font-size");

    webElement.click();

    String nameProductDuck = driver.findElement(By.cssSelector("h1")).getText();
    WebElement regularPriceProductDuck = driver.findElement(By.cssSelector(".regular-price"));
    WebElement campaignPriceProductDuck = driver.findElement(By.cssSelector(".campaign-price"));

    String regularPriceProductDuckText = regularPriceProductDuck.getText();
    String colorPriceProductDuck = regularPriceProductDuck.getCssValue("color");
    String lineThroughPriceProductDuck = regularPriceProductDuck.getCssValue("text-decoration");
    String sizePriceProductDuck = regularPriceProductDuck.getCssValue("font-size");

    String campaignPriceProductDuckText = campaignPriceProductDuck.getText();
    String colorCampaignPriceProductDuck = campaignPriceProductDuck.getCssValue("color");
    String stylePriceProductDuck = campaignPriceProductDuck.getCssValue("font-weight");
    String sizeCampaignPriceProductDuck = campaignPriceProductDuck.getCssValue("font-size");


    Assert.assertEquals(nameProduct, nameProductDuck);
    Assert.assertEquals(regularPriceProduct, regularPriceProductDuckText);
    Assert.assertEquals(campaignPriceProduct, campaignPriceProductDuckText);
    List<String> colorPriceProductSubString = Arrays.stream(colorPriceProduct.substring(5, 21).split(","))
        .map(String::trim).collect(Collectors.toList());
    Assert.assertTrue(checkGrey(colorPriceProductSubString));
    Assert.assertTrue(lineThroughPriceProduct.contains("line-through"));
    List<String> colorCampaignPriceProductSubString = Arrays.stream(colorCampaignPriceProduct.substring(5, 17).split(","))
        .map(String::trim).collect(Collectors.toList());
    Assert.assertTrue(checkRed(colorCampaignPriceProductSubString));
    Assert.assertTrue(Integer.parseInt(stylePriceProduct) >= 700);
    Assert.assertTrue(Float.parseFloat(sizePriceProduct.replaceFirst("px", ""))
        < Float.parseFloat(sizeCampaignPriceProduct.replaceFirst("px", "")));

    List<String> colorPriceProductDuckSubString = Arrays.stream(colorPriceProductDuck.substring(5, 21).split(","))
        .map(String::trim).collect(Collectors.toList());
    Assert.assertTrue(checkGrey(colorPriceProductDuckSubString));
    Assert.assertTrue(lineThroughPriceProductDuck.contains("line-through"));
    List<String> colorCampaignPriceProductDuckSubString = Arrays.stream(colorCampaignPriceProductDuck.substring(5, 17).split(","))
        .map(String::trim).collect(Collectors.toList());
    Assert.assertTrue(checkRed(colorCampaignPriceProductDuckSubString));
    Assert.assertTrue(Integer.parseInt(stylePriceProductDuck) >= 700);
    Assert.assertTrue(Float.parseFloat(sizePriceProductDuck.replaceFirst("px", ""))
        < Float.parseFloat(sizeCampaignPriceProductDuck.replaceFirst("px", "")));
  }

  private boolean checkRed(List<String> stringList) {
    return (stringList.get(1).equals("0") && stringList.get(2).equals("0"));
  }

  private boolean checkGrey(List<String> stringList) {
    return stringList.get(0).equals(stringList.get(1)) && stringList.get(1).equals(stringList.get(2));
  }

  @AfterClass(alwaysRun = true)
  public void stop() {
    driver.quit();
  }
}
