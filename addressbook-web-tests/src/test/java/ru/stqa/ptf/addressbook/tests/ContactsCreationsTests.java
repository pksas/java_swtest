package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactsCreationsTests extends TestBase{

  @Test
  public void testContactsCreations() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoAddNewContactPage();
    app.getContactHelper().createContact
            (new ContactData("test1", "test2", "Ivanovich", "vano", "Ivanovo", "+7(111)2223344", "vano@mail.ru", "test1"));
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}