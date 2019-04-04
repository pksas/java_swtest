package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

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
                      withallphonenumbers("+7(111)2223344").
                      withAllEmails("vano@mail.ru").
                      withGroup("test1"));
      app.goTo().homePage();
    }
  }

  @Test
  public void TestsContactEmails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> ! s.equals("")).collect(Collectors.joining("\n"));
  }
}
