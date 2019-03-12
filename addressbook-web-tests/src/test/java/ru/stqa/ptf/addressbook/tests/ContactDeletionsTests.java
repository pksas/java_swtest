package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionsTests extends TestBase{

  @Test
  public void testContactDeletions() throws Exception {
    app.getContactHelper().selectContactCreation();
    app.getContactHelper().deleteSelectedContact();
    app.acceptContactDeletion();
  }
}
