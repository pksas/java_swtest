package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

public class ContactDeletionsTests extends TestBase{

  @Test
  public void testContactDeletions() throws Exception {
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoAddNewContactPage();
      app.getContactHelper().createContact
              (new ContactData("Ivanov", "Ivan", "Ivanovich", "vano", "Ivanovo", "+7(111)2223344", "vano@mail.ru", "test1"));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().selectContactCreation();
    app.getContactHelper().deleteSelectedContact();
    app.acceptContactDeletion();
  }
}
