package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class UsersManagerHelper extends HelperBase {

  public UsersManagerHelper (ApplicationManager app) {
    super(app);
  }


  public void resetPasswordById(int id) {
    wd.findElement(By.linkText("Управление пользователями")).click();
    wd.findElement(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id))).click();
    wd.findElement(By.xpath("//input[@value='Сбросить пароль']")).click();
  }

  public void confirmNewPassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.id("password"), password);
    type(By.id("password-confirm"), password);
    wd.findElement(By.xpath("//button/span")).click();
  }
}