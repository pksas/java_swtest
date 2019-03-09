package ru.stqa.ptf.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.ptf.addressbook.model.GroupData;

public class GroupHelper {
  private WebDriver wd;

  public GroupHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void returntoGroupPage() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void submitGroupCreation() {
    wd.findElement(By.linkText("group page")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    wd.findElement(By.name("submit")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Groups'])[1]/following::div[1]")).click();
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void deleyeSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
}
