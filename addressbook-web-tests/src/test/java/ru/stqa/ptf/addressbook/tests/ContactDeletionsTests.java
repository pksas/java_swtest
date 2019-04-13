package ru.stqa.ptf.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.model.ContactData;
import ru.stqa.ptf.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionsTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.goTo().addNewContactPage();
      app.contact().create
              (new ContactData().
                      withFirstname("forDelFirstName").
                      withMiddlename("forDelMiddleName").
                      withLastname("forDelLastName").
                      withNickname("forDelNickName").
                      withAddress("forDelAddress").
                      withHomePhone("999 999").
                      withMobilePhone("888 888").
                      withWorkPhone("777 777").
                      withSecondaryPhone("666 666").
                      withEmail1("fordel1@mail.ru").
                      withEmail2("fordel2@mail.ru").
                      withEmail3("fordel3@mail.ru").
                      withPhoto(new File("src/test/resources/BeKind.png")));
    }
  }

  @Test
  public void testContactDeletions() throws Exception {
    Contacts before = app.db().contacts();
    app.goTo().homePage();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
