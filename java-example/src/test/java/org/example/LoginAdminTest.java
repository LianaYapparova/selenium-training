package org.example;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginAdminTest extends BaseTest {

  @Test
  public void loginAdminTest() {
    driver.get(" http://localhost/litecart/admin/");
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }
}
