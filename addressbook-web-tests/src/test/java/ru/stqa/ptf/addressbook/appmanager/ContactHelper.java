package ru.stqa.ptf.addressbook.appmanager;

import com.sun.org.apache.bcel.internal.generic.Visitor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephonenumber());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactCreation() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void create(ContactData contact) {
    fillContactForm(contact, true);
    submitContactCreation();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(int index) {
    initContactModification(index);
    submitContactDelition();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void submitContactDelition() {
    click(By.xpath("(//input[@name='update'])[3]"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.tagName("td"));
    for (int i = 0; i < elements.size(); i++) {
      int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("value"));
      i++;
      String lastname = elements.get(i).getText();
      i++;
      String firstname = elements.get(i).getText();
      i++;
      String address = elements.get(i).getText();
      i++;
      String email = elements.get(i + 1).getText();
      i++;
      String phonenumber = elements.get(i + 1).getText();
      i = i + 4;
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withHomephonenumber(phonenumber).withEmail(email));
    }
    return contacts;
  }
}
