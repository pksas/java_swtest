package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactsCreationsTests extends TestBase{

  @Test
  public void testContactsCreations() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact =
            new ContactData().
                    withFirstname("test11").
                    withMiddlename("test2").
                    withLastname("Ivanovich").
                    withNickname("vano").
                    withAddress("Ivanovo").
                    withHomephonenumber("+7(111)2223344").
                    withEmail("vano@mail.ru").
                    withGroup("test1");
    app.goTo().addNewContactPage();
    app.contact().create(contact);
    app.goTo().homePage();
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}