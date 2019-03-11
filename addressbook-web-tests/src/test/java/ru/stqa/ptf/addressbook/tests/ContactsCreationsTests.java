package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactsCreationsTests extends TestBase{

  @Test
  public void testContactsCreations() throws Exception {
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Ivanov", "Ivan", "Ivanovich", "vano", "Ivanovo", "+7(111)2223344", "vano@mail.ru"));
    app.getContactHelper().submitContactCreation();
  }
}
