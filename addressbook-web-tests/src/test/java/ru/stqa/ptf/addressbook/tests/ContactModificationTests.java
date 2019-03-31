package ru.stqa.ptf.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
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
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).
            withFirstname("Vladimir").
            withMiddlename("Vladimirovich").
            withLastname("Putin").
            withNickname("vano").
            withAddress("Ivanovo").
            withHomephonenumber("+7(111)2223344").
            withEmail("vano@mail.ru");
    app.contact().modify(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(
            before.without(modifiedContact).withAdded(contact)));
  }

}
