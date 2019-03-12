package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Vladimir", "Vladimirovich", "Putin", "vano", "Ivanovo", "+7(111)2223344", "vano@mail.ru"));
    app.getContactHelper().submitContactModification();
  }
}
