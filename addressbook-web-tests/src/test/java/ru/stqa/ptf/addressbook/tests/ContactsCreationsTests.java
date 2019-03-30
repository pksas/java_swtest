package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ContactsCreationsTests extends TestBase{

  @Test
  public void testContactsCreations() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("test1", "test2", "Ivanovich", "vano", "Ivanovo", "+7(111)2223344", "vano@mail.ru", "test1");
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().createContact(contact);
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}