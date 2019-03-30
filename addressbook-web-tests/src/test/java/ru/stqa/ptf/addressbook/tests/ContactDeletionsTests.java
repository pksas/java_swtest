package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionsTests extends TestBase{

  @Test
  public void testContactDeletions() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact
              (new ContactData("Ivanov", "Ivan", "Ivanovich", "vano", "Ivanovo", "+7(111)2223344", "vano@mail.ru", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size() - 1);
    app.getContactHelper().submitContactDelition();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }
}
