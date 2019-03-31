package ru.stqa.ptf.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size() == 0) {
      app.goTo().addNewContactPage();
      app.contact().create
              (new ContactData().
                      withFirstname("Ivanov").
                      withMiddlename("Ivan").
                      withLastname("Ivanovich").
                      withNickname("vano").
                      withAddress("Ivanovo").
                      withHomephonenumber("+7(111)2223344").
                      withEmail("vano@mail.ru").
                      withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void testContactModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withFirstname("Vladimir").
            withMiddlename("Vladimirovich").
            withLastname("Putin").
            withNickname("vano").
            withAddress("Ivanovo").
            withHomephonenumber("+7(111)2223344").
            withEmail("vano@mail.ru");
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
