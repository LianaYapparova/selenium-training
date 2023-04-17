package org.example;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class MyFirstTest extends BaseTest{
  @Test
  public void myFirstTest() {
    driver.get("http://www.google.com");
    driver.findElement(By.name("q")).sendKeys("webdriver");
    driver.findElements(By.name("btnK")).get(1).click();
    wait.until(titleIs("webdriver - Поиск в Google"));
  }
}
