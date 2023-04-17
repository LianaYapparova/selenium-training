package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CountriesTest extends BaseTest{

  @BeforeMethod
  public void login(){
    driver.get(" http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @Test
  public void countriesTest() {
    driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
    List<WebElement> webElements = driver.findElement(By.className("dataTable")).findElements(By.className("row"));
    List<String> stringList = driver.findElement(By.className("dataTable"))
        .findElements(By.cssSelector("td:nth-child(5)"))
        .stream().map(WebElement::getText)
        .collect(Collectors.toList());
    isSorted(stringList);
    IntStream.range(0, webElements.size()).forEach(i -> {
      WebElement element = driver.findElement(By.className("dataTable")).findElements(By.className("row")).get(i);
      if (Integer.parseInt(element.findElement(By.cssSelector("td:nth-child(6)")).getText()) > 0) {
        element.findElement(By.cssSelector("td:nth-child(5) a")).click();
        List<WebElement> webElementsTimeZone = driver.findElements(By.cssSelector(".dataTable  td:nth-child(3)"));
        List<String> stringListTimeZone = webElementsTimeZone.stream().map(e -> e.getText()).collect(Collectors.toList());
        isSorted(stringListTimeZone);
        driver.navigate().back();
      }
    });
  }

  @Test
  public void timeZoneTest() {
    driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
    List<WebElement> webElements = driver.findElements(By.cssSelector(".dataTable td:nth-child(3) a"));
    IntStream.range(0, webElements.size()).forEach(i -> {
      driver.findElements(By.cssSelector(".dataTable td:nth-child(3) a")).get(i).click();
      List<String> listTimeZone = driver.findElements(By.cssSelector(".dataTable  td:nth-child(3) option[selected]"))
          .stream().map(e -> e.getText()).collect(Collectors.toList());
      isSorted(listTimeZone);
      driver.navigate().back();
    });
  }

  private void isSorted(List<String> stringList) {
    IntStream.range(0, stringList.size())
        .noneMatch(i -> stringList.get(i).compareTo(stringList.get(i + 1)) < 0);
  }

  @AfterMethod
  public void logout(){
    driver.findElement(By.cssSelector("a[title=Logout]")).click();
  }
}
